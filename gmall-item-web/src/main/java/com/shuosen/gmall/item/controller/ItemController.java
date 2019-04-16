package com.shuosen.gmall.item.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.shuosen.gmall.bean.SkuInfo;
import com.shuosen.gmall.bean.SkuSaleAttrValue;
import com.shuosen.gmall.bean.SpuSaleAttr;
import com.shuosen.gmall.service.ManageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ItemController {

    @Reference
    private ManageService manageService;


    @RequestMapping("{skuId}.html")
    public String itemHtml(@PathVariable String skuId, HashMap map) {

        System.out.println("skuId:" + skuId);
        SkuInfo skuInfo = manageService.getSkuInfo(skuId);
        map.put("skuInfo", skuInfo);

        // 存储spu sku数据
        List<SpuSaleAttr> saleAttrList = manageService.getSpuSaleAttrListCheckBySku(skuInfo);
        map.put("saleAttrList", saleAttrList);

        List<SkuSaleAttrValue> skuSaleAttrValueListBySpu = manageService.getSkuSaleAttrValueListBySpu(skuInfo.getSpuId());


//把列表变换成 valueid1|valueid2|valueid3 ：skuId  的 哈希表 用于在页面中定位查询
        String valueIdsKey = "";

        Map<String, String> valuesSkuMap = new HashMap<>();

        for (int i = 0; i < skuSaleAttrValueListBySpu.size(); i++) {
            SkuSaleAttrValue skuSaleAttrValue = skuSaleAttrValueListBySpu.get(i);
            if (valueIdsKey.length() != 0) {
                valueIdsKey = valueIdsKey + "|";
            }
            valueIdsKey = valueIdsKey + skuSaleAttrValue.getSaleAttrValueId();

            if ((i + 1) == skuSaleAttrValueListBySpu.size() || !skuSaleAttrValue.getSkuId().equals(skuSaleAttrValueListBySpu.get(i + 1).getSkuId())) {

                valuesSkuMap.put(valueIdsKey, skuSaleAttrValue.getSkuId());
                valueIdsKey = "";
            }

        }

        //把map变成json串
        String valuesSkuJson = JSON.toJSONString(valuesSkuMap);

        map.put("valuesSkuJson", valuesSkuJson);

        return "item";
    }
}
