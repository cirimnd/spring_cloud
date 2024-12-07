package com.example.bkmallgateway.config;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.net.URI;
import java.nio.charset.StandardCharsets;
@Slf4j

@Component
public class LoggingFilter implements GlobalFilter, Ordered  {

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {




        // 添加线程日志号
        MDC.put("UUID", Thread.currentThread().getName());

        ServerHttpRequest request = exchange.getRequest();
        URI URIPath = request.getURI();
        String path = request.getPath().value();
        String method = request.getMethod().toString();
        HttpHeaders header = request.getHeaders();
        log.info("");
        log.info(" --------------------- 请求开始 ------------------------");
        log.info("原始请求信息：URI = {}, path = {}，method = {}，header = {}。", URIPath, path, method, header);
        if ("POST".equals(method)) {
            return DataBufferUtils.join(exchange.getRequest().getBody())
                    .flatMap(dataBuffer -> {
                        byte[] bytes = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(bytes);
                        String bodyString = new String(bytes, StandardCharsets.UTF_8);
                        bodyString = bodyString.replaceAll("\"ImageBase64\":\"[^\"]*\",", "");
                        log.info("{} 请求参数,不包含ImageBase64：" + bodyString,exchange.getAttributes().get("requestId"));
                        exchange.getAttributes().put("POST_BODY", bodyString);
                        DataBufferUtils.release(dataBuffer);
                        Flux<DataBuffer> cachedFlux = Flux.defer(() -> {
                            DataBuffer buffer = exchange.getResponse().bufferFactory()
                                    .wrap(bytes);
                            return Mono.just(buffer);
                        });

                        ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(
                                exchange.getRequest()) {
                            @NotNull
                            @Override
                            public Flux<DataBuffer> getBody() {
                                return cachedFlux;
                            }
                        };
                        return chain.filter(exchange.mutate().request(mutatedRequest)
                                .build()).doFinally(signalType -> {
                            // 请求处理完毕后清理变量
                            exchange.getAttributes().remove("requestId");
                            exchange.getAttributes().remove("hasAcquired");
                        });
                    });
        } else if ("GET".equals(method)) {
            MultiValueMap<String, String> queryParams = request.getQueryParams();
            log.info("{} 请求参数：" + queryParams,exchange.getAttributes().get("requestId"));
            return chain.filter(exchange);
        }
        return chain.filter(exchange);
    }

}