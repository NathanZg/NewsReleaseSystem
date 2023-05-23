package com.news.entity.vo;

import com.news.entity.Comment;
import com.news.entity.NewsData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Sancean
 * @Date 2023/05/23 14:55
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsDetail {
    private NewsData news;
    private List<Comment> commentList;
}
