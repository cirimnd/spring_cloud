//package com.example.bkmallorder.Utils;
//
//import com.example.bkmallcommon.domain.dto.BookDto;
//import com.example.bkmallcommon.domain.dto.UserDto;
//import com.example.bkmallcommon.pojo.Result;
//import com.example.bkmallorder.client.BookClient;
//import com.example.bkmallorder.client.UserClient;
//import com.example.bkmallorder.domain.vo.OrderVo;
//import com.example.bkmallorder.entity.Order;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.BeanUtils;
//
//@RequiredArgsConstructor
//public class ConvertorUtils {
//    private final BookClient bookClient;
//    private final UserClient userClient;
//
//    public OrderVo ToOrderVo(Order order){
//        OrderVo orderVo = new OrderVo();
//        orderVo.setOrderId(order.getOrderId());
//        orderVo.setOrderState(order.getState());
//        orderVo.setOrderDatetime(order.getOrderDatetime());
//        orderVo.setAddress(order.getAddress());
//        orderVo.setBookPrice(order.getBookPrice());
//
//        Result result = bookClient.getBookById(order.getBookId());
//        BookDto bookDto = new BookDto();
//        BeanUtils.copyProperties(bookDto,result.getData());
//        orderVo.setBookAuthor(bookDto.getBookAuthor());
//        orderVo.setBookName(bookDto.getBookName());
//        orderVo.setBookTitle(bookDto.getBookTitle());
//
//        Result result1 = userClient.getUserById(order.getUserId());
//        UserDto userDto = new UserDto();
//        BeanUtils.copyProperties(userDto,result1.getData());
//        orderVo.setUsername(userDto.getUserName());
//
//        return orderVo;
//    }
//}
