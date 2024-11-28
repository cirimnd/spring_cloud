package com.example.bkmallbook.service.impl;

import com.example.bkmallbook.entity.Book;
import com.example.bkmallbook.mapper.BookMapper;
import com.example.bkmallbook.service.IBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 图书表 服务实现类
 * </p>
 *
 * @author 齐润东
 * @since 2024-11-28
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {

}
