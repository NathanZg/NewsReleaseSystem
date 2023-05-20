package com.news.service;

import com.news.entity.NewsType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface NewsTypeService extends IService<NewsType> {
    /**
     * 查询新闻类型表
     * @return list<NewType>集合
     */
    List<NewsType> getAll();

    /**
     * 添加新闻类型
     * @param newsType 新闻类型
     * @return true/false
     */
    boolean insertNT(NewsType newsType);
    /**
     * 删除新闻类型
     * @param ids id集合
     * @return 删除成功(true)/删除失败(false)
     */
    boolean deleteNT(ArrayList<Integer> ids);
}
