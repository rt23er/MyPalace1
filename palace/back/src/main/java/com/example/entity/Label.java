package com.example.entity;

import lombok.Data;

/**
 * 标签
 */
@Data
public class Label {
    /** ID */
    private Integer id;
    /** 分类名称 */
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
