package com.funtl.my.shop.web.admin.web.controller;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;
import com.funtl.my.shop.domain.TbContent;
import com.funtl.my.shop.web.admin.abstracs.AbstractBaseController;
import com.funtl.my.shop.web.admin.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 内容管理
 */
@Controller
@RequestMapping(value="content")
public class ContentController extends AbstractBaseController<TbContent,TbContentService> {

    @Autowired
    private TbContentService tbContentService;

    @ModelAttribute
    public TbContent getTbContent(Long id){
        TbContent tbContent=null;
        //id不为空则从数据库获取
        if(id!=null){
            tbContent=tbContentService.getById(id);
        }
        else{
            tbContent=new TbContent();
        }
        return tbContent;
    }

    /**
     * 跳转到用户列表页
     * @return
     */
    @Override
    @RequestMapping(value="list",method= RequestMethod.GET)
    public String list(){
        return "content_list";
    }

    /**
     * 跳转内容表单页
     * @return
     */
    @Override
    @RequestMapping(value="form",method=RequestMethod.GET)
    public String form(){
        return "content_form";
    }

    @Override
    @RequestMapping(value="save",method=RequestMethod.POST)
    public String save(TbContent tbContent, RedirectAttributes redirectAttributes, Model model){
        BaseResult baseResult = tbContentService.save(tbContent);
        //保存成功
        if(baseResult.getStatus()==200){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/content/list";
        }
        //保存失败
        else{
            model.addAttribute("baseResult",baseResult);
            return "content_form";
        }
    }


    /**
     * 批量删除用户信息
     * @param ids
     * @return
     */
    @Override
    @ResponseBody
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public BaseResult delete(String ids){
        BaseResult baseResult=null;
        if(StringUtils.isNotBlank(ids)){
            String[] idArray=ids.split(",");
            tbContentService.deleteMulti(idArray);
            baseResult=BaseResult.success("删除内容成功");
            System.out.println("提示消息"+baseResult.getMessage());
        }
        else{
            baseResult=BaseResult.fail("删除内容失败");
        }
        return baseResult;
    }


    /**
     * 显示用户详情
     * @param tbUser
     * @return
     */
    @Override
    @RequestMapping(value="detail",method= RequestMethod.GET)
    public String detail(TbContent tbContent){
        return "content_detail";
    }
}
