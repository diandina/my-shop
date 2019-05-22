package com.funtl.my.shop.web.admin.web.controller.qiantaiController;

import com.funtl.my.shop.domain.*;
import com.funtl.my.shop.web.admin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 电影类别管理
 */
@Controller
@Transactional
@RequestMapping(value="qiantai/order")
public class QianTaiOrderController {
    @Autowired
    private TbMovieInfoService tbMovieInfoService;
    @Autowired
    private TbOrderService tbOrderService;
    @Autowired
    private TbOrderItemService tbOrderItemService;
    @Autowired
    private TbMessageService tbMessageService;
    @Autowired
    private TbSeatService tbSeatService;

    @ModelAttribute
    public TbOrder getTbOrderInfo(Long id){
        TbOrder tbOrder=null;
        //id不为空则从数据库获取
        if(id!=null){
            tbOrder=tbOrderService.getById(id);
        }
        else{
            tbOrder=new TbOrder();
        }
        return tbOrder;
    }


    /**
     * 订单明细查看详情
     * @return
     */
    @RequestMapping(value ="orderListDetail",method = RequestMethod.GET)
    public String orderListDetail(TbOrder tbOrder,HttpServletRequest request){
        List<TbOrderItem> tbOrderItems = tbOrderItemService.getByOrderIdNew(tbOrder.getOrderNum());
        HashMap<Object, Object> map = new HashMap<>();
        for (int i = 0; i < tbOrderItems.size(); i++) {
            List<TbSeat> tbSeats = tbSeatService.selectByMovieIdAndOrderId(tbOrderItems.get(i).getMovieId(),Long.parseLong(tbOrderItems.get(i).getOrderId()));
            TbMovieInfo tbMovieInfo = tbMovieInfoService.getById(tbOrderItems.get(i).getMovieId());
            tbOrderItems.get(i).setMovieTime(tbMovieInfo.getShowTime());
            ArrayList<String> mapString = new ArrayList<>();
            for (TbSeat tbSeat : tbSeats) {
                mapString.add(tbSeat.getRow()+"排"+tbSeat.getCol()+"座 ");
            }
            map.put(i,mapString);
        }
        request.setAttribute("tbOrderItems",tbOrderItems);
        request.setAttribute("map",map);
        return "site/order_list_detail";
    }
}
