package com.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author aibinru:am19136754034@gmail.com
 * @date 23-12-27 14:24
 * @description:
 */
@Data
@TableName("tb_user")
public class UserEntity implements Serializable {

    @TableId
    private Integer id;

    private String name;

    private String password;

    private String pabusitype;

}
