package com.news.controller;

import com.news.entity.Comment;
import com.news.entity.NewsComment;
import com.news.service.NewsCommentService;
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
 * @since 2023-05-18
 */
@RestController
@RequestMapping("/api/newsComment")
public class NewsCommentController {
    private NewsCommentService newsCommentService;

    public NewsCommentController(NewsCommentService newsCommentService) {
        this.newsCommentService = newsCommentService;
    }

    /**
     * 添加新闻与评论的关联信息
     * @param newsComment 关联实体类
     * @return
     */
    @PostMapping
    public Response insert(@RequestBody NewsComment newsComment){
        boolean flag=newsCommentService.insert(newsComment);
        if(flag) {
            return ResponseUtils.success("insert success!");
        }
        else {
            return ResponseUtils.fail("insert fail!");
        }
    }

    /**
     * 查询所有关联数据
     * @return Response
     */
    @GetMapping
    public Response getAll(){
        List<NewsComment> newsCommentList=newsCommentService.getAll();
        boolean flag= newsCommentList != null;
        if(flag) {
            return ResponseUtils.success("get all NewsComment data success!!!");
        }
        else {
            return ResponseUtils.fail("get all NewsComment data fail!!!");
        }
    }

    /**
     * 查询指定新闻的评论
     * @param id 新闻id
     * @return
     */
    @GetMapping("/{id}")
    public Response<List<Comment>> getCommentByNews(@PathVariable Integer id){
        List<Comment> commentList=newsCommentService.getCommentByNews(id);
        boolean flag= commentList != null;
        if(flag) {
            return ResponseUtils.success(commentList);
        }
        else {
            return ResponseUtils.fail("get comments by newsId fail!!!");
        }
    }

    /**
     * 删除关联信息
     * @param id 评论id
     * @return Response
     */
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Integer id){
        boolean flag=newsCommentService.delete(id);
        if(flag) {
            return ResponseUtils.success("delete success!!!");
        }
        else {
            return ResponseUtils.fail("delete fail!!!");
        }
    }


    /**
     * 批量删除关联信息
     * @param ids 以逗号分割的id序列
     * @return Response
     */
    @DeleteMapping("/batchDelete")
    public Response batchDelete(@RequestBody String ids){
        boolean flag=newsCommentService.batchDelete(ids);
        if(flag) {
            return ResponseUtils.success("batchDelete success!!!");
        }
        else {
            return ResponseUtils.fail("batchDelete fail!!!");
        }
    }
}
