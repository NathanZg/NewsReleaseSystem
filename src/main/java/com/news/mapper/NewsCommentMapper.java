package com.news.mapper;

import com.news.entity.Comment;
import com.news.entity.NewsComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Sancean
 * @since 2023-05-19
 */
@Mapper
public interface NewsCommentMapper extends BaseMapper<NewsComment> {

    @Select("select* from comment where id in(select comment_id from news_comment where news_comment.news_id=#{id})")
    public List<Comment> getCommentByNews(Integer id);
}
