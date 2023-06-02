package com.news.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.news.constants.PageConstant;
import com.news.entity.Comment;
import com.news.entity.vo.CommentQueryVo;
import com.news.entity.vo.PageVo;
import com.news.mapper.CommentMapper;
import com.news.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean insert(Comment comment) {
        LocalDateTime commentDate = comment.getCommentDate();
        if (commentDate == null) {
            comment.setCommentDate(LocalDateTime.now());
        }
        return commentMapper.insert(comment)>0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean batchDelete(String deleteIds) {
        if (deleteIds == null || "".equals(deleteIds)) {
            return false;
        }
        String[] ids = deleteIds.split(",");
        List<Integer> idList = new ArrayList<>();
        for (String id : ids) {
            try {
                idList.add(Integer.valueOf(id));
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return commentMapper.deleteBatchIds(idList)>0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public PageVo<Comment> pageQueryByNewsId(Integer id) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("news_id", id);
        Page<Comment> page = new Page<>(PageConstant.CURRENT, PageConstant.SIZE);
        commentMapper.selectPage(page, wrapper);
        PageVo<Comment> commentPageVo = new PageVo<>();
        BeanUtils.copyProperties(page, commentPageVo);
        commentPageVo.setPages(page.getPages());
        return commentPageVo;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public PageVo<Comment> pageQueryByCondition(CommentQueryVo queryVo) {
        if (queryVo == null) {
            return null;
        }
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        Integer id = queryVo.getId();
        if (id != null) {
            queryWrapper.eq("id", id);
        }
        Integer newsId = queryVo.getNewsId();
        if (newsId != null) {
            queryWrapper.eq("news_id", newsId);
        }
        String user = queryVo.getUser();
        if (user != null) {
            queryWrapper.like("user", user);
        }
        String commentData = queryVo.getCommentData();
        if (commentData != null) {
            queryWrapper.like("comment_data", commentData);
        }
        LocalDateTime startDate = queryVo.getStartDate();
        if (startDate != null) {
            queryWrapper.ge("creation_date", startDate);
        }
        LocalDateTime endDate = queryVo.getEndDate();
        if (endDate != null) {
            queryWrapper.le("creation_date", endDate);
        }
        Long current = queryVo.getCurrent();
        if (current == null) {
            current = PageConstant.CURRENT;
        }
        Long size = queryVo.getSize();
        if (size == null) {
            size = PageConstant.SIZE;
        }
        Page<Comment> page = new Page<>(current, size);
        commentMapper.selectPage(page, queryWrapper);
        PageVo<Comment> newPageVo = new PageVo<>();
        BeanUtils.copyProperties(page, newPageVo);
        newPageVo.setPages(page.getPages());
        return newPageVo;
    }


}
