package com.news.service;

import com.news.entity.Type;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface TypeService extends IService<Type> {
    /**
     * 查找类型
     * @return 查找成功/失败
     */
    List<Type> getAll();

    /**
     * 根据ID查找类型
     * @param id 类型ID
     * @return type
     */
    Type getOne(Integer id);

    /**
     * 根据类型查找id
     * @param type 类型名
     * @return id
     */
    Integer getIdByType(String type);
    /**
     * 添加类型
     * @param type 类型
     * @return 添加成功/失败
     */
    boolean insertType(Type type);
    /**
     * 删除类型
     * @param id 类型ID
     * @return 删除成功/失败
     */
    boolean deleteType(String id);


}
