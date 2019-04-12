package com.shuosen.gmall.service;

import com.shuosen.gmall.bean.*;

import java.util.List;

public interface ManageService {

     List<BaseCatalog1> getCatalog1();

     List<BaseCatalog2> getCatalog2(String catalog1Id);


     List<BaseCatalog3> getCatalog3(String catalog2Id);

     List<BaseAttrInfo> getAttrList(String catalog3Id);

     //
     void saveAttrInfo(BaseAttrInfo baseAttrInfo);

//     List<BaseAttrValue> getAttrValueList(String attrId);
     //通过属性id查询平台属性值集合
     BaseAttrInfo getAttrInfo(String attrId);
}
