package com.funtl.my.shop.web.admin.service;/*
 * @Author diandian
 * @Description
 * @Param
 * @return
 * */

import com.funtl.my.shop.domain.TbOrderItem;
import com.funtl.my.shop.domain.TbSeat;

import java.util.List;

public interface TbSeatService {
    void insert(TbSeat tbSeat);

    List<TbSeat> selectByMovieId(Long id);

    List<TbSeat> selectByMovieIdAndOrderId(Long movieId,Long orderId);
}
