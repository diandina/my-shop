package com.funtl.my.shop.commons.persistence;/*
 * @Author diandian
 * @Description
 * 所有业务逻辑层的基类
 * @Param
 * @return
 * */

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;

import java.util.List;

public interface BaseService<T extends BaseEntity> {
    //查询全部
    public List<T> selectAll();

    //保存信息
    BaseResult save(T entity);

    //删除用户信息
    void delete(Long id);

    //根据id查询用户信息
    T getById(Long id);

    //更新信息
    void update(T entity);

    //批量删除
    void deleteMulti(String[] ids);

    /**
     * 分页查询
     * @param start
     * @param length
     * @return
     */
    PageInfo<T> page(int start, int length, int draw, T entity);


    /**
     * 查询总笔数
     * @return
     */
    int count(T entity);
}
