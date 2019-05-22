package com.funtl.my.shop.web.admin.abstracs;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;
import com.funtl.my.shop.commons.persistence.BaseDao;
import com.funtl.my.shop.commons.persistence.BaseEntity;
import com.funtl.my.shop.commons.persistence.BaseService;
import com.funtl.my.shop.domain.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractBaseServiceImpl<T extends BaseEntity,D extends BaseDao<T>> implements BaseService<T> {

    @Autowired
    protected D dao;

    //查询全部
    @Override
    public List<T> selectAll() {
        return dao.selectAll();
    }

    //删除用户信息
    @Override
    @Transactional(readOnly = false)
    public void delete(Long id){
        dao.delete(id);
    };

    //根据id查询用户信息
    @Override
    public T getById(Long id){
        return dao.getById(id);
    };

    //更新信息
    @Override
    @Transactional(readOnly = false)
    public void update(T entity){
        dao.update(entity);
    };

    //批量删除
    @Override
    @Transactional(readOnly = false)
    public void deleteMulti(String[] ids){
        dao.deleteMulti(ids);
    };


    /**
     * 查询总笔数
     * @return
     */
    @Override
    public int count(T entity){
        return dao.count(entity);
    };

    /**
     * 分页查询
     * @param params, 需要两个参数，start/记录开始的位置，length/每页记录数
     * @return
     */
    @Override
    public PageInfo<T> page(int start, int length, int draw, T entity) {
        int count=count(entity);

        Map<String,Object> params=new HashMap<>();
        params.put("start",start);
        params.put("length",length);
        params.put("pageParams",entity);

        PageInfo<T> pageInfo=new PageInfo<>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(dao.page(params));

        return pageInfo;
    }
}
