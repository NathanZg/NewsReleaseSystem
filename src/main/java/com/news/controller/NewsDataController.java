package com.news.controller;

import com.news.entity.NewsData;
import com.news.entity.vo.PageVo;
import com.news.entity.vo.QueryVo;
import com.news.service.NewsDataService;
import com.news.utils.Response;
import com.news.utils.ResponseUtils;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api")
public class NewsDataController {
    private NewsDataService newsDataService;
    public NewsDataController(NewsDataService newsDataService) {
        this.newsDataService = newsDataService;
    }
    @PostMapping("/newsSelect")
    public Response<List<NewsData>> newsSelect() {
        List<NewsData> allData = newsDataService.getAllData();
        return ResponseUtils.success(allData);
    }
    @PostMapping("/newsAdd")
    public Response<Object> newsAdd(@RequestBody NewsData newsData) {
        if(newsDataService.insertData(newsData)){
            return ResponseUtils.success("Add newsData successfully!");
        }
        else{
            return ResponseUtils.fail("Failed to Add newsData !");
        }
    }
    @PostMapping("/newsUpdate")
    public Response<Object> newsUpdate(@RequestBody NewsData newsData) {
        if(newsDataService.updateData(newsData)){
            return ResponseUtils.success("Update newsData successfully!");
        }
        else{
            return ResponseUtils.fail("Failed to Update newsData !");
        }
    }
    @DeleteMapping("/newsDelete")
    public Response<Object> newsDelete(@RequestBody String ids) {
        if(newsDataService.deleteData(ids)){
            return ResponseUtils.success("Delete newsData successfully!");
        }
        else{
            return ResponseUtils.fail("Failed to Delete newsData !");
        }
    }

    @PostMapping("/pageQueryByCondition")
    public Response<PageVo<NewsData>> pageQueryByCondition(@RequestBody QueryVo queryVo){
        PageVo<NewsData> newsDataPageVo=newsDataService.pageQueryByCondition(queryVo);
        boolean flag= newsDataPageVo != null;
        if(flag){
            return ResponseUtils.success(newsDataPageVo);
        }
        else {
            return ResponseUtils.fail("pageQueryByCondition fail!!!");
        }
    }

}
