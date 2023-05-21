package com.news.service;

import com.news.entity.Comment;
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
public interface CommentService extends IService<Comment> {

    /**
     * 查询全部
     * @param
     * @return List<Comment> 评论列表
     */
    public List<Comment> getAll();

    /**
     * 添加
     * @param Comment comment 评论信息
     * @return 删除成功(true)/删除失败(false)
     */
    public boolean insert(Comment comment);

    /**
     * 根据Id修改
     * @param Comment comment 评论信息
     * @return 删除成功(true)/删除失败(false)
     */
    public boolean updateId(Comment comment);

    /**
     * 根据Id删除
     * @param Integer id 评论id
     * @return 删除成功(true)/删除失败(false)
     */
    public boolean delete(Integer id);

    /**
     * 批量删除
     * @param List<Integer> idList 评论id列表
     * @return 删除成功(true)/删除失败(false)
     */
    public boolean batch_delete(List<Integer> idList);
}
