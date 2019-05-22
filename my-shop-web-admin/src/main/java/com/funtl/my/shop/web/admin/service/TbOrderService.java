package com.funtl.my.shop.web.admin.service;/*
 * @Author diandian
 * @Description
 * @Param
 * @return
 * */

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.domain.TbMessage;
import com.funtl.my.shop.domain.TbOrder;

import java.util.List;

public interface TbOrderService {
    List<TbOrder> selectAll();
   /* *//*新增*//*
    BaseResult save(TbOrder tbOrder);*/

    BaseResult deleteById(Long id);

    TbOrder getById(Long id);

    TbOrder getByOrderNum(String orderNum);

    void insert(TbOrder tbOrder);

    void update(TbOrder tbOrder);

}
