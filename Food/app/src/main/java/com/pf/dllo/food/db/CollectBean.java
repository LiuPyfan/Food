package com.pf.dllo.food.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Created by dllo on 2016/12/8.
 */
@Entity
public class CollectBean {
    @Id
    private Long id;
    private String title,url;
    private String name,nameUrl;
    @Transient
    private String beizhu;

    @Generated(hash = 678342802)
    public CollectBean(Long id, String title, String url, String name,
            String nameUrl) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.name = name;
        this.nameUrl = nameUrl;
    }

    @Generated(hash = 420494524)
    public CollectBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameUrl() {
        return nameUrl;
    }

    public void setNameUrl(String nameUrl) {
        this.nameUrl = nameUrl;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }
}
