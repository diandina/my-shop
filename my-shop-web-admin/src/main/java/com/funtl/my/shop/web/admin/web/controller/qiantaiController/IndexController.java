package com.funtl.my.shop.web.admin.web.controller.qiantaiController;

import com.funtl.my.shop.commons.constant.ConstantUtils;
import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;
import com.funtl.my.shop.commons.utils.CookieUtils;
import com.funtl.my.shop.domain.TbCatelog;
import com.funtl.my.shop.domain.TbMovieInfo;
import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.service.TbCatelogService;
import com.funtl.my.shop.web.admin.service.TbMovieInfoService;
import com.funtl.my.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("index")
public class IndexController {
    private static final String COOKIE_NAME_USER_INFO="userInfo";
    @Autowired
    TbUserService tbUserService;
    @Autowired
    TbMovieInfoService tbMovieInfoService;
    @Autowired
    TbCatelogService tbCatelogService;
    @ModelAttribute
    public TbUser getTbUser(Long id){
        TbUser tbUser=null;
        //id不为空则从数据库获取
        if(id!=null){
            tbUser=tbUserService.getById(id);
        }
        else{
            tbUser=new TbUser();
        }
        return tbUser;
    }
    /**
     * 跳转首页
     */
    @RequestMapping(value = {"","index"},method = RequestMethod.GET)
    public String index(){
        return "site/index";
    }

    @RequestMapping(value = {"movie/type"},method = RequestMethod.GET)
    public String index_type(){
        return "site/index_type";
    }

    /**
     * 跳转到搜索页面
     * @return
     */
    @RequestMapping(value = {"movie/search"},method = RequestMethod.GET)
    public String index_search(){
        return "site/index_search";
    }

    /**
     * 根据类型筛选电影信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="movie/selectAllByType",method = RequestMethod.GET)
    public List<TbMovieInfo> selecAllByType(HttpServletRequest request){
        long id = Long.parseLong(request.getParameter("id"));
        List<TbMovieInfo> list=tbMovieInfoService.selectByCatelogId(id);
        return list;
    }
    @ResponseBody
    @RequestMapping(value="movie/selectAllByName",method = RequestMethod.GET)
    public List<TbMovieInfo> selecAllByName(HttpServletRequest request){
        String name = request.getParameter("name");
        List<TbMovieInfo> list=tbMovieInfoService.selectByName(name);
        return list;
    }
    /**
     * 跳转首页
     */
    @RequestMapping(value ="huiyuan/form",method = RequestMethod.GET)
    public String huiyuan(){
        return "site/huiyuan_form";
    }

    @RequestMapping(value ="huiyuan/login/form",method = RequestMethod.GET)
    public String loginForm(){
        return "site/huiyuan_login";
    }

    @RequestMapping(value="huiyuan/login",method=RequestMethod.POST)
    public String login(@RequestParam(required = true)String email, @RequestParam(required = true)String password, HttpServletRequest httpServletRequest, Model model, HttpServletResponse httpServletResponse) {
        TbUser tbUser = tbUserService.login(email, password);
        boolean isRemember=httpServletRequest.getParameter("isRemember")==null?false:true;
        //System.out.println(isRemember);

        /*//用户选择不记住
        if(!isRemember){
            CookieUtils.deleteCookie(httpServletRequest,httpServletResponse,COOKIE_NAME_USER_INFO);
            httpServletRequest.setAttribute("isRemember",false);
        }*/


        //登录失败
        if(tbUser==null){
            model.addAttribute("message","用户名或密码错误，请重新输入");
            return loginForm();
        }
        //登录成功
        else{
            System.out.println(tbUser.isAdmin());
            if(isRemember){
                CookieUtils.setCookie(httpServletRequest,httpServletResponse,COOKIE_NAME_USER_INFO,String.format("%s:%s",email,password),7*24*60*60);
            }else{
                CookieUtils.deleteCookie(httpServletRequest,httpServletResponse,COOKIE_NAME_USER_INFO);
                httpServletRequest.setAttribute("isRemember",false);
            }
            //将登陆信息放入会话
            httpServletRequest.getSession().setAttribute(ConstantUtils.SESSION_USER,tbUser);
            return "redirect:/index/index";
        }

    }

    @RequestMapping(value="huiyuan/save",method=RequestMethod.POST)
    public String save(TbUser tbUser, RedirectAttributes redirectAttributes, Model model, HttpServletRequest httpServletRequest){
        BaseResult baseResult = tbUserService.save(tbUser);
        //保存成功
        if(baseResult.getStatus()==200){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            //将登陆信息放入会话
             httpServletRequest.getSession().setAttribute(ConstantUtils.SESSION_USER,tbUser);
            return "redirect:/index/index";
        }
        //保存失败
        else{
            model.addAttribute("baseResult",baseResult);
            return "site/huiyuan_form";
        }
    }

    /*注销*/
    @RequestMapping(value="huiyuan/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest httpServletRequest){
        httpServletRequest.getSession().invalidate();
        return index();
    }

    @ResponseBody
    @RequestMapping(value="movie/page",method = RequestMethod.GET)
    public PageInfo<TbMovieInfo> page(HttpServletRequest request, TbMovieInfo tbMovieInfo){
        Map<String,Object> result=new HashMap<>();
        String strDraw=request.getParameter("draw");
        String strStart=request.getParameter("start");
        String strLength=request.getParameter("length");

        int draw=strDraw==null?0:Integer.parseInt(strDraw);
        int start=strStart==null?0:Integer.parseInt(strStart);
        int length=(strLength==null?0:Integer.parseInt(strLength))+start;

        //封装Datatables需要的结果
        PageInfo<TbMovieInfo> pageInfo = tbMovieInfoService.page(start,length,draw,tbMovieInfo);
        return pageInfo;
    }
    @ResponseBody
    @RequestMapping(value="movie/selectAll",method = RequestMethod.GET)
    public List<TbMovieInfo> selecAll(){
        List<TbMovieInfo> list=tbMovieInfoService.selectAll();
        return list;
    }
    @ResponseBody
    @RequestMapping(value="catelog/selectAll",method = RequestMethod.GET)
    public List<TbCatelog> selecAllCatelog(){
        List<TbCatelog> list = tbCatelogService.selectAll();
        return list;
    }
}
