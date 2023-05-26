package com.news.controller;

import com.news.entity.Comment;
import com.news.entity.NewsData;
import com.news.entity.vo.NewsDetail;
import com.news.entity.vo.NewsType;
import com.news.entity.vo.PageVo;
import com.news.entity.vo.QueryVo;
import com.news.service.CommentService;
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
    private CommentService commentService;

    public NewsDataController(NewsDataService newsDataService, CommentService commentService) {
        this.newsDataService = newsDataService;
        this.commentService = commentService;
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
    @PostMapping("/pageQueryByCondition01")
    public Response<PageVo<NewsType>> pageQueryByCondition01(@RequestBody QueryVo queryVo){
        PageVo<NewsType> newsTypePageVo = newsDataService.pageQueryByCondition01(queryVo);
        boolean flag= newsTypePageVo !=null;
        if(flag){
            return ResponseUtils.success(newsTypePageVo);
        }
        else {
            return ResponseUtils.fail("pageQueryByCondition01 fail!!!");
        }
    }


    @GetMapping("/newsDetail/{id}")
    public Response<NewsDetail> getNewsDetailById(@PathVariable Integer id){
        PageVo<Comment> commentPageVo = commentService.pageQueryByNewsId(id);
        NewsData data = newsDataService.getData(id);
        NewsDetail newsDetail = new NewsDetail(data, commentPageVo);
        boolean flag= data != null;
        if(flag) {
            return ResponseUtils.success(newsDetail);
        }
        else {
            return ResponseUtils.fail("get newsDetail by newsId fail!!!");
        }
    }
}
