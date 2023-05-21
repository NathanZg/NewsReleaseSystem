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
     * 待办事项id
     */
    private Integer itemId;

    /**
     * 待办事项标题
     */
    private String itemTitle;

    /**
     * 待办事项描述
     */
    private String itemDescription;

    /**
     * 开始时间
     */
    private LocalDateTime startDate;

    /**
     * 结束时间
     */
    private LocalDateTime endDate;

    /**
     * 完成标识
     */
    private Byte completionFlag;

    /**
     * 当前页
     */
    private Long current;

    /**
     * 每页显示条数
     */
    private Long size;
}
