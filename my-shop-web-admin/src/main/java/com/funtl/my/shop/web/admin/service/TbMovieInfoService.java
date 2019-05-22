package com.funtl.my.shop.web.admin.service;/*
 * @Author diandian
 * @Description
 * @Param
 * @return
 * */

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;
import com.funtl.my.shop.commons.persistence.BaseService;
import com.funtl.my.shop.domain.TbCatelog;
import com.funtl.my.shop.domain.TbMovieInfo;
import com.funtl.my.shop.domain.TbUser;

import java.util.List;

public interface TbMovieInfoService{
    List<TbMovieInfo> selectAll();
    List<TbMovieInfo> selectByCatelogId(Long id);
    List<TbMovieInfo> selectByName(String name);
    /*新增*/
    BaseResult save(TbMovieInfo tbMovieInfo);

    void deleteById(Long id);

    TbMovieInfo getById(Long id);

    /**
     * 分页查询
     * @param start
     * @param length
     * @return
     */
    PageInfo<TbMovieInfo> page(int start, int length, int draw, TbMovieInfo tbMovieInfo);

}
