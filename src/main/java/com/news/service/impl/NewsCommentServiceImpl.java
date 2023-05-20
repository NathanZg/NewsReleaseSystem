package com.news.service.impl;

import com.news.entity.NewsComment;
import com.news.mapper.NewsCommentMapper;
import com.news.service.NewsCommentService;
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
public class NewsCommentServiceImpl extends ServiceImpl<NewsCommentMapper, NewsComment> implements NewsCommentService {
    private NewsCommentMapper newsCommentMapper;

    public NewsCommentServiceImpl(NewsCommentMapper newsCommentMapper) {
        this.newsCommentMapper = newsCommentMapper;
    }

    @Override
    public boolean insert(NewsComment newsComment) {
        return newsCommentMapper.insert(newsComment)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return newsCommentMapper.deleteById(id)>0;
    }

    @Override
    public List<NewsComment> getAll() {
        List<NewsComment> newsCommentList=newsCommentMapper.selectList(null);
        return newsCommentList;
    }

    @Override
    public boolean batch_delete(List<Integer> idList) {
        return newsCommentMapper.deleteBatchIds(idList)>0;
    }
}
