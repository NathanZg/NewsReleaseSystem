package com.news.mapper;

import com.news.entity.NewsData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.news.entity.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 *  @author Sancean
 *  @since 2023-05-19
 */
@Mapper
public interface NewsDataMapper extends BaseMapper<NewsData> {
    /**
     * 根据类型查所属新闻
     * @param id 类型ID
     * @return list<NewsData>集合
     */
    @Select("select * from news_data where id in(select news_id from news_type where type_id=#{id})")
    List<NewsData> getAllNewsByType(Integer id);
}
