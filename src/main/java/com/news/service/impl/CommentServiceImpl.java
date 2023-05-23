package com.news.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.news.constants.PageConstant;
import com.news.entity.Comment;
import com.news.entity.vo.PageVo;
import com.news.mapper.CommentMapper;
import com.news.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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


}
