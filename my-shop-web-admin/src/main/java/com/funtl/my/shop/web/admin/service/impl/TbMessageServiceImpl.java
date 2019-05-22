package com.funtl.my.shop.web.admin.service.impl;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.domain.TbMessage;
import com.funtl.my.shop.domain.TbMovieInfo;
import com.funtl.my.shop.web.admin.dao.TbMessageDao;
import com.funtl.my.shop.web.admin.dao.TbMovieInfoDao;
import com.funtl.my.shop.web.admin.service.TbMessageService;
import com.funtl.my.shop.web.admin.service.TbMovieInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TbMessageServiceImpl implements TbMessageService {
    @Autowired
    private TbMessageDao tbMessageDao;
    @Override
    public List<TbMessage> selectAll() {
        return tbMessageDao.selectAll();
    }

    @Override
    public BaseResult save(TbMessage tbMessage) {
        //新增回复
        if(tbMessage.getId()!=null){
            tbMessage.setReplyId(tbMessage.getId());
            tbMessage.setId(null);
            SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = format0.format(new Date().getTime());
            tbMessage.setTime(time);
            tbMessageDao.insert(tbMessage);
        }else{
            tbMessageDao.insert(tbMessage);
        }

        return BaseResult.success("保存留言信息成功");
    }

    @Override
    public BaseResult deleteById(Long id) {
        List<TbMessage> list = tbMessageDao.selectByReplyId(id);
        if(list.size()>0){
            return BaseResult.fail("存在相关留言，暂不允许删除");
        }else{
            tbMessageDao.deleteByPrimaryKey(id);
            return BaseResult.success("删除留言信息成功");
        }
    }

    @Override
    public TbMessage getById(Long id) {
            return tbMessageDao.selectByPrimaryKey(id);
    }


}
