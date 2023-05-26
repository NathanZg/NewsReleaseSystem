package com.news.service.impl;

import com.news.entity.Type;
import com.news.mapper.TypeMapper;
import com.news.service.TypeService;
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
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {
    private TypeMapper typeMapper;

    public TypeServiceImpl(TypeMapper typeMapper) {
        this.typeMapper = typeMapper;
    }

    /**
     * 查找类型
     * @return 查找成功/失败
     */
    @Override
    public List<Type> getAll(){
        return typeMapper.selectList(null);
    }

    /**
     * 根据ID查找类型
     * @param id 类型ID
     * @return type
     */
    @Override
    public Type getOne(Integer id){
        return typeMapper.selectById(id);
    }
    /**
     * 添加类型
     * @param type 类型
     * @return 添加成功/失败
     */
    @Override
    public boolean insertType(Type type){
        return typeMapper.insert(type)>=1;
    }

    /**
     * 删除类型
     * @param id 类型ID
     * @return 删除成功/失败
     */
    @Override
    public boolean deleteType(String id){
        String[] split = id.split(",");
        ArrayList<Integer> ID = new ArrayList<>();
        for(String s:split){
            ID.add(Integer.parseInt(s));
        }
        return typeMapper.deleteBatchIds(ID)>=1;
    }


}
