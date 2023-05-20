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

    //查询全部
    public List<Comment> getAll();

    //添加
    public boolean insert(Comment comment);

    //根据Id修改
    public boolean updateId(Comment comment);

    //根据Id删除
    public boolean delete(Integer id);

    //批量删除
    public boolean batch_delete(List<Integer> idList);
}
