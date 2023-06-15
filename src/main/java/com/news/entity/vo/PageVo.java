package com.news.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Sancean
 * @Date 2023/05/04 08:13
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVo<T> {
    /**
     * 当前页的记录
     */
    private List<T> records;
    /**
     * 记录总条数
     */
    private Long total;
    /**
     * 每页的大小
     */
    private Long size;
    /**
     * 当前页
     */
    private Long current;

    /**
     * 总页数
     */
    private Long pages;
}
