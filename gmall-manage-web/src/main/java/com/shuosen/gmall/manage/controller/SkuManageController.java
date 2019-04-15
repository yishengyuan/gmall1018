package com.shuosen.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shuosen.gmall.bean.SpuImage;
import com.shuosen.gmall.bean.SpuInfo;
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

}
