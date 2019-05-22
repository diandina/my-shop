package com.funtl.my.shop.web.admin.web.controller;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;
import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.abstracs.AbstractBaseController;
import com.funtl.my.shop.web.admin.service.TbUserService;
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
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*@Description 用户管理
*@Author diandian
*@param
*@return
*/
@Controller
@RequestMapping(value="user")
public class UserController extends AbstractBaseController<TbUser,TbUserService> {

    @ModelAttribute
    public TbUser getTbUser(Long id){
        TbUser tbUser=null;
        //id不为空则从数据库获取
        if(id!=null){
            tbUser=service.getById(id);
        }
        else{
            tbUser=new TbUser();
        }
        return tbUser;
    }



    /**
     * 跳转用户表单页
     * @return
     */
    @Override
    @RequestMapping(value="form",method=RequestMethod.GET)
    public String form(){
        return "user_form";
    }

    /**
     * 跳转到用户列表页
     * @return
     */
    @Override
    @RequestMapping(value="list",method= RequestMethod.GET)
    public String list(){
        return "user_list";
    }

    @Override
    @RequestMapping(value="save",method=RequestMethod.POST)
    public String save(TbUser tbUser, RedirectAttributes redirectAttributes,Model model){
        BaseResult baseResult = service.save(tbUser);
        //保存成功
        if(baseResult.getStatus()==200){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/user/list";
        }
        //保存失败
        else{
            model.addAttribute("baseResult",baseResult);
            return "user_form";
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
            service.deleteMulti(idArray);
            baseResult=BaseResult.success("删除数据成功");
        }
        else{
            baseResult=BaseResult.fail("删除失败");
        }
        return baseResult;
    }

    /**
     * 分页查询
     * @param request
     * @return
     */
    @Override
    @ResponseBody
    @RequestMapping(value="page",method = RequestMethod.GET)
    public PageInfo<TbUser> page(HttpServletRequest request,TbUser tbUser){
        Map<String,Object> result=new HashMap<>();
        String strDraw=request.getParameter("draw");
        String strStart=request.getParameter("start");
        String strLength=request.getParameter("length");

        int draw=strDraw==null?0:Integer.parseInt(strDraw);
        int start=strStart==null?0:Integer.parseInt(strStart);
        int length=(strLength==null?0:Integer.parseInt(strLength))+start;

        //封装Datatables需要的结果
        PageInfo<TbUser> pageInfo = service.page(start,length,draw,tbUser);
        return pageInfo;
    }

    /**
     * 显示用户详情
     * @param tbUser
     * @return
     */
    @Override
    @RequestMapping(value="detail",method=RequestMethod.GET)
    public String detail(TbUser tbUser){
        return "user_detail";
    }

}
