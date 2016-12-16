package com.pf.dllo.food.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dllo on 2016/12/9.
 */

@Entity
public class SearchBean {
    @Id
    private Long id;
    private String name;

    @Generated(hash = 118660477)
    public SearchBean(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 562045751)
    public SearchBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
