package com.news;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.news.entity.NewsType;
import com.news.mapper.NewsTypeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
@SpringBootTest
public class NewsTypeTest {
    @Autowired
    private NewsTypeMapper newsTypeMapper;

    @Test
    void insertNT(){
        NewsType news = new NewsType(3,2);
        int insert = newsTypeMapper.insert(news);
        System.out.println(insert);
    }

    @Test
    void getAll(){
        List<NewsType> list = newsTypeMapper.selectList(null);
        System.out.println(list);
    }
    @Test
    void deleteNT(){
        ArrayList<Integer> ids=new ArrayList<>();
        ids.add(3);
        int i = newsTypeMapper.deleteBatchIds(ids);
        System.out.println(i);
    }
}
