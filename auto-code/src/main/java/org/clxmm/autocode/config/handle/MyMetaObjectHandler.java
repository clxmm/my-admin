package org.clxmm.autocode.config.handle;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.clxmm.autocode.autocode.common.entity.CommonEntityId;
import org.clxmm.autocode.system.security.util.SecurityUserUtil;
import org.springframework.stereotype.Component;

import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 自动填充配置
 *
 * @liin com.baomidou.mybatisplus.core.handlers.MetaObjectHandler
 * isFill()
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Object originalObject = metaObject.getOriginalObject();

        if (originalObject instanceof CommonEntityId) {
            System.out.println("MyMetaObjectHandler 自动添加创建字段");
            CommonEntityId commonEntityId = (CommonEntityId) originalObject;
            System.out.println(commonEntityId);
            // 新增
            this.setInsertFieldValByName("createTime", new Date(), metaObject);
            this.setInsertFieldValByName("createId", SecurityUserUtil.getAdmin().getId() + "", metaObject);

        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
//        Object originalObject = metaObject.getOriginalObject();
//
//        if (originalObject instanceof CommonEntityId) {
//            System.out.println("-------");
//            CommonEntityId commonEntityId = (CommonEntityId) originalObject;
//            System.out.println(commonEntityId);
        Object originalObject = metaObject.getOriginalObject();

        if (originalObject instanceof CommonEntityId) {
            System.out.println("MyMetaObjectHandler 自动添加更新字段");
            this.setUpdateFieldValByName("updateTime", new Date(), metaObject);
            this.setUpdateFieldValByName("updateId", SecurityUserUtil.getAdmin().getId() + "", metaObject);
        }
//        }
//    }
    }


}
