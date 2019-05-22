package com.funtl.my.shop.web.admin.service.impl;

import com.funtl.my.shop.domain.TbOrderItem;
import com.funtl.my.shop.domain.TbSeat;
import com.funtl.my.shop.web.admin.dao.TbOrderItemDao;
import com.funtl.my.shop.web.admin.dao.TbSeatDao;
import com.funtl.my.shop.web.admin.service.TbOrderItemService;
import com.funtl.my.shop.web.admin.service.TbSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class TbSeatServiceImpl implements TbSeatService {
    @Autowired
    private TbSeatDao tbSeatDao;

    @Override
    public void insert(TbSeat tbSeat) {
        tbSeatDao.insert(tbSeat);
    }

    @Override
    public List<TbSeat> selectByMovieId(Long id) {
        return tbSeatDao.selectByMovieId(id);
    }

    @Override
    public List<TbSeat> selectByMovieIdAndOrderId(Long movieId, Long orderId) {
        HashMap<Object, Object> params = new HashMap<>();
        params.put("movieId",movieId);
        params.put("orderItemOrderId",orderId);
        return tbSeatDao.selectByMovieIdAndOrderId(params);
    }
}
