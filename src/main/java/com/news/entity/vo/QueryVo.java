package com.news.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Sancean
 * @Date 2023/05/01 11:05
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryVo {
    /**
     * 新闻id
     */
    private Integer id;

    /**
     * 类型id
     */
    private Integer typeId;

    /**
     * 新闻标题
     */
    private String title;

    /**
     * 新闻内容
     */
    private String data;

    /**
     * 编者
     */
    private String publisher;

    /**
     * 开始时间
     */
    private LocalDateTime startDate;

    /**
     * 结束时间
     */
    private LocalDateTime endDate;

    /**
     * 当前页
     */
    private Long current;

    /**
     * 每页显示条数
     */
    private Long size;
}
