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
@TableName("news_type")
public class NewsType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 新闻id
     */
    @TableId(value = "news_id")
    private Integer newsId;

    /**
     * 类型id
     */
    private Integer typeId;
}
