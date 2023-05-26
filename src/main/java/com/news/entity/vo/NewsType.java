package com.news.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewsType {
    /**
     * 新闻id
     */
    private Integer id;

    /**
     * 类型id
     */
    private Integer typeId;
    /**
     * 类型
     */
    private String type;
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
     * 发布日期
     */
    private LocalDateTime date;
}
