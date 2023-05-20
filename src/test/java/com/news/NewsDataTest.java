package com.news;

import com.news.controller.NewsDataController;
import com.news.controller.TypeController;
import com.news.entity.NewsData;
import com.news.entity.Type;
import com.news.mapper.NewsDataMapper;
import com.news.service.NewsDataService;
import com.news.utils.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@SpringBootTest
public class NewsDataTest {
    @Autowired
    private NewsDataMapper newsDataMapper;
    @Autowired
    private NewsDataService newsDataService;
    @Autowired
    private NewsDataController newsDataController;
    @Test
    void getAllData(){
        List<NewsData> newsData = newsDataMapper.selectList(null);
        System.out.println(newsData);
    }
    @Test
    void getData(){
        NewsData newsData = newsDataMapper.selectById(1);
        System.out.println(newsData);
    }
    @Test
    void insertData(){
        NewsData newsData =new NewsData(3,"数学","回到海口海口的话","无关", LocalDateTime.now());
        int insert = newsDataMapper.insert(newsData);
        System.out.println(insert);
    }
    @Test
    void updateData(){
        NewsData newsData =new NewsData(3,"语文","回到海口海口的话","无关", LocalDateTime.now());
        int i = newsDataMapper.updateById(newsData);
        System.out.println(i);
    }
    @Test
    void deleteData(){
        String ids="1,2,3";
        String[] id = ids.split(",");
        ArrayList<Integer> ID = new ArrayList<>();
        for(String s:id){
            ID.add(Integer.parseInt(s));
        }
        //newsTypeService.deleteNT(ID);
        int i = newsDataMapper.deleteBatchIds(ID);
        System.out.println(i);
    }
    @Test
    void demo(){
        NewsData data = newsDataService.getData(1);
        System.out.println(data);
    }
    @Test
    void getALL(){
        Type type=new Type(1,"科幻");
        //Response<Object> objectResponse = newsDataController.selectALL(type.getId());
        //System.out.println(objectResponse);
        List<NewsData> all = newsDataMapper.getALL(type.getId());
        System.out.println(all);
    }
}
