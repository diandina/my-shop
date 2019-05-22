package com.funtl.my.shop.commons.persistence;/*
 * @Author diandian
 * @Description
 * 通用的树形结构接口
 * @Param
 * @return
 * */

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;

import java.util.List;

public interface BaseTreeDao<T extends BaseEntity> {

    //查询全部
    List<T> selectAll();

    /*新增*/
    void insert(T entity);

    //删除用户信息
    void delete(Long id);

    //根据id查询用户信息
    T getById(Long id);

    //更新信息
    void update(T entity);

    /**
     * 根据父级结点ID查询所有子节点
     * @param pid
     * @return
     */
    List<T> selectByPid(Long pid);
}
