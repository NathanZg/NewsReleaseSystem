package com.news.controller;

import com.news.entity.Comment;
import com.news.service.CommentService;
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
@RequestMapping("/api/comment")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * 新增评论
     * @param comment 评论实体类
     * @return Response<Comment>
     */
    @PostMapping
    public Response<Comment> insert(@RequestBody Comment comment){
        boolean flag=commentService.insert(comment);
        if(flag){
            return ResponseUtils.success(comment);
        }
        else {
            return ResponseUtils.fail("insert fail!!!");
        }
    }

    /**
     * 获取新闻对应所有评论
     * @return Response<List<Comment>>
     */
    @GetMapping
    public Response<List<Comment>> getAll(){
        List<Comment> commentList=commentService.getAll();
        boolean flag= commentList != null;
        if(flag) {
            return ResponseUtils.success(commentList);
        }
        else {
            return ResponseUtils.fail("getAll fail!!!");
        }
    }

    /**
     * 删除评论
     * @param id 评论id
     * @return Response
     */
    @DeleteMapping("{id}")
    public Response delete(@PathVariable Integer id){
        boolean flag=commentService.delete(id);
        if(flag) {
            return ResponseUtils.success("delete success!!!");
        }
        else {
            return ResponseUtils.fail("delete fail!!!");
        }
    }

    /**
     * 修改评论
     * @param comment 评论实体类
     * @return Response
     */
    @PutMapping
    public Response update(@RequestBody Comment comment){
        boolean flag=commentService.updateId(comment);
        if(flag) {
            return ResponseUtils.success(comment);
        }
        else {
            return ResponseUtils.fail("update fail!!!");
        }
    }

    /**
     * 批量删除
     * @param ids 以逗号分割的id序列
     * @return Response
     */
    @DeleteMapping("/batchDelete")
    public Response batchDelete(@RequestBody String ids){
        boolean flag=commentService.batchDelete(ids);
        if(flag) {
            return ResponseUtils.success("batchDelete success!!!");
        }
        else {
            return ResponseUtils.fail("batchDelete fail!!!");
        }
    }
}
