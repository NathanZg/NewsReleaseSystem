package com.news.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.news.entity.NewsData;
import com.news.entity.Type;
import com.news.mapper.NewsDataMapper;
import com.news.mapper.TypeMapper;
import com.news.service.NewsDataService;
import com.news.service.TypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {
    private TypeMapper typeMapper;

    private NewsDataMapper newsDataMapper;

    public TypeServiceImpl(TypeMapper typeMapper, NewsDataMapper newsDataMapper) {
        this.typeMapper = typeMapper;
        this.newsDataMapper = newsDataMapper;
    }

    /**
     * 查找类型
     * @return 查找成功/失败
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Type> getAll(){
        return typeMapper.selectList(null);
    }

    /**
     * 根据ID查找类型
     * @param id 类型ID
     * @return type
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Type getOne(Integer id){
        return typeMapper.selectById(id);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Integer getIdByType(String type){
        QueryWrapper<Type> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type",type);
        Type type1 = typeMapper.selectOne(queryWrapper);
        return type1.getId();
    }
    /**
     * 添加类型
     * @param type 类型
     * @return 添加成功/失败
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean insertType(Type type){
        return typeMapper.insert(type)>=1;
    }

    /**
     * 删除类型
     * @param id 类型ID
     * @return 删除成功/失败
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean deleteType(String id){
        String[] split = id.split(",");
        ArrayList<Integer> typeIds = new ArrayList<>();
        for(String s:split){
            int typeId = Integer.parseInt(s);
            QueryWrapper<NewsData> wrapper = new QueryWrapper<>();
            wrapper.eq("type_id", typeId);
            newsDataMapper.delete(wrapper);
            typeIds.add(typeId);
        }
        return typeMapper.deleteBatchIds(typeIds)>=1;
    }
}
