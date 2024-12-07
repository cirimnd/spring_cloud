package com.example.bkmallgateway.config;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author Miller.Lai
 * @description: 响应日志输出过滤器
 * @date 2024-01-31 14:26:55
 */
@Component
@Slf4j
public class WrapperResponseGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public int getOrder() {
        //-1 is response write filter, must be called before that
        return -2;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取response的 返回数据
        ServerHttpResponse originalResponse = exchange.getResponse();
        DataBufferFactory bufferFactory = originalResponse.bufferFactory();
//        StringBuffer responseBuffer =new StringBuffer();
        ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
            @NotNull
            @Override
            public Mono<Void> writeWith(@NotNull Publisher<? extends DataBuffer> body) {
                if ( body instanceof Flux) {
                    Flux<? extends DataBuffer> fluxBody = Flux.from(body);

                    return super.writeWith(fluxBody.buffer().map(dataBuffers -> {

                        StringBuilder responseBuffer = new StringBuilder(1024);
                        dataBuffers.forEach(dataBuffer -> {
                            try {
                                byte[] content = new byte[dataBuffer.readableByteCount()];
                                dataBuffer.read(content);
                                DataBufferUtils.release(dataBuffer);
                                String data = new String(content, StandardCharsets.UTF_8);
                                responseBuffer.append(data);
                            }catch (Exception e){
                                log.error("rsp log, list add error", e);
                            }
                        });

                        if(responseBuffer.indexOf("RequestId") != -1  && responseBuffer.substring(responseBuffer.indexOf("RequestId")).length()>=48){
                            String responseStr = responseBuffer.toString();
                            String requestId = responseStr.substring(responseStr.indexOf("RequestId")+12,responseStr.indexOf("RequestId")+48);
                            log.info("{} 响应结果，code:{},RequestId：{}",exchange.getAttributes().get("requestId"),getStatusCode(),requestId);


                            log.info("{} --------------------- 请求结束 ------------------------",exchange.getAttributes().get("requestId"));
                            log.info("");
                        }
                        String result = responseBuffer.toString();
                        getDelegate().getHeaders().setContentLength(result.getBytes().length);
                        return bufferFactory.wrap(result.getBytes());
                    }));
                }
                return super.writeWith(body);
            }
        };
        return chain.filter(exchange.mutate().response(decoratedResponse).build());
    }

    public static void main(String[] args) {
        String a = "\"RequestId\":\"1f123f96-56c7-4be4-9b4b-ac844de6f10b\"";
        String b = a.substring(a.indexOf("RequestId")+12,a.indexOf("RequestId")+48);
        System.out.println(a.substring(a.indexOf("RequestId")));
        System.out.println(b);
    }
}
