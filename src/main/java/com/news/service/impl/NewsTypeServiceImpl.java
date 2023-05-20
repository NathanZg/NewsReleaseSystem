package com.news.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.news.entity.NewsType;
import com.news.mapper.NewsTypeMapper;
import com.news.service.NewsTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Sancean
 * @since 2023-05-19
 */
@Service
public class NewsTypeServiceImpl extends ServiceImpl<NewsTypeMapper, NewsType> implements NewsTypeService {
    private NewsTypeMapper newsTypeMapper;

    public NewsTypeServiceImpl(NewsTypeMapper newsTypeMapper) {
        this.newsTypeMapper = newsTypeMapper;
    }


    /**
     * 查询新闻类型表
     * @return list<NewType>集合
     */
    @Override
    public List<NewsType> getAll(){
        return newsTypeMapper.selectList(null);
    }

    /**
     * 添加新闻类型
     * @param newsType 新闻类型
     * @return true/false
     */
    @Override
    public boolean insertNT(NewsType newsType){
        return newsTypeMapper.insert(newsType)>=1;
    }

    /**
     * 删除新闻类型
     * @param ids id集合
     * @return 删除成功(true)/删除失败(false)
     */
    @Override
    public boolean deleteNT(ArrayList<Integer> ids){
        if(ids==null){
            return false;
        }
        return newsTypeMapper.deleteBatchIds(ids)>=1;
    }
}
