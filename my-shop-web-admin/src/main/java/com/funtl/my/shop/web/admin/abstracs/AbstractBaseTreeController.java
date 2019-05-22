package com.funtl.my.shop.web.admin.abstracs;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;
import com.funtl.my.shop.commons.persistence.BaseEntity;
import com.funtl.my.shop.commons.persistence.BaseTreeEntity;
import com.funtl.my.shop.commons.persistence.BaseTreeService;
import com.funtl.my.shop.domain.TbContentCategory;
import com.funtl.my.shop.domain.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public abstract class AbstractBaseTreeController<T extends BaseTreeEntity,S extends BaseTreeService<T>>{

    @Autowired
    protected S service;

    /**
     * 跳转列表页
     * @param model
     * @return
     */
    public abstract String list(Model model);

    /**
     * 跳转表单页
     * @param tbContentCategory
     * @return
     */
    public abstract String form(TbContentCategory tbContentCategory);

    /**
     * 保存
     * @param tbContentCategory
     * @param model
     * @param redirectAttributes
     * @return
     */
    public abstract String save(T entity,Model model,RedirectAttributes redirectAttributes);

    /**
     * 树形结构
     * @param id
     * @return
     */
    public abstract List<T> treeData(Long id);

}
