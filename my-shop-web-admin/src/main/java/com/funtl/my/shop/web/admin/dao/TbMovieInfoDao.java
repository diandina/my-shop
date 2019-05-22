package com.funtl.my.shop.web.admin.dao;

import com.funtl.my.shop.domain.TbMovieInfo;

import java.util.List;
import java.util.Map;

public interface TbMovieInfoDao {
    int deleteByPrimaryKey(Long id);

    int insert(TbMovieInfo record);

    int insertSelective(TbMovieInfo record);

    TbMovieInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbMovieInfo record);

    int updateByPrimaryKey(TbMovieInfo record);

    List<TbMovieInfo> selectAll();

    List<TbMovieInfo> selectByCatelogId(Long id);

    List<TbMovieInfo> selectByName(String name);

    /**
     * 分页查询
     * @param params, 需要两个参数，start/记录开始的位置，length/每页记录数
     * @return
     */
    List<TbMovieInfo> page(Map<String,Object> params);

    /**
     * 查询总笔数
     * @return
     */
    int count(TbMovieInfo entity);

}