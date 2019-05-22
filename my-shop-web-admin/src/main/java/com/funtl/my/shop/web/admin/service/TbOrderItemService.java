package com.funtl.my.shop.web.admin.service;/*
 * @Author diandian
 * @Description
 * @Param
 * @return
 * */

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.domain.TbOrder;
import com.funtl.my.shop.domain.TbOrderItem;

import java.util.List;

public interface TbOrderItemService {
//    List<TbOrderItem> selectAll();
   /* *//*新增*//*
    BaseResult save(TbOrder tbOrder);*/

//    BaseResult deleteById(Long id);

    List<TbOrderItem> getByOrderIdNew(String id);

    TbOrderItem getByOrderId(String id);

    void insertBatch(List list);
}
