package com.funtl.my.shop.web.admin.abstracs;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.persistence.BaseEntity;
import com.funtl.my.shop.commons.persistence.BaseTreeDao;
import com.funtl.my.shop.commons.persistence.BaseTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class AbstractBaseTreeServiceImpl<T extends BaseEntity,D extends BaseTreeDao<T>> implements BaseTreeService<T> {

    @Autowired
    protected D dao;

    //查询全部
    public List<T> selectAll(){
        return dao.selectAll();
    }

    //删除
    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {
        dao.delete(id);
    }


    @Override
    /*根据id查询单个对象*/
    public T getById(Long id){
        return dao.getById(id);
    };

    @Override
    @Transactional(readOnly = false)
    /*更新用户信息*/
    public void update(T entity){
        dao.update(entity);
    };

    @Override
    /**
     * 根据父级结点ID查询所有子节点
     * @param pid
     * @return
     */
    public List<T> selectByPid(Long pid){
        return dao.selectByPid(pid);
    }

}
