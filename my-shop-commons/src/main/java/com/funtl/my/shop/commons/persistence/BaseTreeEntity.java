package com.funtl.my.shop.commons.persistence;

import lombok.Data;

import java.io.Serializable;
@Data
public class BaseTreeEntity<T extends BaseEntity> extends BaseEntity implements Serializable {
    private T parent;
    private Boolean isParent;
}
