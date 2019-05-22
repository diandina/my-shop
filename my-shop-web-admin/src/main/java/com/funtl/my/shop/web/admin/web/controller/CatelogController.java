package com.funtl.my.shop.web.admin.web.controller;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.domain.TbCatelog;
import com.funtl.my.shop.domain.TbContent;
import com.funtl.my.shop.web.admin.service.TbCatelogService;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping(value="movie/catelog")
public class CatelogController {
    @Autowired
    private TbCatelogService tbCatelogService;

    @ModelAttribute
    public TbCatelog getTbCatelog(Long id){
        TbCatelog tbCatelog=null;
        //id不为空则从数据库获取
        if(id!=null){
            tbCatelog=tbCatelogService.getById(id);
        }
        else{
            tbCatelog=new TbCatelog();
        }
        return tbCatelog;
    }


    /**
     * 跳转电影类别表单页
     * @param model
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        List<TbCatelog> list = tbCatelogService.selectAll();
        model.addAttribute("tbCatelogs", list);
        return "catelog_list";
    }
    /**
     * 获取电影类别数据
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "listData", method = RequestMethod.GET)
    public List<TbCatelog> list() {
        List<TbCatelog> list = tbCatelogService.selectAll();
        return list;
    }

    /**
     * 跳转内容表单页
     *
     * @return
     */
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(TbCatelog tbCatelog) {
        return "catelog_form";
    }

    /**
     * 保存
     *
     * @param tbCatelog
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbCatelog tbCatelog, Model model, RedirectAttributes redirectAttributes) {
        BaseResult baseResult = tbCatelogService.save(tbCatelog);
        if (baseResult.getStatus() == 200) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/movie/catelog/list";
        } else {
            model.addAttribute("baseResult", baseResult);
            return form(tbCatelog);
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
        BaseResult baseResult=null;
        if(ids!=null){
            tbCatelogService.deleteById(ids);
            baseResult=BaseResult.success("删除内容成功");
        }
        else{
            baseResult=BaseResult.fail("删除内容失败");
        }
        return baseResult;
    }
}
