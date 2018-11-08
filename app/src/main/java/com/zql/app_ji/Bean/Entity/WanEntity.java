package com.zql.app_ji.Bean.Entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class WanEntity {
    @Id
    private Long Id;
    @NotNull
    private String title;
    @NotNull
    private String wan_url;
    @Generated(hash = 2082890547)
    public WanEntity(Long Id, @NotNull String title, @NotNull String wan_url) {
        this.Id = Id;
        this.title = title;
        this.wan_url = wan_url;
    }
    @Generated(hash = 1620231465)
    public WanEntity() {
    }
    public Long getId() {
        return this.Id;
    }
    public void setId(Long Id) {
        this.Id = Id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getWan_url() {
        return this.wan_url;
    }
    public void setWan_url(String wan_url) {
        this.wan_url = wan_url;
    }
}
