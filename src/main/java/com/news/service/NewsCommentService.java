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
    /**
     * 添加
     * @param newsComment 评论信息
     * @return 删除成功(true)/删除失败(false)
     */
    boolean insert(NewsComment newsComment);

    /**
     * 根据Id删除
     * @param id 评论id
     * @return 删除成功(true)/删除失败(false)
     */
    boolean delete(Integer id);

    /**
     *批量删除关联信息
     * @param deleteIds 以逗号分割的id序列
     * @return 成功 true 失败 false
     */
    boolean batchDelete(String deleteIds);

    /**
     * 查询全部
     * @param
     * @return List<NewsComment> 评论列表
     */
    List<NewsComment> getAll();

    /**
     * 根据新闻id获取对应的评论
     * @param id 新闻id
     * @return List<Comment>
     */
    List<Comment> getCommentByNews(Integer id);
}
