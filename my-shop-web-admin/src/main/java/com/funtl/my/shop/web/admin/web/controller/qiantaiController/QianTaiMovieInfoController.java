package com.funtl.my.shop.web.admin.web.controller.qiantaiController;

import com.alibaba.druid.support.json.JSONUtils;
import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.domain.*;
import com.funtl.my.shop.web.admin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 电影类别管理
 */
@Controller
@Transactional
@RequestMapping(value="qiantai/movie")
public class QianTaiMovieInfoController {
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
    public TbMovieInfo getTbMovieInfo(Long id){
        TbMovieInfo tbMovieInfo=null;
        //id不为空则从数据库获取
        if(id!=null){
            tbMovieInfo=tbMovieInfoService.getById(id);
        }
        else{
            tbMovieInfo=new TbMovieInfo();
        }
        return tbMovieInfo;
    }



    /**
     * 前台跳转到电影详情页
     * @return
     */
    @RequestMapping(value ="detail",method = RequestMethod.GET)
    public String movieDetail(){
        return "site/movie_detail";
    }

    /**
     * 选完座跳转到电影详情页
     * @return
     */
    @RequestMapping(value ="todetail",method = RequestMethod.GET)
    public String tomovieDetail(HttpServletRequest request,Model model){
        model.addAttribute("counter",request.getParameter("counter"));
        model.addAttribute("seatNum",request.getParameter("seatNum"));
        return "site/movie_detail";
    }
    /**
     * 前台跳转到选座页面
     * @return
     */
    @RequestMapping(value ="seat",method = RequestMethod.GET)
    public String selectSeat(HttpServletRequest request){
        String id = request.getParameter("id");
        List<TbSeat> tbSeats = tbSeatService.selectByMovieId(Long.parseLong(id));
        ArrayList<String> mapString = new ArrayList<>();
        for (TbSeat tbSeat : tbSeats) {
            mapString.add(tbSeat.getRow()+"_"+tbSeat.getCol());
        }
        request.setAttribute("mapString",mapString);
        return "site/movie_seat";
    }

    /**
     * 超链接到我的购物车
     * @return
     */
   /* @RequestMapping(value ="link/mychart",method = RequestMethod.GET)
    public String linkmychart(){
        return "site/movie_mychart";
    }*/

    @RequestMapping(value ="mychart",method = RequestMethod.GET)
    public String mychart(HttpServletRequest req){
        ArrayList<TbMovieInfo> tbMovieInfoList = (ArrayList<TbMovieInfo>) req.getSession().getAttribute("tbMovieInfoList");
        ArrayList<String> numList = (ArrayList<String>) req.getSession().getAttribute("numList");
        ArrayList<String> totalList = (ArrayList<String>) req.getSession().getAttribute("totalList");
        ArrayList<String> seatList = (ArrayList<String>) req.getSession().getAttribute("seatList");
        if(tbMovieInfoList==null){
                tbMovieInfoList=new ArrayList<>();
                numList=new ArrayList<>();
                totalList=new ArrayList<>();
                seatList=new ArrayList<>();
        };
        if(!"".equals(req.getParameter("id"))){
            TbMovieInfo tbMovieInfo1 = tbMovieInfoService.getById(Long.parseLong(req.getParameter("id")));
            boolean flag=true;
            for (TbMovieInfo tbMovieInfo : tbMovieInfoList) {
                if(tbMovieInfo.getId()==tbMovieInfo1.getId()){
                    flag=false;
                    break;
                }
            }
            if(flag){
                numList.add(req.getParameter("num"));
                seatList.add(req.getParameter("seatNum"));
                tbMovieInfoList.add(tbMovieInfo1);
                totalList.add(String.valueOf(Integer.parseInt(req.getParameter("num")) * tbMovieInfo1.getPrice()));
            }
        }
        req.getSession().setAttribute("tbMovieInfoList",tbMovieInfoList);
        req.getSession().setAttribute("numList",numList);
        req.getSession().setAttribute("totalList",totalList);
        req.getSession().setAttribute("seatList",seatList);
        return "site/movie_mychart";
    }

    @RequestMapping(value ="delAllMychart",method = RequestMethod.GET)
    public String delAllMychart(HttpServletRequest request){
        request.getSession().removeAttribute("tbMovieInfoList");
        request.getSession().removeAttribute("numList");
        request.getSession().removeAttribute("totalList");
        return "site/movie_mychart";
    }

    @RequestMapping(value ="delMychart",method = RequestMethod.GET)
    public String delMychart(HttpServletRequest request){
        ArrayList<TbMovieInfo> tbMovieInfoList = (ArrayList<TbMovieInfo>) request.getSession().getAttribute("tbMovieInfoList");
        ArrayList<String> numList = (ArrayList<String>) request.getSession().getAttribute("numList");
        ArrayList<String> totalList = (ArrayList<String>) request.getSession().getAttribute("totalList");
        Long id = Long.parseLong(request.getParameter("id"));
        String num = request.getParameter("num");
        String totalFee = request.getParameter("totalFee");
        for (TbMovieInfo tbMovieInfo : tbMovieInfoList) {
            if(tbMovieInfo.getId()==id){
                tbMovieInfoList.remove(tbMovieInfo);
                numList.remove(num);
                totalList.remove(totalFee);
                break;
            }
        }
        request.getSession().setAttribute("tbMovieInfoList",tbMovieInfoList);
        return "site/movie_mychart";
    }

    @RequestMapping(value ="jiezhang",method = RequestMethod.GET)
    public String toSubmitOrder(){
        return "site/submit_order";
    }

    @Transactional
    @RequestMapping(value ="submitOrder",method = RequestMethod.GET)
    public String submitOrder(HttpServletRequest request){
        ArrayList<TbMovieInfo> tbMovieInfoList = (ArrayList<TbMovieInfo>)request.getSession().getAttribute("tbMovieInfoList");
        ArrayList<String> numList = (ArrayList<String>) request.getSession().getAttribute("numList");
        ArrayList<String> totalList = (ArrayList<String>) request.getSession().getAttribute("totalList");
        ArrayList<String> seatList = (ArrayList<String>) request.getSession().getAttribute("seatList");
        TbUser user = (TbUser)request.getSession().getAttribute("user");

        int totalPrice=0;
        for(int i=0;i<totalList.size();i++){
            totalPrice+=Integer.parseInt(totalList.get(i));
        }

        /**
         * 新增订单
         */
        TbOrder tbOrder = new TbOrder();
        tbOrder.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        tbOrder.setPayWay(request.getParameter("payway"));
        tbOrder.setUserId(user.getId());
        tbOrder.setOrderNum(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        tbOrder.setTotalFee(totalPrice);
        tbOrder.setStatus(false);

        tbOrderService.insert(tbOrder);

        /**
         * 新增详细订单
         */
        ArrayList<TbOrderItem> tbOrderItems = new ArrayList<>();
        for(int i=0;i<tbMovieInfoList.size();i++){
            TbOrderItem tbOrderItem = new TbOrderItem();
            tbOrderItem.setMovieId(tbMovieInfoList.get(i).getId());
            tbOrderItem.setMovieName(tbMovieInfoList.get(i).getName());
            tbOrderItem.setNum(Integer.parseInt(numList.get(i)));
            tbOrderItem.setOrderId(tbOrder.getOrderNum());
            tbOrderItem.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            tbOrderItem.setPrice(tbMovieInfoList.get(i).getPrice());
            tbOrderItem.setUsername(user.getUsername());
            tbOrderItem.setTotalFee(Long.parseLong(totalList.get(i)));
            tbOrderItems.add(tbOrderItem);
        }
        tbOrderItemService.insertBatch(tbOrderItems);

        /**
         * 新增座位表项
         */
        TbSeat tbSeat = new TbSeat();
        for(int i=0;i<seatList.size();i++){
            String s = seatList.get(i);
            String[] strings = s.split(",");
            for (String string : strings) {
                String[] s1 = string.split("_");
                int row=Integer.parseInt(s1[0]);
                int col=Integer.parseInt(s1[1]);
                tbSeat.setCol(col);
                tbSeat.setRow(row);
                tbSeat.setOrderItemOrderId(Long.parseLong(tbOrderItems.get(i).getOrderId()));
                tbSeat.setMovieId(tbMovieInfoList.get(i).getId());
                tbSeatService.insert(tbSeat);
            }
        }

        request.getSession().removeAttribute("tbMovieInfoList");
        request.getSession().removeAttribute("numList");
        request.getSession().removeAttribute("totalList");
        request.getSession().removeAttribute("seatList");
        request.getSession().setAttribute("tbOrder",tbOrder);

        return "site/order_detail";
    }

    @RequestMapping(value ="myOrder",method = RequestMethod.GET)
    public String orderList(HttpServletRequest request){
        List<TbOrder> tbOrders = tbOrderService.selectAll();
        request.getSession().setAttribute("tbOrders",tbOrders);
        return "site/order_list";
    }

}
