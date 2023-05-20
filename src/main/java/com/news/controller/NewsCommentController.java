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
@RequestMapping("/newsComment")
public class NewsCommentController {
    private NewsCommentService newsCommentService;

    public NewsCommentController(NewsCommentService newsCommentService) {
        this.newsCommentService = newsCommentService;
    }

    //添加数据
    @PostMapping
    public Response<NewsComment> insert(@RequestBody NewsComment newsComment){
        boolean flag=newsCommentService.insert(newsComment);
        ResponseUtils responseUtils=new ResponseUtils();
//        System.out.println("插入！！");
        if(flag)
            return responseUtils.success(newsComment);
        else
            return responseUtils.fail("insert fail!!!");
    }

    //查看数据
    @GetMapping
    public Response getAll(){
        List<NewsComment> commentList=newsCommentService.getAll();
        boolean flag=commentList!=null?true:false;
        ResponseUtils responseUtils=new ResponseUtils();
//        System.out.println("查询全部");
        if(flag)
            return responseUtils.success("all success!!!");
        else
            return responseUtils.fail("some fail!!!");
    }

    //删除数据
    @DeleteMapping("{id}")
    public Response delete(@PathVariable Integer id){
        boolean flag=newsCommentService.delete(id);
        ResponseUtils responseUtils=new ResponseUtils();
//        System.out.println("删除！！");
        if(flag)
            return responseUtils.success("delete success!!!");
        else
            return responseUtils.fail("delete fail!!!");
    }


    //批量删除
    @PostMapping("/batch_delete")
    public Response batch_delete(@RequestBody List<Integer>list){
        boolean flag=newsCommentService.batch_delete(list);
        ResponseUtils responseUtils=new ResponseUtils();
        System.out.println("批量删除！！");
        if(flag)
            return responseUtils.success("batch_delete success!!!");
        else
            return responseUtils.fail("batch_delete fail!!!");
    }
}
