package com.funtl.my.shop.commons.persistence;/*
 * @Author diandian
 * @Description
 * 所有数据访问层的基类
 * @Param
 * @return
 * */

import com.fasterxml.jackson.databind.ser.Serializers;

import java.util.List;
import java.util.Map;

public interface BaseDao<T extends BaseEntity> {
    /*查询全部数据*/
    List<T> selectAll();

    /*新增*/
    void insert(T entity);

    /*删除*/
    void delete(Long id);

    /*根据id查询单个对象*/
    T getById(Long id);

    /*更新用户信息*/
    void update(T entity);


    //批量删除
    void deleteMulti(String[] ids);

    /**
     * 分页查询
     * @param params, 需要两个参数，start/记录开始的位置，length/每页记录数
     * @return
     */
    List<T> page(Map<String,Object> params);

    /**
     * 查询总笔数
     * @return
     */
    int count(T entity);
}
