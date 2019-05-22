package com.funtl.my.shop.web.admin.dao;

import com.funtl.my.shop.domain.TbSeat;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TbSeatDao {
    int deleteByPrimaryKey(@Param("id") Long id, @Param("hallId") Long hallId);

    int insert(TbSeat record);

    int insertSelective(TbSeat record);

    TbSeat selectByPrimaryKey(@Param("id") Long id, @Param("hallId") Long hallId);

    int updateByPrimaryKeySelective(TbSeat record);

    int updateByPrimaryKey(TbSeat record);

    List<TbSeat> selectByMovieId(Long id);

    List<TbSeat> selectByMovieIdAndOrderId(Map<Object,Object> map);
}