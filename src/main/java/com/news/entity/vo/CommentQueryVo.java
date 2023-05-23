package com.news.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Sancean
 * @Date 2023/05/23 20:24
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentQueryVo {
    /**
     * 评论id
     */
    private Integer id;

    /**
     * 评论用户
     */
    private String user;

    /**
     * 评论内容
     */
    private String commentData;

    /**
     * 新闻id
     */
    private Integer newsId;
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
