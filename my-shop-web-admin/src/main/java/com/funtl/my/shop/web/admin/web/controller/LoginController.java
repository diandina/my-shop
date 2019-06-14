package com.funtl.my.shop.web.admin.web.controller;

import com.funtl.my.shop.commons.constant.ConstantUtils;
import com.funtl.my.shop.commons.utils.CookieUtils;
import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    private static final String COOKIE_NAME_USER_INFO="userInfo";
    @Autowired
    private TbUserService tbUserService;

    @RequestMapping(value={"login"},method = RequestMethod.GET)
    public String login(HttpServletRequest req){
        String userInfo = CookieUtils.getCookieValue(req, COOKIE_NAME_USER_INFO);

        if(!StringUtils.isBlank(userInfo)){
            String[] userInfoArray=userInfo.split(":");
            String email=userInfoArray[0];
            String password=userInfoArray[1];
            req.setAttribute("email",email);
            req.setAttribute("password",password);
            req.setAttribute("isRemember",true);
        }
        return "login";
    }

    /**
     * 登陆逻辑
     * @param email
     * @param password
     * @param req
     * @return
     */
    @RequestMapping(value="login",method = RequestMethod.POST)
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
            return login(httpServletRequest);
        }
        //登录成功
        else{
            if(tbUser.isAdmin()){
                if(isRemember){
                    CookieUtils.setCookie(httpServletRequest,httpServletResponse,COOKIE_NAME_USER_INFO,String.format("%s:%s",email,password),7*24*60*60);
                }else{
                    CookieUtils.deleteCookie(httpServletRequest,httpServletResponse,COOKIE_NAME_USER_INFO);
                    httpServletRequest.setAttribute("isRemember",false);
                }
                //将登陆信息放入会话
                httpServletRequest.getSession().setAttribute(ConstantUtils.SESSION_USER,tbUser);
                return "redirect:/main";
            }else{
                model.addAttribute("message","非管理员禁止登陆");
                return login(httpServletRequest);
            }
        }

    }

    /*注销*/
    @RequestMapping(value="logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest httpServletRequest){
        httpServletRequest.getSession().invalidate();
        return login(httpServletRequest);
    }
}
