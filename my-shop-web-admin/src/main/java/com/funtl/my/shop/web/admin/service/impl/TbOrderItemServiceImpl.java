package com.funtl.my.shop.web.admin.service.impl;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.domain.TbOrder;
import com.funtl.my.shop.domain.TbOrderItem;
import com.funtl.my.shop.web.admin.dao.TbOrderDao;
import com.funtl.my.shop.web.admin.dao.TbOrderItemDao;
import com.funtl.my.shop.web.admin.service.TbOrderItemService;
import com.funtl.my.shop.web.admin.service.TbOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbOrderItemServiceImpl implements TbOrderItemService {
    @Autowired
    private TbOrderItemDao tbOrderItemDao;

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
    public List<TbOrderItem> getByOrderIdNew(String id) {
            return tbOrderItemDao.selectByOrderIdNew(id);
    }

    @Override
    public TbOrderItem getByOrderId(String id) {
            return tbOrderItemDao.selectByOrderId(id);
    }

    @Override
    public void insertBatch(List list) {
        tbOrderItemDao.insertBatch(list);
    }


}
