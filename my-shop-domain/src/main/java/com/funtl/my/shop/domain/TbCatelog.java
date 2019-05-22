package com.funtl.my.shop.domain;

import javax.validation.constraints.NotNull;

public class TbCatelog {
    private Long id;
    @NotNull(message = "电影类别不能为空")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Boolean del;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Boolean getDel() {
        return del;
    }

    public void setDel(Boolean del) {
        this.del = del;
    }
}