package com.funtl.my.shop.domain;

import com.funtl.my.shop.commons.persistence.BaseEntity;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class TbContent extends BaseEntity {

    @Length(min=1,max=20,message = "标题长度介于1-20个字符之间")
    private String title;

    @Length(min=1,max=20,message = "子标题长度介于1-20个字符之间")
    private String subTitle;

    @Length(min=1,max=50,message = "标题描述长度介于1-50个字符之间")
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;

    @Length(min=1,message = "内容不可为空")
    private String content;

    @NotNull(message = "父级类目不能为空")
    private TbContentCategory tbContentCategory;
}
