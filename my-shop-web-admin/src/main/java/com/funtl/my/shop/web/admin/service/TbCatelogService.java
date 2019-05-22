package com.funtl.my.shop.web.admin.service;/*
 * @Author diandian
 * @Description
 * @Param
 * @return
 * */

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.domain.TbCatelog;

import java.util.List;

public interface TbCatelogService {
    List<TbCatelog> selectAll();
    /*新增*/
    BaseResult save(TbCatelog tbCatelog);

    void deleteById(Long id);

    TbCatelog getById(Long id);
}
