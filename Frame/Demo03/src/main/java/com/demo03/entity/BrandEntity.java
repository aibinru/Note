package com.demo03.entity;



import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {

    /**
     * 品牌id bigint
     */
    @TableId
    private Long brandId;
    //品牌名 char
    private String name;
    //logo地址 varchar
    private String logo;
    //介绍 longtext
    private String descript;
    //状态 tinyint
    private Integer showStatus;
    //检索首字符 char
    private String firstLetter;
    //排序 int
    private Integer sort;
}
