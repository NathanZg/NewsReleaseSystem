package com.news;

import com.news.controller.TypeController;
import com.news.entity.Type;
import com.news.mapper.TypeMapper;
import com.news.service.TypeService;
import com.news.utils.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
@SpringBootTest
public class TypeTest {
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private TypeController typeController;
    @Autowired
    private TypeService typeService;
    @Test
    void getAll(){
        List<Type> types = typeMapper.selectList(null);
        System.out.println(types);
    }
    @Test
    void insertType(){
        Type type =new Type(3,"玄幻");
        int insert = typeMapper.insert(type);
        System.out.println(insert);
    }
    @Test
    void deleteType(){
        String s="3";
        int i = typeMapper.deleteById(s);
        System.out.println(i);
    }

    //测试TypeController
    @Test
    void demoCoIN(){
        Type type=new Type(1,"灵异");
        Response<Object> objectResponse = typeController.typeAdd(type);
        System.out.println(objectResponse);
    }
//    @Test
//    void demoST(){
//        List<Type> types = typeController.typeSelect();
//        System.out.println(types);
//    }
    @Test
    void demoDT(){
        String s="1";
        Response<Object> objectResponse = typeController.typeDelete(s);
        System.out.println(objectResponse);
    }
    @Test
    void demo(){
        String type = "的撒旦";
        Integer idByType = typeService.getIdByType(type);
        System.out.println(idByType);
    }
}
