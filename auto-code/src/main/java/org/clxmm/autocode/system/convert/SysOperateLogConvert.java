package org.clxmm.autocode.system.convert;

import org.clxmm.autocode.autocode.entity.SysOperateLog;
import org.clxmm.autocode.system.operatelog.core.dto.OperateLogCreateReqDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface SysOperateLogConvert {


    SysOperateLogConvert INSTANCE = Mappers.getMapper(SysOperateLogConvert.class);

    SysOperateLog convert(OperateLogCreateReqDTO bean);


}
