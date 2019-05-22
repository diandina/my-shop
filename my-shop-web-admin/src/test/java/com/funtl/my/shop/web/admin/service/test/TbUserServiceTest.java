/*
package com.funtl.my.shop.web.admin.service.test;

import com.funtl.my.shop.domain.TbUser;
import com.funtl.my.shop.web.admin.service.TbUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml","classpath:spring-context-druid.xml","classpath:spring-context-mybatis.xml"})
public class TbUserServiceTest {
    @Autowired
    private TbUserService tbUserService;

    @Test
    public void testSelectAll(){
        List<TbUser> tbUsers=tbUserService.selectAll();
        for(TbUser tbUser:tbUsers){
            System.out.println(tbUser.getUsername());
        }
    }

    @Test
    public void testInsert(){
        TbUser tbUser=new TbUser();
        tbUser.setUsername("Lusifer1");
        tbUser.setPhone("15822382282");
        tbUser.setEmail("lusifer@funtl.com");
        tbUser.setPassword(DigestUtils.md5DigestAsHex("12345".getBytes()));
        tbUser.setUpdated(new Date());
        tbUser.setCreated(new Date());

        tbUserService.save(tbUser);
    }

    @Test
    public void testDelete(){
        tbUserService.delete(33L);
    }

    @Test
    public void testGetById(){
        TbUser tbUser = tbUserService.getById(35L);
        System.out.println(tbUser.getUsername());
    }

    @Test
    public void testUpdate(){
        TbUser tbUser = tbUserService.getById(22l);
        tbUser.setUsername("tidy2");
        tbUserService.update(tbUser);
    }


    @Test
    public void testMD5(){
        System.out.println(DigestUtils.md5DigestAsHex("123456".getBytes()));
    }
}
*/
