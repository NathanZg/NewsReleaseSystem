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
//    private Integer NewsId;
//
//    /**
//     * 新闻标题
//     */
//    private String NewsTitle;
//
//    /**
//     * 新闻内容
//     */
//    private String NewsData;
//
//    /**
//     * 编者
//     */
//    private String NewsPublisher;
//
//    /**
//     * 新闻时间
//     */
//    private LocalDateTime NewsDate;

    /**
     * 当前页
     */
    private Long current;

    /**
     * 每页显示条数
     */
    private Long size;
}
