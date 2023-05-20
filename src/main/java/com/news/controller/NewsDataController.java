package com.news.controller;

import com.news.entity.NewsData;
import com.news.service.NewsDataService;
import com.news.utils.Response;
import com.news.utils.ResponseUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Sancean
 * @since 2023-05-19
 */
@RestController
@RequestMapping("/news/api")
public class NewsDataController {
    private NewsDataService newsDataService;

    public NewsDataController(NewsDataService newsDataService) {
        this.newsDataService = newsDataService;
    }
    @PostMapping("/newsSelect")
    public List<NewsData> newsSelect() {
        return newsDataService.getAllData();
    }
    @PostMapping("/newsAdd")
    public Response<Object> newsAdd(NewsData newsData) {
        if(newsDataService.insertData(newsData)){
            return ResponseUtils.success("Add newsData successfully!");
        }
        else{
            return ResponseUtils.fail("Failed to Add newsData !");
        }
    }
    @PostMapping("/newsUpdate")
    public Response<Object> newsUpdate(NewsData newsData) {
        if(newsDataService.updateData(newsData)){
            return ResponseUtils.success("Update newsData successfully!");
        }
        else{
            return ResponseUtils.fail("Failed to Update newsData !");
        }
    }
    @PostMapping("/newsDelete")
    public Response<Object> newsDelete(String ids) {
        if(newsDataService.deleteData(ids)){
            return ResponseUtils.success("Delete newsData successfully!");
        }
        else{
            return ResponseUtils.fail("Failed to Delete newsData !");
        }
    }
}
