package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yeming
 * @date 2019/7/19 22:08
 */
@RestController
@RequestMapping("label")
@CrossOrigin
public class LabelController {

    @Autowired
    private LabelService labelService;

    /**
     * 查询所有标签
     * @return
     */
    @GetMapping
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",labelService.findAll());
    }

    /**
     * 根据ID查询
     * @return
     */
    @GetMapping("{labelId}")
    public Result findById(@PathVariable String labelId){
        return new Result(true, StatusCode.OK,"查询成功",labelService.findById(labelId));
    }

    /**
     * 增加标签
     * @param label
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Label label){
        labelService.add(label);
        return new Result(true, StatusCode.OK,"增加成功");
    }

    /**
     * 根据ID更新标签
     * @return
     */
    @PutMapping("{labelId}")
    public Result update(@PathVariable String labelId,@RequestBody Label label){
        label.setId(labelId);
        labelService.update(label);
        return new Result(true, StatusCode.OK,"更新成功");
    }

    /**
     * 根据ID删除标签
     * @return
     */
    @DeleteMapping("{labelId}")
    public Result deleteById(@PathVariable String labelId){
        labelService.deleteById(labelId);
        return new Result(true, StatusCode.OK,"删除成功");
    }

    /**
     * 根据条件查询标签
     * @return
     */
    @PostMapping("search")
    public Result findSearch(@RequestBody Label label){
        List<Label> labelList =  labelService.findSearch(label);
        return new Result(true, StatusCode.OK,"查询成功",labelList);
    }

    /**
     * 根据条件查询标签,并分页
     * @return
     */
    @PostMapping("search/{page}/{size}")
    public Result pageQuery(@RequestBody Label label,@PathVariable Integer page,@PathVariable Integer size){
        Page<Label> pageData =  labelService.pageQuery(label,page,size);
        return new Result(true, StatusCode.OK,"查询成功",new PageResult<Label>(pageData.getTotalElements(),pageData.getContent()));
    }
}
