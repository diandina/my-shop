package com.funtl.my.shop.web.admin.web.controller;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.domain.TbMessage;
import com.funtl.my.shop.domain.TbMovieInfo;
import com.funtl.my.shop.web.admin.service.TbMessageService;
import com.funtl.my.shop.web.admin.service.TbMovieInfoService;
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
 * 电影类别管理
 */
@Controller
@RequestMapping(value="message")
public class MessageController {
    @Autowired
    private TbMessageService tbMessageService;

    @Autowired
    private TbMovieInfoService tbMovieInfoService;

    @ModelAttribute
    public TbMessage getTbMessage(Long id){
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


    /**
     * 跳转电影信息表单页
     * @param model
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        List<TbMessage> list = tbMessageService.selectAll();
        for (TbMessage tbMessage : list) {
            TbMovieInfo tbMovieInfo = tbMovieInfoService.getById(tbMessage.getMovieId());
            if(tbMovieInfo!=null){
                tbMessage.setMovieName(tbMovieInfo.getName());
            }
        }
        model.addAttribute("tbMessages", list);
        return "message_list";
    }

    /**
     * 跳转信息表单页
     *
     * @return
     */
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(TbMessage tbMessage) {
        return "message_form";
    }

    /**
     * 保存
     *
     * @param tbCatelog
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbMessage tbMessage, Model model, RedirectAttributes redirectAttributes) {
        BaseResult baseResult = tbMessageService.save(tbMessage);
        if (baseResult.getStatus() == 200) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/message/list";
        } else {
            model.addAttribute("baseResult", baseResult);
            return form(tbMessage);
        }
    }
    /**
     * 删除
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deleteById",method = RequestMethod.POST)
    public BaseResult delete(Long ids){
        return tbMessageService.deleteById(ids);
    }
}
