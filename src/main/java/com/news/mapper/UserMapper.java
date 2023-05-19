package com.news.mapper;

import com.news.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Sancean
 * @since 2023-05-19
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
