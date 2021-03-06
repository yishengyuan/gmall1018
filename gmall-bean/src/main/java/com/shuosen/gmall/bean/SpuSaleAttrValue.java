package com.shuosen.gmall.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/***
 * 商品销售属性值表
 */
@Data
public class SpuSaleAttrValue implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column
    private String  spuId ;

    @Column
    private String saleAttrId ;

    @Column
    private String  saleAttrValueName ;

    @Transient
    String isChecked;


}
