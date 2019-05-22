package com.funtl.my.shop.web.admin.service;/*
 * @Author diandian
 * @Description
 * @Param
 * @return
 * */

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.domain.TbMessage;
import com.funtl.my.shop.domain.TbMovieInfo;

import java.util.List;

public interface TbMessageService {
    List<TbMessage> selectAll();
    /*新增*/
    BaseResult save(TbMessage tbMessage);

    BaseResult deleteById(Long id);

    TbMessage getById(Long id);
}
