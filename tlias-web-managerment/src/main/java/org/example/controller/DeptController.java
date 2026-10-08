package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.Dept;
import org.example.pojo.Result;
import org.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    //查询
    @GetMapping//("/depts")
    public Result list(){
        List<Dept> deptList = deptService.findAll();
        log.info("查询所有部门：{}",deptList);
        return Result.success(deptList);
    }

    //删除
    @DeleteMapping//("/depts")
    public Result delete(/*@RequestParam("id")*/ Integer id){
//        System.out.println("id");
        log.info("删除部门：{}",id);
        deptService.deleteById(id);
        return Result.success();
    }

    //新增
    @PostMapping//("/depts")
    //@RequestBody   接受json格式参数   {"name”:“教研部"}
    public Result add(@RequestBody Dept dept){
        log.info("新增部门：{}",dept);
        deptService.add(dept);
        return Result.success();
    }

    //查询回显
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        Dept dept=deptService.getById(id);
        log.info("查询部门：{}",dept);
        return Result.success(dept);
    }

    //修改数据
    @PutMapping//("/depts")
    public Result upDate(@RequestBody Dept dept){
        deptService.update(dept);
        log.info("修改部门：{}",dept);
        return Result.success();
    }
}
