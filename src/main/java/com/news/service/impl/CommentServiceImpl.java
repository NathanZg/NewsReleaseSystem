package com.news.service.impl;

import com.news.entity.Comment;
import com.news.mapper.CommentMapper;
import com.news.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Sancean
 * @since 2023-05-18
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    private CommentMapper commentMapper;

    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public List<Comment> getAll(){
        List<Comment> commentList=commentMapper.selectList(null);
        return commentList;
    }

    @Override
    public boolean insert(Comment comment) {
        return commentMapper.insert(comment)>0;
    }

    @Override
    public boolean updateId(Comment comment) {
        return commentMapper.updateById(comment)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return commentMapper.deleteById(id)>0;
    }

    @Override
    public boolean batch_delete(List<Integer> idList) {
        return commentMapper.deleteBatchIds(idList)>0;
    }


}
