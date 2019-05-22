package com.funtl.my.shop.web.admin.service.impl;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.domain.TbCatelog;
import com.funtl.my.shop.web.admin.dao.TbCatelogDao;
import com.funtl.my.shop.web.admin.service.TbCatelogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class TbCatelogServiceImpl implements TbCatelogService {
    @Autowired
    private TbCatelogDao tbCatelogDao;
    @Override
    public List<TbCatelog> selectAll() {
        return tbCatelogDao.selectAll();
    }

    @Override
    public BaseResult save(TbCatelog tbCatelog) {
        //新增
        if(tbCatelog.getId()==null){
            tbCatelogDao.insert(tbCatelog);
        }
        //修改
        else{
            tbCatelogDao.updateByPrimaryKey(tbCatelog);
        }

        return BaseResult.success("保存分类信息成功");
    }

    @Override
    public void deleteById(Long id) {
         tbCatelogDao.deleteByPrimaryKey(id);
    }

    @Override
    public TbCatelog getById(Long id) {
            return tbCatelogDao.selectByPrimaryKey(id);
    }


}
