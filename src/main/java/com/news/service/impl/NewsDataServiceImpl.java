package com.news.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.news.constants.PageConstant;
import com.news.entity.NewsData;
import com.news.entity.vo.PageVo;
import com.news.entity.vo.QueryVo;
import com.news.mapper.NewsDataMapper;
import com.news.service.NewsDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        if(ids==null) {
            return false;
        }
        String[] id = ids.split(",");
        ArrayList<Integer> idList = new ArrayList<>();
        for(String s:id){
            idList.add(Integer.parseInt(s));
        }
        return newsDataMapper.deleteBatchIds(idList)>=1;
    }

    /**
     * 分页条件查询新闻信息
     * @param queryVo 查询条件（起始页面和页面容量）
     * @return PageVo 前端页面封装
     */
    @Override
    public PageVo<NewsData> pageQueryByCondition(QueryVo queryVo) {
        if (queryVo == null) {
            return null;
        }
        QueryWrapper<NewsData> queryWrapper = new QueryWrapper<>();
        Long current = queryVo.getCurrent();
        if (current == null) {
            current = PageConstant.CURRENT;
        }
        Long size = queryVo.getSize();
        if (size == null) {
            size = PageConstant.SIZE;
        }
        Page<NewsData> page = new Page<>(current, size);
        PageVo<NewsData> newPageVo = new PageVo<>();
        newsDataMapper.selectPage(page,queryWrapper);
        BeanUtils.copyProperties(page, newPageVo);
        newPageVo.setPages(page.getPages());
        return newPageVo;
    }

    @Override
    public List<NewsData> getAllNewsByType(Integer id) {
        if (id == null) {
            return null;
        }
        return newsDataMapper.getAllNewsByType(id);
    }
}
