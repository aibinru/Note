package com.mybatis.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author aibinru
 * @since 2023-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AgentList extends Model<AgentList> {

    private static final long serialVersionUID = 1L;

    @TableField("agentCode")
    private String agentCode;

    @TableField("agentName")
    private String agentName;

    @TableField("agentIdType")
    private String agentIdType;

    @TableField("agentIdNo")
    private Integer agentIdNo;

    @TableField("agentSex")
    private String agentSex;

    @TableField("agentBirthday")
    private LocalDate agentBirthday;

    private String manage;

    private String phone;

    @TableField("saleChnl")
    private String saleChnl;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
