package com.news.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

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
@TableName("news_comment")
public class NewsComment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论id
     */
    @TableId(value = "comment_id")
    private Integer commentId;

    /**
     * 新闻id
     */
    private Integer newsId;
}
