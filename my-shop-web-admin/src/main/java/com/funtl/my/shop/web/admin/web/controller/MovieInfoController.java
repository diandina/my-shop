package com.funtl.my.shop.web.admin.web.controller;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.domain.TbMovieInfo;
import com.funtl.my.shop.web.admin.service.TbMovieInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

/**
 * 电影类别管理
 */
@Controller
@RequestMapping(value="movieInfo")
public class MovieInfoController {
    @Autowired
    private TbMovieInfoService tbMovieInfoService;

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
     * 跳转电影信息表单页
     * @param model
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        List<TbMovieInfo> list = tbMovieInfoService.selectAll();
        model.addAttribute("tbMovieInfos", list);
        return "movieInfo_list";
    }

    /**
     * 跳转信息表单页
     *
     * @return
     */
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(TbMovieInfo tbMovieInfo) {
        return "movieInfo_form";
    }

    /**
     * 保存
     *
     * @param
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbMovieInfo tbMovieInfo, Model model, RedirectAttributes redirectAttributes) {
        String showTime = tbMovieInfo.getShowTime();
        String introduce = tbMovieInfo.getIntroduce();
        BaseResult baseResult = tbMovieInfoService.save(tbMovieInfo);
        if (baseResult.getStatus() == 200) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/movieInfo/list";
        } else {
            model.addAttribute("baseResult", baseResult);
            return form(tbMovieInfo);
        }
    }
    /**
     * 删除
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deleteById",method = RequestMethod.POST)
    public BaseResult delete(Long ids){
        BaseResult baseResult=null;
        if(ids!=null){
            tbMovieInfoService.deleteById(ids);
            baseResult=BaseResult.success("删除内容成功");
        }
        else{
            baseResult=BaseResult.fail("删除内容失败");
        }
        return baseResult;
    }
}
