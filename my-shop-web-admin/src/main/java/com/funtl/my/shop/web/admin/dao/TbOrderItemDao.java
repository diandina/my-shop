package com.funtl.my.shop.web.admin.dao;


import com.funtl.my.shop.domain.TbOrderItem;

import java.util.List;

public interface TbOrderItemDao {
    int deleteByPrimaryKey(String id);

    int insert(TbOrderItem record);

    int insertSelective(TbOrderItem record);

    TbOrderItem selectByPrimaryKey(String id);

    TbOrderItem selectByOrderId(String id);

    List<TbOrderItem> selectByOrderIdNew(String id);

    int updateByPrimaryKeySelective(TbOrderItem record);

    int updateByPrimaryKey(TbOrderItem record);

    void insertBatch(List list);
}