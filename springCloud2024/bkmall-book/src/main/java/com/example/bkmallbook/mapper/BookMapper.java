package com.example.bkmallbook.mapper;

import com.example.bkmallbook.entity.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 图书表 Mapper 接口
 * </p>
 *
 * @author 齐润东
 * @since 2024-11-28
 */
@Mapper
public interface BookMapper extends BaseMapper<Book> {

}
