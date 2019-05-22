package com.funtl.my.shop.web.admin.dao;


import com.funtl.my.shop.domain.TbMessage;
import com.funtl.my.shop.domain.TbMovieInfo;

import java.util.List;

public interface TbMessageDao {
    int deleteByPrimaryKey(Long id);

    int insert(TbMessage record);

    int insertSelective(TbMessage record);

    TbMessage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbMessage record);

    int updateByPrimaryKey(TbMessage record);

    List<TbMessage> selectAll();

    List<TbMessage> selectByReplyId(Long id);
}