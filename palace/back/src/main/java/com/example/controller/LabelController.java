package com.example.controller;

import com.example.common.Result;
import com.example.entity.Category;
import com.example.entity.Label;
import com.example.service.CategoryService;
import com.example.service.LabelService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 标签前端操作接口
 **/
@RestController
@RequestMapping("/label")
public class LabelController {

    @Resource
    private LabelService labelService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Label label) {
        labelService.add(label);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        labelService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        labelService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Label label) {
        labelService.updateById(label);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Label label = labelService.selectById(id);
        return Result.success(label);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Label label) {
        List<Label> list = labelService.selectAll(label);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Label label,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Label> page = labelService.selectPage(label, pageNum, pageSize);
        return Result.success(page);
    }

}