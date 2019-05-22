package com.funtl.my.shop.web.admin.service.impl;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;
import com.funtl.my.shop.commons.utils.RegexpUtils;
import com.funtl.my.shop.commons.validator.BeanValidator;
import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.abstracs.AbstractBaseServiceImpl;
import com.funtl.my.shop.web.admin.dao.TbUserDao;
import com.funtl.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class TbUserServiceImpl extends AbstractBaseServiceImpl<TbUser,TbUserDao> implements TbUserService {

    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbUser tbUser) {
        String validator = BeanValidator.validator(tbUser);
        //格式验证不通过
        if(validator!=null){
            return BaseResult.fail(validator);
        }
        if(!validate(tbUser)){
            return BaseResult.fail("邮箱地址不能重复");
        }
        //格式验证通过
        tbUser.setUpdated(new Date());
        //新增用户
        if(tbUser.getId()==null){
            tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
            tbUser.setCreated(new Date());
            dao.insert(tbUser);
        }

        //编辑用户
        else{
            update(tbUser);
        }
        return BaseResult.success("保存用户信息成功");

    }

    /**
     * 校验邮箱是否重复
     * @param tbUser
     * @return
     */
    public boolean validate(TbUser tbUser){
        TbUser user = dao.getByEmail(tbUser.getEmail());
        if(user!=null&&user.getId()!=tbUser.getId()){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public TbUser login(String email, String password) {
        TbUser tbUser = dao.getByEmail(email);
        if(tbUser!=null){
            //明文密码加密
            String md5Password= DigestUtils.md5DigestAsHex(password.getBytes());

            //判断加密后的密码和数据库中存放的密码是否匹配，匹配则表示允许登录
            if(md5Password.equals(tbUser.getPassword())){
                return tbUser;
            }
        }
        return null;
    }

    public TbUser getById(Long id){
        return dao.getById(id);
    };

}
