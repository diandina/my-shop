package com.funtl.my.shop.web.admin.dao;


import com.funtl.my.shop.domain.TbMessage;
import com.funtl.my.shop.domain.TbOrder;

import java.util.List;

public interface TbOrderDao {
    int deleteByPrimaryKey(Long id);

    int insert(TbOrder record);

    int insertSelective(TbOrder record);

    TbOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbOrder record);

    int updateByPrimaryKey(TbOrder record);

    List<TbOrder> selectAll();

    TbOrder selectByOrderNum(String orderNum);
}