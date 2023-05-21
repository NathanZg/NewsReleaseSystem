package com.news.controller;

import com.news.entity.Comment;
import com.news.service.CommentService;
import com.news.service.NewsCommentService;
import com.news.utils.Response;
import com.news.utils.ResponseUtils;
import org.springframework.context.annotation.Configuration;
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
@RequestMapping("/api/comment")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //添加数据
    @PostMapping
    public Response<Comment> insert(@RequestBody Comment comment){
        boolean flag=commentService.insert(comment);
        if(flag)
            return ResponseUtils.success(comment);
        else
            return ResponseUtils.fail("insert fail!!!");
    }

    //查看数据
    @GetMapping
    public Response getAll(){
        List<Comment> commentList=commentService.getAll();
        boolean flag=commentList!=null?true:false;
        if(flag)
            return ResponseUtils.success(commentList);
        else
            return ResponseUtils.fail("getAll fail!!!");
    }

    //删除数据
    @DeleteMapping("{id}")
    public Response delete(@PathVariable Integer id){
        boolean flag=commentService.delete(id);
        if(flag)
            return ResponseUtils.success("delete success!!!");
        else
            return ResponseUtils.fail("delete fail!!!");
    }

    //根据id修改数据
    @PutMapping
    public Response update(@RequestBody Comment comment){
        boolean flag=commentService.updateId(comment);
        if(flag)
            return ResponseUtils.success(comment);
        else
            return ResponseUtils.fail("update fail!!!");
    }

    //批量删除
    @DeleteMapping("/batch_delete")
    public Response batch_delete(@RequestBody String Ids){
        boolean flag=commentService.batch_delete(Ids);
        if(flag)
            return ResponseUtils.success("batch_delete success!!!");
        else
            return ResponseUtils.fail("batch_delete fail!!!");
    }
}
