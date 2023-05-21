package com.news.service;

import com.news.entity.Comment;
import com.news.entity.NewsComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Sancean
 * @since 2023-05-18
 */
public interface NewsCommentService extends IService<NewsComment> {
    //增加
    boolean insert(NewsComment newsComment);

    //删除
    boolean delete(Integer id);

    //批量删除
    boolean batch_delete(List<Integer> idList);

    //查询
    List<NewsComment> getAll();

    //更具新闻id获取评论数据
    List<Comment> getCommentByNews(Integer id);
}
