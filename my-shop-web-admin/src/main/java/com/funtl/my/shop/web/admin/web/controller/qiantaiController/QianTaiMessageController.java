package com.funtl.my.shop.web.admin.web.controller.qiantaiController;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.domain.*;
import com.funtl.my.shop.web.admin.service.TbMessageService;
import com.funtl.my.shop.web.admin.service.TbMovieInfoService;
import com.funtl.my.shop.web.admin.service.TbOrderItemService;
import com.funtl.my.shop.web.admin.service.TbOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 电影类别管理
 */
@Controller
@Transactional
@RequestMapping(value="qiantai/movie/message")
public class QianTaiMessageController {
    @Autowired
    private TbMovieInfoService tbMovieInfoService;
    @Autowired
    private TbOrderService tbOrderService;
    @Autowired
    private TbOrderItemService tbOrderItemService;
    @Autowired
    private TbMessageService tbMessageService;

    @ModelAttribute
    public TbMessage getTbMessage(Long id,HttpServletRequest request){
        TbMessage tbMessage=null;
        //id不为空则从数据库获取
        if(id!=null){
            tbMessage=tbMessageService.getById(id);
        }
        else{
            tbMessage=new TbMessage();
        }
        return tbMessage;
    }

    @RequestMapping(value ="list",method = RequestMethod.GET)
    public String message(HttpServletRequest request){
        List<TbMessage> tbMessages = tbMessageService.selectAll();
        request.getSession().setAttribute("tbMessages",tbMessages);
        return "site/message_list";
    }

    @RequestMapping(value ="messageForm",method = RequestMethod.GET)
    public String messageReply(){
        return "site/message_form";
    }
    @RequestMapping(value ="messageFormAdd",method = RequestMethod.GET)
    public String messageAddForm(){
        return "site/message_form_add";
    }
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
        TbMessage tbMessage = tbMessageService.getById(Long.parseLong(request.getParameter("id")));
        tbMessage.setContent(request.getParameter("content"));
        BaseResult baseResult = tbMessageService.save(tbMessage);
        if (baseResult.getStatus() == 200) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            message(request);
            return "site/message_list";
        } else {
            model.addAttribute("baseResult", baseResult);
            return "site/message_form";
        }
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String messageAdd(HttpServletRequest request){
        TbMessage tbMessage = new TbMessage();
        String id = request.getParameter("movieName");
        String content = request.getParameter("content");
        TbMovieInfo tbMovieInfo = tbMovieInfoService.getById(Long.parseLong(id));
        tbMessage.setContent(content);
        tbMessage.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        tbMessage.setMovieName(tbMovieInfo.getName());
        tbMessage.setMovieId(Long.parseLong(id));
        TbUser user = (TbUser) request.getSession().getAttribute("user");
        tbMessage.setUserId(user.getId());

        tbMessageService.save(tbMessage);
        message(request);
        return "site/message_list";
    }

}
