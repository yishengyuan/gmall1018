package com.shuosen.gmall.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
public class SkuInfo implements Serializable {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column
    private String spuId;

    @Column
    private String skuName;

    @Column
    private String skuDesc;

    @Column
    private String weight;

    @Column
    private String skuDefaultImg;

    @Column
    private String price  ;

    @Column
    private  String catalog3Id;


    //  @Transient:表示不是数据库的字段，但是在实体类中需要使用，来存储数据
    @Transient
    private List<SkuSaleAttrValue> skuSaleAttrValueList;

    @Transient
    private List<SkuAttrValue> skuAttrValueList  ;

    @Transient
    private List<SkuImage> skuImageList;
}
