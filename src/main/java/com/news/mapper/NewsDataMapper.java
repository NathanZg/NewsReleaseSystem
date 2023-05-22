package com.news.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.news.entity.NewsData;
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

}
