package com.funtl.my.shop.web.admin.service.impl;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;
import com.funtl.my.shop.domain.TbCatelog;
import com.funtl.my.shop.domain.TbMovieInfo;
import com.funtl.my.shop.web.admin.dao.TbCatelogDao;
import com.funtl.my.shop.web.admin.dao.TbMovieInfoDao;
import com.funtl.my.shop.web.admin.service.TbCatelogService;
import com.funtl.my.shop.web.admin.service.TbMovieInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbMovieInfoServiceImpl implements TbMovieInfoService {
    @Autowired
    private TbMovieInfoDao tbMovieInfoDao;
    @Override
    public List<TbMovieInfo> selectAll() {
        return tbMovieInfoDao.selectAll();
    }

    @Override
    public List<TbMovieInfo> selectByCatelogId(Long id) {
        return tbMovieInfoDao.selectByCatelogId(id);
    }

    @Override
    public List<TbMovieInfo> selectByName(String name) {
        return tbMovieInfoDao.selectByName(name);
    }


    @Override
    public BaseResult save(TbMovieInfo tbMovieInfo) {
        //新增
        if(tbMovieInfo.getId()==null){
            tbMovieInfoDao.insert(tbMovieInfo);
        }
        //修改
        else{
            tbMovieInfoDao.updateByPrimaryKey(tbMovieInfo);
        }

        return BaseResult.success("保存分类信息成功");
    }

    @Override
    public void deleteById(Long id) {
        tbMovieInfoDao.deleteByPrimaryKey(id);
    }

    @Override
    public TbMovieInfo getById(Long id) {
            return tbMovieInfoDao.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<TbMovieInfo> page(int start, int length, int draw, TbMovieInfo entity) {
        int count=count(entity);

        Map<String,Object> params=new HashMap<>();
        params.put("start",start);
        params.put("length",length);
        params.put("pageParams",entity);

        PageInfo<TbMovieInfo> pageInfo=new PageInfo<>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(tbMovieInfoDao.page(params));

        return pageInfo;
    }

    /**
     * 查询总笔数
     * @return
     */
    public int count(TbMovieInfo entity){
        return tbMovieInfoDao.count(entity);
    };

}
