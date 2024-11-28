package com.example.bkmallbook.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.bkmallbook.entity.Book;
import com.example.bkmallbook.entity.Result;
//import com.example.bkmallcommon.pojo.Result;
import com.example.bkmallbook.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.apache.el.util.ReflectionUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 图书表 前端控制器
 * </p>
 *
 * @author 齐润东
 * @since 2024-11-28
 */
@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final IBookService bookService;

    /**
     * 根据书本id查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getBookById(@PathVariable("id") int id) {
        Book book = bookService.lambdaQuery()
                .eq(Book::getId, id).one();
        return Result.success(book);
    }

    /**
     * 根据书名模糊查询
     * @param name
     * @return
     */
    @GetMapping("/name")
    public Result getBooksByName(@RequestParam String name) {
        List<Book> bookList = bookService.lambdaQuery()
                .like(Book::getTitle, "%" + name + "%")
                .orderByAsc(Book::getId)
                .list();
        return Result.success(bookList);
    }

    /**
     * 根据作者名模糊查询
     * @param author
     * @return
     */
    @GetMapping("/author")
    public Result getBooksByAuthor(@RequestParam String author) {
        List<Book> bookList = bookService.lambdaQuery()
                .like(Book::getAuthor, "%" + author + "%")
                .orderByAsc(Book::getId)
                .list();
        return Result.success(bookList);
    }

    /**
     * 根据类别查询
     * @param catagory
     * @return
     */
    @GetMapping("/catagory")
    public Result getBooksByCatagory(@RequestParam String catagory) {
        List<Book> bookList = bookService.lambdaQuery()
                .eq(Book::getCategory, catagory)
                .orderByAsc(Book::getId)
                .list();
        return Result.success(bookList);
    }

    /**
     * 根据价格区间查询
     * @param maxPrice
     * @param minPrice
     * @return
     */
    @GetMapping("/price")
    public Result getBooksByPrice(@RequestParam int maxPrice, @RequestParam int minPrice) {
        List<Book> bookList = bookService.lambdaQuery()
                .le(Book::getPrice, maxPrice)
                .ge(Book::getPrice, minPrice)
                .orderByAsc(Book::getId)
                .list();
        return Result.success(bookList);
    }

    /**
     * 根据id删除书本
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result deleteBookById(@PathVariable("id") int id) {
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(Book::getId, id);
        bookService.remove(wrapper);
        return Result.success("删除成功");
    }
}
