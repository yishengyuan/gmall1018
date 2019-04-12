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
    BaseAttrInfoMapper baseAttrInfoMapper ;

    @Autowired
    BaseCatalog1Mapper baseCatalog1Mapper  ;

    @Autowired
    BaseCatalog2Mapper  baseCatalog2Mapper ;

    @Autowired
    BaseCatalog3Mapper baseCatalog3Mapper ;

    @Autowired
    BaseAttrValueMapper baseAttrValueMapper;



    @Override
    public List<BaseCatalog1> getCatalog1() {
        return  baseCatalog1Mapper.selectAll();
    }

    @Override
    public List<BaseCatalog2> getCatalog2(String catalog1Id) {
        BaseCatalog2 baseCatalog2 = new BaseCatalog2();
        baseCatalog2.setCatalog1Id(catalog1Id);
        return  baseCatalog2Mapper.select(baseCatalog2);
    }

    @Override
    public List<BaseCatalog3> getCatalog3(String catalog2Id) {

        BaseCatalog3 baseCatalog3 = new BaseCatalog3() ;
        baseCatalog3.setCatalog2Id(catalog2Id);

        return baseCatalog3Mapper.select(baseCatalog3);
    }

    @Override
    public List<BaseAttrInfo> getAttrList(String catalog3Id) {
        BaseAttrInfo baseAttrInfo = new BaseAttrInfo();
        baseAttrInfo.setCatalog3Id(catalog3Id);
        return baseAttrInfoMapper.select(baseAttrInfo);
    }

    @Override
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {
        // 如果有主键就进行更新 如果没有
        if(baseAttrInfo.getId()!=null && baseAttrInfo.getId().length()>0){
            baseAttrInfoMapper.updateByPrimaryKeySelective(baseAttrInfo);
        }else{
            baseAttrInfo.setId(null);
            baseAttrInfoMapper.insertSelective(baseAttrInfo);
        }
        //把原属性值清空
        BaseAttrValue baseAttrValue = new BaseAttrValue() ;
        baseAttrValue.setAttrId(baseAttrInfo.getId());
        baseAttrValueMapper.delete(baseAttrValue);

        //重新插入属性值
        if(baseAttrInfo.getAttrValueList()!=null && baseAttrInfo.getAttrValueList().size()>0){
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
         return baseAttrInfo ;
    }

//    @Override
//    public List<BaseAttrValue> getAttrValueList(String attrId) {
//        BaseAttrValue baseAttrValue = new BaseAttrValue() ;
//        baseAttrValue.setAttrId(attrId);
//        return  baseAttrValueMapper.select(baseAttrValue);
//    }



}
