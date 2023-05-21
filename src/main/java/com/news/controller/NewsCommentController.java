package com.news.controller;

import com.news.entity.Comment;
import com.news.entity.NewsComment;
import com.news.service.CommentService;
import com.news.service.NewsCommentService;
import com.news.utils.Response;
import com.news.utils.ResponseUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

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

    //添加数据
    @PostMapping
    public Response<NewsComment> insert(@RequestBody NewsComment newsComment){
        boolean flag=newsCommentService.insert(newsComment);
        if(flag)
            return ResponseUtils.success(newsComment);
        else
            return ResponseUtils.fail("insert fail!!!");
    }

    //查看全部数据
    @GetMapping
    public Response getAll(){
        List<NewsComment> commentList=newsCommentService.getAll();
        boolean flag=commentList!=null?true:false;
        if(flag)
            return ResponseUtils.success("all success!!!");
        else
            return ResponseUtils.fail("some fail!!!");
    }

    //查询指定新闻的评论
    @GetMapping("/{id}")
    public Response getCommentByNews(@PathVariable Integer id){
        List<Comment> commentList=newsCommentService.getCommentByNews(id);
        boolean flag=commentList!=null?true:false;
        if(flag)
            return ResponseUtils.success(commentList);
        else
            return ResponseUtils.fail("some fail!!!");
    }

    //删除数据
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Integer id){
        boolean flag=newsCommentService.delete(id);
        if(flag)
            return ResponseUtils.success("delete success!!!");
        else
            return ResponseUtils.fail("delete fail!!!");
    }


    //批量删除
    @DeleteMapping("/batch_delete")
    public Response batch_delete(@RequestBody String ids){
        boolean flag=newsCommentService.batch_delete(ids);
        if(flag)
            return ResponseUtils.success("batch_delete success!!!");
        else
            return ResponseUtils.fail("batch_delete fail!!!");
    }
}
