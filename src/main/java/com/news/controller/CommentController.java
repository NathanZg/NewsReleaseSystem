package com.news.controller;

import com.news.entity.Comment;
import com.news.entity.NewsData;
import com.news.entity.vo.CommentQueryVo;
import com.news.entity.vo.PageVo;
import com.news.entity.vo.QueryVo;
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
            return ResponseUtils.success("insert successfully！");
        }
        else {
            return ResponseUtils.fail("insert fail!!!");
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

    /**
     * 分页查询
     * @param queryVo
     * @return
     */
    @PostMapping("/pageQueryByCondition")
    public Response<PageVo<Comment>> pageQueryByCondition(@RequestBody CommentQueryVo queryVo){
        PageVo<Comment> newsDataPageVo = commentService.pageQueryByCondition(queryVo);
        boolean flag= newsDataPageVo != null;
        if(flag){
            return ResponseUtils.success(newsDataPageVo);
        }
        else {
            return ResponseUtils.fail("pageQueryByCondition fail!!!");
        }
    }
}
