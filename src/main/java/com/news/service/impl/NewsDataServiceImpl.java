package com.news.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.news.constants.PageConstant;
import com.news.entity.NewsData;
import com.news.entity.Type;
import com.news.entity.vo.NewsType;
import com.news.entity.vo.PageVo;
import com.news.entity.vo.QueryVo;
import com.news.mapper.NewsDataMapper;
import com.news.service.NewsDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.news.service.TypeService;
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
    private TypeService typeService;
    public NewsDataServiceImpl(NewsDataMapper newsDataMapper, TypeService typeService) {
        this.newsDataMapper = newsDataMapper;
        this.typeService = typeService;
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
        Integer id = queryVo.getId();
        if (id != null) {
            queryWrapper.eq("id", id);
        }
        String type = queryVo.getType();
        if(type!=null){
            Integer idByType = typeService.getIdByType(type);
            if (idByType != null) {
                queryWrapper.eq("type_id",idByType);
            }
        }
        else{
            Integer typeId = queryVo.getTypeId();
            if (typeId != null) {
                queryWrapper.eq("type_id", typeId);
            }
        }
        String title = queryVo.getTitle();
        if (title != null) {
            queryWrapper.like("title", title);
        }
        String data = queryVo.getData();
        if (data != null) {
            queryWrapper.like("data", data);
        }
        String publisher = queryVo.getPublisher();
        if (publisher != null) {
            queryWrapper.like("publisher", publisher);
        }
        LocalDateTime startDate = queryVo.getStartDate();
        if (startDate != null) {
            queryWrapper.ge("data", startDate);
        }
        LocalDateTime endDate = queryVo.getEndDate();
        if (endDate != null) {
            queryWrapper.le("data", endDate);
        }
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
    public PageVo<NewsType> pageQueryByCondition01(QueryVo queryVo){
        PageVo<NewsType> objectPageVo = new PageVo<>();
        PageVo<NewsData> newsDataPageVo = pageQueryByCondition(queryVo);
        BeanUtils.copyProperties(newsDataPageVo, objectPageVo);
        List<NewsData> records = newsDataPageVo.getRecords();
        ArrayList<NewsType> list = new ArrayList<>();
        for(NewsData newsData:records){
            Type one = typeService.getOne(newsData.getTypeId());
            NewsType newsType = new NewsType();
            BeanUtils.copyProperties(newsData, newsType);
            newsType.setType(one.getType());
            list.add(newsType);
        }
        objectPageVo.setRecords(list);
        return objectPageVo;
    }

}
