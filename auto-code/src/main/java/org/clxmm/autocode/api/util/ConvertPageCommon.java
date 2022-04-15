package org.clxmm.autocode.api.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.clxmm.autocode.api.vo.common.CommonPage;
import org.clxmm.autocode.api.vo.common.CommonPageData;
import org.clxmm.autocode.learn.demo.logging.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ConvertPageCommon {


    public static void change(CommonPage commonPage, IPage iPage) {
        commonPage.setPageSize(iPage.getSize());
        commonPage.setTotal(iPage.getTotal());
        commonPage.setCurrent(iPage.getCurrent());
    }


    public static CommonPageData getCommonPageData(IPage iPage) {
        CommonPageData commonPageData = new CommonPageData();
        change(commonPageData, iPage);
        return commonPageData;
    }

//    public static<R> CommonPageData getCommonPageDataResult(IPage<R> iPage, Object target) {
//        CommonPageData  commonPageData = new CommonPageData();
//        change(commonPageData,iPage);
//        List<R> records = iPage.getRecords();
//
//        commonPageData.setData(data);
//
//        return commonPageData;
//    }

    public static <R> CommonPageData getCommonPageDataResult(IPage<R> iPage, Object target) {
        CommonPageData commonPageData = new CommonPageData();
        change(commonPageData, iPage);
        List<R> records = iPage.getRecords();
        List<?> objects = BeanUtil.copyToList(records, target.getClass());
        commonPageData.setData(objects);
        return commonPageData;
    }


    /**
     * 获取分页条件,
     */
    public static <T> Page<T> getPage(CommonPage commonPage) {
        Page<T> page = new Page<>();
        page.setSize(commonPage.getPageSize());
        page.setCurrent(commonPage.getCurrent());
        return page;
    }


}
