package com.shuosen.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.shuosen.gmall.bean.*;
import com.shuosen.gmall.manage.mapper.*;
import com.shuosen.gmall.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    BaseAttrInfoMapper baseAttrInfoMapper;

    @Autowired
    BaseCatalog1Mapper baseCatalog1Mapper;

    @Autowired
    BaseCatalog2Mapper baseCatalog2Mapper;

    @Autowired
    BaseCatalog3Mapper baseCatalog3Mapper;

    @Autowired
    BaseAttrValueMapper baseAttrValueMapper;

    @Autowired
    SpuInfoMapper spuInfoMapper;

    @Autowired
    BaseSaleAttrMapper baseSaleAttrMapper;

    @Autowired
    SpuImageMapper spuImageMapper;


    @Autowired
    SpuSaleAttrMapper spuSaleAttrMapper;

    @Autowired
    SpuSaleAttrValueMapper spuSaleAttrValueMapper;

    @Autowired
    SkuInfoMapper skuInfoMapper;

    @Autowired
    SkuImageMapper skuImageMapper;

    @Autowired
    SkuAttrValueMapper skuAttrValueMapper;

    @Autowired
    SkuSaleAttrValueMapper skuSaleAttrValueMapper;

    @Override
    public List<BaseCatalog1> getCatalog1() {
        return baseCatalog1Mapper.selectAll();
    }

    @Override
    public List<BaseCatalog2> getCatalog2(String catalog1Id) {
        BaseCatalog2 baseCatalog2 = new BaseCatalog2();
        baseCatalog2.setCatalog1Id(catalog1Id);
        return baseCatalog2Mapper.select(baseCatalog2);
    }

    @Override
    public List<BaseCatalog3> getCatalog3(String catalog2Id) {

        BaseCatalog3 baseCatalog3 = new BaseCatalog3();
        baseCatalog3.setCatalog2Id(catalog2Id);

        return baseCatalog3Mapper.select(baseCatalog3);
    }

    @Override
    public List<BaseAttrInfo> getAttrList(String catalog3Id) {
        return baseAttrInfoMapper.getBaseAttrInfoListByCatalog3Id(Long.parseLong(catalog3Id));
    }

    @Override
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {
        // 如果有主键就进行更新 如果没有
        if (baseAttrInfo.getId() != null && baseAttrInfo.getId().length() > 0) {
            baseAttrInfoMapper.updateByPrimaryKeySelective(baseAttrInfo);
        } else {
            baseAttrInfo.setId(null);
            baseAttrInfoMapper.insertSelective(baseAttrInfo);
        }
        //把原属性值清空
        BaseAttrValue baseAttrValue = new BaseAttrValue();
        baseAttrValue.setAttrId(baseAttrInfo.getId());
        baseAttrValueMapper.delete(baseAttrValue);

        //重新插入属性值
        if (baseAttrInfo.getAttrValueList() != null && baseAttrInfo.getAttrValueList().size() > 0) {
            for (BaseAttrValue attrValue : baseAttrInfo.getAttrValueList()) {
                attrValue.setId(null);
                attrValue.setAttrId(baseAttrInfo.getId());
                baseAttrValueMapper.insertSelective(baseAttrValue);
            }
        }
    }

    @Override
    public BaseAttrInfo getAttrInfo(String attrId) {
        //根据attrId获取平台属性
        BaseAttrInfo baseAttrInfo = baseAttrInfoMapper.selectByPrimaryKey(attrId);
        BaseAttrValue baseAttrValue = new BaseAttrValue();
        baseAttrValue.setAttrId(attrId);
        List<BaseAttrValue> baseAttrValueList = baseAttrValueMapper.select(baseAttrValue);
        //给平台属性值集合赋值
        baseAttrInfo.setAttrValueList(baseAttrValueList);
        return baseAttrInfo;
    }

    @Override
    public List<SpuInfo> getSpuInfoList(SpuInfo spuInfo) {
        return spuInfoMapper.select(spuInfo);
    }

    @Override
    public List<BaseSaleAttr> getBaseSaleAttrList() {
        return baseSaleAttrMapper.selectAll();
    }

    @Override
    public void saveSpuInfo(SpuInfo spuInfo) {
        //什么情况下保存 ，什么情况下更新spuInfo
        if (spuInfo.getId() == null || spuInfo.getId().length() == 0) {
            spuInfo.setId(null);
            spuInfoMapper.insertSelective(spuInfo);
        } else {
            spuInfoMapper.updateByPrimaryKeySelective(spuInfo);
        }

        //spuImage 图片列表 先删除 再新增
        SpuImage spuImage = new SpuImage();
        spuImage.setSpuId(spuInfo.getId());
        spuImageMapper.delete(spuImage);

        //保存数据 ，先获取数据
        List<SpuImage> spuImageList = spuInfo.getSpuImageList();
        if (spuImageList != null && spuImageList.size() > 0) {
            for (SpuImage image : spuImageList) {

                image.setId(null);
                image.setSpuId(spuInfo.getId());
                spuImageMapper.insertSelective(image);
            }
        }

        //销售属性 删除插入
        SpuSaleAttr spuSaleAttr = new SpuSaleAttr();
        spuSaleAttr.setSpuId(spuInfo.getId());
        spuSaleAttrMapper.delete(spuSaleAttr);

        //获取数据
        List<SpuSaleAttr> spuSaleAttrList = spuInfo.getSpuSaleAttrList();
        if (spuSaleAttrList != null && spuSaleAttrList.size() > 0) {
            for (SpuSaleAttr saleAttr : spuSaleAttrList) {
                saleAttr.setId(null);
                saleAttr.setSpuId(spuInfo.getId());
                spuSaleAttrMapper.insertSelective(saleAttr);
                List<SpuSaleAttrValue> spuSaleAttrValueList = saleAttr.getSpuSaleAttrValueList();
                if (spuSaleAttrValueList != null && spuSaleAttrValueList.size() > 0) {
                    for (SpuSaleAttrValue spuSaleAttrValue : spuSaleAttrValueList) {
                        spuSaleAttrValue.setId(null);
                        spuSaleAttrValue.setSpuId(spuInfo.getId());
                        spuSaleAttrValueMapper.insertSelective(spuSaleAttrValue);
                    }
                }
            }
        }

    }

    @Override
    public List<SpuImage> getSpuImageList(String spuId) {
        SpuImage image = new SpuImage();
        image.setSpuId(spuId);
        return spuImageMapper.select(image);
    }

    @Override
    public List<SpuSaleAttr> getSpuSaleAttrList(String spuId) {

        return spuSaleAttrMapper.selectSpuSaleAttrList(Long.parseLong(spuId));
    }

    //字符串 str.length()  数组长度 length  集合长度 size()  文件长度 length()
    @Override
    public void saveSkuInfo(SkuInfo skuInfo) {

        //保存 skuInfo
        if (skuInfo.getId() != null && skuInfo.getId().length() > 0) {
            //更新
            skuInfoMapper.updateByPrimaryKeySelective(skuInfo);
        } else {
            skuInfo.setId(null);
            skuInfoMapper.insertSelective(skuInfo);
        }

        //删除之前的
        SkuImage skuImage = new SkuImage();
        skuImage.setSkuId(skuInfo.getId());
        skuImageMapper.delete(skuImage);
        //保存 skuImage
        List<SkuImage> spuImageList = skuInfo.getSkuImageList();
        if (spuImageList != null && spuImageList.size() > 0) {
            for (SkuImage image : spuImageList) {
                if (image.getId() != null && image.getId().length() > 0) {
                    image.setId(null);
                }
                image.setSkuId(skuInfo.getId());
                skuImageMapper.insertSelective(image);
            }
        }

        //保存 skuAttrValue
        SkuAttrValue skuAttrValue = new SkuAttrValue()  ;
        skuAttrValue.setSkuId(skuInfo.getId());
        skuAttrValueMapper.delete(skuAttrValue);

        List<SkuAttrValue> skuAttrValueList = skuInfo.getSkuAttrValueList();
        if(skuAttrValueList!=null && skuAttrValueList.size()>0){
            for (SkuAttrValue attrValue : skuAttrValueList) {
                  if(attrValue.getId()!=null && attrValue.getId().length()>0){
                      attrValue.setId(null);
                  }
                  attrValue.setSkuId(skuInfo.getId());
                  skuAttrValueMapper.insertSelective(attrValue);
            }
        }

        //保存 skuSaleAttrvalue
        SkuSaleAttrValue skuSaleAttrValue = new SkuSaleAttrValue();
        skuSaleAttrValue.setSkuId(skuInfo.getId());
        skuSaleAttrValueMapper.delete(skuSaleAttrValue);

        List<SkuSaleAttrValue> skuSaleAttrValueList = skuInfo.getSkuSaleAttrValueList();
        if(skuSaleAttrValueList!=null&& skuSaleAttrValueList.size()> 0){
            for (SkuSaleAttrValue saleAttrValue : skuSaleAttrValueList) {
                  if(saleAttrValue.getId()!=null && saleAttrValue.getId() .length()>0){
                      saleAttrValue.setId(null);
                  }
                  saleAttrValue.setSkuId(skuInfo.getId());
                  skuSaleAttrValueMapper.insertSelective(saleAttrValue);
            }
        }
    }

//    @Override
//    public List<BaseAttrValue> getAttrValueList(String attrId) {
//        BaseAttrValue baseAttrValue = new BaseAttrValue() ;
//        baseAttrValue.setAttrId(attrId);
//        return  baseAttrValueMapper.select(baseAttrValue);
//    }


}
