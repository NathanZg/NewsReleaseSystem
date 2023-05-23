package com.news.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.news.entity.Comment;
import com.news.entity.vo.PageVo;

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
    List<Comment> getAll();

    /**
     * 添加
     * @param comment 评论信息
     * @return 删除成功(true)/删除失败(false)
     */
    boolean insert(Comment comment);

    /**
     * 根据Id修改
     * @param comment 评论信息
     * @return 删除成功(true)/删除失败(false)
     */
    boolean updateId(Comment comment);

    /**
     * 根据Id删除
     * @param id 评论id
     * @return 删除成功(true)/删除失败(false)
     */
    boolean delete(Integer id);

    /**
     * 批量删除
     * @param deleteIds 评论id列
     * @return 删除成功(true)/删除失败(false)
     */
    boolean batchDelete(String deleteIds);

    /**
     * 分页查询新闻的评论
     * @param id 新闻id
     * @return PageVo<Comment>
     */
    PageVo<Comment> pageQueryByNewsId(Integer id);
}
