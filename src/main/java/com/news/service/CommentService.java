package com.news.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.news.entity.Comment;
import com.news.entity.vo.CommentQueryVo;
import com.news.entity.vo.PageVo;
import com.news.entity.vo.QueryVo;

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
     * 添加
     * @param comment 评论信息
     * @return 删除成功(true)/删除失败(false)
     */
    boolean insert(Comment comment);


    /**
     * 批量删除
     * @param deleteIds 评论id列
     * @return 删除成功(true)/删除失败(false)
     */
    boolean batchDelete(String deleteIds);

    /**
     * 根据新闻id分页查询新闻的评论
     * @param id 新闻id
     * @return PageVo<Comment>
     */
    PageVo<Comment> pageQueryByNewsId(Integer id);

    /**
     * 分页条件查询评论
     * @param queryVo
     * @return
     */
    PageVo<Comment> pageQueryByCondition(CommentQueryVo queryVo);
}
