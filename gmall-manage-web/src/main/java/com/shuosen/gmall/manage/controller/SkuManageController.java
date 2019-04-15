package com.shuosen.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shuosen.gmall.bean.SkuInfo;
import com.shuosen.gmall.bean.SpuImage;
import com.shuosen.gmall.bean.SpuInfo;
import com.shuosen.gmall.bean.SpuSaleAttr;
import com.shuosen.gmall.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin
public class SkuManageController {

    @Reference
    private ManageService manageService  ;

    @RequestMapping("spuImageList")
    @ResponseBody
    public List<SpuImage> spuImageList (String spuId){
          return    manageService.getSpuImageList(spuId);
    }


    @RequestMapping("/spuSaleAttrList")
    @ResponseBody
    public List<SpuSaleAttr> spuSaleAttrList(String spuId){
        //调用service层
        return  manageService.getSpuSaleAttrList(spuId);
    }

    /**
     * 整体保存
     * 从前台页面获取所有的信息
     * 四张表 ：
     *
     */
    @RequestMapping
    @ResponseBody
    public String saveSkuInfo(@RequestBody  SkuInfo skuInfo){
        manageService.saveSkuInfo(skuInfo);
         return "ok" ;
    }
}
