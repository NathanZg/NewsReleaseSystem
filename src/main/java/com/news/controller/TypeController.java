package com.news.controller;

import com.news.entity.Type;
import com.news.service.TypeService;
import com.news.utils.Response;
import com.news.utils.ResponseUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 */
@RestController
@RequestMapping("/api")
public class TypeController {
    private TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    /**
     * 查询类型
     * @return List集合
     */
    @PostMapping("/typeSelect")
    public Response<List<Type>> typeSelect() {
        List<Type> all = typeService.getAll();
        return ResponseUtils.success(all);
    }
    /**
     * 添加类型
     * @param type 类型
     * @return 成功/失败
     */
    @PostMapping("/typeAdd")
    public Response<Object> typeAdd(@RequestBody Type type) {
        if (typeService.insertType(type)) {
            return ResponseUtils.success("Add Type successfully!");
        } else {
            return ResponseUtils.fail("Failed to Add Type!");
        }
    }

    /**
     * 删除类型
     * @param id 类型ID
     * @return 成功/失败
     */
    @DeleteMapping("/typeDelete")
    public Response<Object> typeDelete(@RequestBody String id) {
        if (typeService.deleteType(id)) {
            return ResponseUtils.success("Delete Type successfully!");
        } else {
            return ResponseUtils.fail("Failed to Delete Type!");
        }
    }
}
