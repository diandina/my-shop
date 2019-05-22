package com.funtl.my.shop.web.admin.web.controller;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.domain.*;
import com.funtl.my.shop.web.admin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * 电影订单管理
 */
@Controller
@RequestMapping(value="order")
public class OrderController {
    @Autowired
    private TbOrderService tbOrderService;
    @Autowired
    private TbOrderItemService tbOrderItemService;
    @Autowired
    private TbMovieInfoService tbMovieInfoService;
    @Autowired
    private TbUserService tbUserService;

    @ModelAttribute
    public TbOrder getTbOrder(Long id){
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
     * 跳转电影信息表单页
     * @param model
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        List<TbOrder> list = tbOrderService.selectAll();
        model.addAttribute("tbOrders", list);
        return "order_list";
    }

    /**
     * 跳转信息表单页
     *
     * @return
     */
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(TbOrder tbOrder) {
        return "order_form";
    }

    /**
     * 审核
     *
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.GET)
    public String update(TbOrder tbOrder,Model model) {
        tbOrder.setStatus(true);
        tbOrderService.update(tbOrder);
        List<TbOrder> list = tbOrderService.selectAll();
        model.addAttribute("tbOrders", list);
        return "order_list";
    }

    /**
     * 保存
     *
     * @param tbCatelog
     * @return
     */
    /*@RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbOrder tbOrder, Model model, RedirectAttributes redirectAttributes) {
        BaseResult baseResult = tbOrderService.save(tbOrder);
        if (baseResult.getStatus() == 200) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/order/list";
        } else {
            model.addAttribute("baseResult", baseResult);
            return form(tbOrder);
        }
    }*/
    /**
     * 删除
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deleteById",method = RequestMethod.POST)
    public BaseResult delete(Long ids){
        return tbOrderService.deleteById(ids);

    }

    /**
     * 显示订单详情
     * @param
     * @return
     */
    @RequestMapping(value="detail",method=RequestMethod.GET)
    public String detail(TbOrder tbOrder,Model model)
    {
        TbOrder tb= tbOrderService.getByOrderNum(tbOrder.getOrderNum());
        TbOrderItem tbOrderItem = tbOrderItemService.getByOrderId(tbOrder.getOrderNum());
        TbMovieInfo tbMovieInfo = tbMovieInfoService.getById(tb.getMovieId());
        TbUser tbUser = tbUserService.getById(tb.getUserId());
        model.addAttribute("tbOrderItem",tbOrderItem);
        model.addAttribute("tbMovieInfo",tbMovieInfo);
        model.addAttribute("tbUser",tbUser);
        return "order_detail";
    }
}
