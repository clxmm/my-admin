package org.clxmm.autocode.autocode.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class CommonEntityId extends CommonEntity {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;





}
