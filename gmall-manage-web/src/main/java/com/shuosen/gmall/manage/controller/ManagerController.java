package com.shuosen.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shuosen.gmall.bean.*;
import com.shuosen.gmall.service.ManageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
public class ManagerController {

    @Reference
    private ManageService manageService;

    //控制器为index ，返回的视图名称为index.html
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("getCatalog1")
    @ResponseBody
    public List<BaseCatalog1> getCatalog1() {
        return manageService.getCatalog1();
    }

    @RequestMapping("getCatalog2")
    @ResponseBody
    public List<BaseCatalog2> getCatalog2(String catalog1Id) {
        return manageService.getCatalog2(catalog1Id);
    }

    @RequestMapping("getCatalog3")
    @ResponseBody
    public List<BaseCatalog3> getCatalog3(String catalog2Id) {
        return manageService.getCatalog3(catalog2Id);
    }

    @RequestMapping("attrInfoList")
    @ResponseBody
    public List<BaseAttrInfo> attrInfoList(String catalog3Id) {
        return manageService.getAttrList(catalog3Id);
    }

    @RequestMapping("saveAttrInfo")
    @ResponseBody
    public void saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo) {
        //调用服务层保存方法
        manageService.saveAttrInfo(baseAttrInfo);
    }

    //通过属性id查询到平台属性的集合
    @RequestMapping("getAttrValueList")
    @ResponseBody
    public List<BaseAttrValue> getAttrValueList(String attrId) {
         BaseAttrInfo baseAttrInfo = manageService.getAttrInfo(attrId);
         return baseAttrInfo.getAttrValueList();
    }

}
