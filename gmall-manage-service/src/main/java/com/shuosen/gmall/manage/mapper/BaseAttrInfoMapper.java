package com.shuosen.gmall.manage.mapper;

import com.shuosen.gmall.bean.BaseAttrInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BaseAttrInfoMapper  extends Mapper<BaseAttrInfo> {

    List<BaseAttrInfo> getBaseAttrInfoListByCatalog3Id (Long catalog3Id);

}
