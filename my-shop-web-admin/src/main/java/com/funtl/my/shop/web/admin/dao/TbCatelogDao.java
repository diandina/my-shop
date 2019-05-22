package com.funtl.my.shop.web.admin.dao;


import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.domain.TbCatelog;

import java.util.List;

public interface TbCatelogDao {
    int deleteByPrimaryKey(Long id);

    int insert(TbCatelog record);


    int insertSelective(TbCatelog record);

    TbCatelog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbCatelog record);

    int updateByPrimaryKey(TbCatelog record);

    List<TbCatelog>  selectAll();

    BaseResult save(TbCatelog tbCatelog);


}