package com.funtl.my.shop.web.admin.service.impl;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.domain.TbMessage;
import com.funtl.my.shop.domain.TbOrder;
import com.funtl.my.shop.web.admin.dao.TbMessageDao;
import com.funtl.my.shop.web.admin.dao.TbOrderDao;
import com.funtl.my.shop.web.admin.service.TbMessageService;
import com.funtl.my.shop.web.admin.service.TbOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TbOrderServiceImpl implements TbOrderService {
    @Autowired
    private TbOrderDao tbOrderDao;
    @Override
    public List<TbOrder> selectAll() {
        return tbOrderDao.selectAll();
    }

    /*@Override
    public BaseResult save(TbOrder tbOrder) {
        //新增回复
        if(tbMessage.getId()!=null){
            tbMessage.setReplyId(tbMessage.getId());
            tbMessage.setId(null);
            SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = format0.format(new Date().getTime());
            tbMessage.setTime(time);
            tbMessageDao.insert(tbMessage);
        }

        return BaseResult.success("保存留言信息成功");
    }
*/
    @Override
    public BaseResult deleteById(Long id) {

        tbOrderDao.deleteByPrimaryKey(id);
        return BaseResult.success("删除订单信息成功");
    }

    @Override
    public TbOrder getById(Long id) {
            return tbOrderDao.selectByPrimaryKey(id);
    }

    @Override
    public TbOrder getByOrderNum(String orderNum) {
        return tbOrderDao.selectByOrderNum(orderNum);
    }

    @Override
    public void insert(TbOrder tbOrder) {
        tbOrderDao.insert(tbOrder);
    }

    @Override
    public void update(TbOrder tbOrder) {
        tbOrderDao.updateByPrimaryKey(tbOrder);
    }
}
