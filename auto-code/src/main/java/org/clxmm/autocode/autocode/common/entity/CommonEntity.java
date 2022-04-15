package org.clxmm.autocode.autocode.common.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

/**
 * 业务表公共的创建，更新字段
 */
@Data
public class CommonEntity {

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "create_id", fill = FieldFill.INSERT)
    private String createId;

    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

    @TableField(value = "update_id", fill = FieldFill.UPDATE)
    private String updateId;

    /**
     * 1 表示删除，0 表示未删除
     */
    @TableLogic
    private int isDeleted;
}
