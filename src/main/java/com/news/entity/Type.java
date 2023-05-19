package com.news.entity;

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
public class Type implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 类型id
     */
    private Integer id;

    /**
     * 类型
     */
    private String type;
}
