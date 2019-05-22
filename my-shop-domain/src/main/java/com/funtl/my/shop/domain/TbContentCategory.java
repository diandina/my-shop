package com.funtl.my.shop.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.funtl.my.shop.commons.persistence.BaseEntity;
import com.funtl.my.shop.commons.persistence.BaseTreeEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
*@Description
*@Author diandian
*@param 分类管理
*@return
*/
@Data
public class TbContentCategory extends BaseTreeEntity {
    @JsonProperty(value="parent.id")
    private Long id;
    @Length(min=1,max=20,message="分类名称必须介于 1-20 位之间")
    private String name;
    private Integer status;
    @NotNull(message = "排序不能为空")
    private Integer sortOrder;
    private Boolean isParent;
    private TbContentCategory parent;
}
