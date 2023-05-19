package com.news.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.*;

/**
 * <p>
 *
 * </p>
 *
 * @author Sancean
 * @since 2023-05-19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("news_data")
public class NewsData implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 新闻内容
     */
    private String data;

    /**
     * 发布者
     */
    private String publisher;

    /**
     * 发布日期
     */
    private LocalDateTime date;
}
