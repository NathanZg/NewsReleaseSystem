package com.news.service.impl;

import com.news.entity.NewsData;
import com.news.mapper.NewsDataMapper;
import com.news.service.NewsDataService;
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
public class NewsDataServiceImpl extends ServiceImpl<NewsDataMapper, NewsData> implements NewsDataService {
    private final NewsDataMapper newsDataMapper;

    public NewsDataServiceImpl(NewsDataMapper newsDataMapper) {
        this.newsDataMapper = newsDataMapper;
    }

    /**
     * 查找新闻信息表
     * @return list<NewsData>集合
     */
    @Override
    public List<NewsData> getAllData(){
        return newsDataMapper.selectList(null);
    }

    /**
     * 查找单个新闻信息
     * @param id 新闻ID
     * @return NewData对象
     */
    @Override
    public NewsData getData(Integer id){
        return newsDataMapper.selectById(id);
    }

    /**
     * 添加新闻信息
     * @param newsData 新闻实例
     * @return 添加成功(true)/添加失败(false)
     */
    @Override
    public boolean insertData(NewsData newsData){
        return newsDataMapper.insert(newsData)>=1;
    }

    /**
     * 修改新闻信息
     * @param newsData 新闻实例
     * @return 修改成功(true)/修改失败(false)
     */
    @Override
    public boolean updateData(NewsData newsData){
        return newsDataMapper.updateById(newsData)>=1;

    }

    /**
     * 删除新闻信息
     * @param ids id集合
     * @return 删除成功(true)/删除失败(false)
     */
    @Override
    public boolean deleteData(String ids){
        if(ids==null) return false;
        String[] id = ids.split(",");
        ArrayList<Integer> ID = new ArrayList<>();
        for(String s:id){
            ID.add(Integer.parseInt(s));
        }
        return newsDataMapper.deleteBatchIds(ID)>=1;
    }
}
