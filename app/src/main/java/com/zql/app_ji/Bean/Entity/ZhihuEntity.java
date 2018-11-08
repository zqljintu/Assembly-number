package com.zql.app_ji.Bean.Entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ZhihuEntity {
    @Id
    private Long Id;
    @NotNull
    private String title;
    @NotNull
    private String note_id;
    @Generated(hash = 1451357055)
    public ZhihuEntity(Long Id, @NotNull String title, @NotNull String note_id) {
        this.Id = Id;
        this.title = title;
        this.note_id = note_id;
    }
    @Generated(hash = 1971495100)
    public ZhihuEntity() {
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
    public String getNote_id() {
        return this.note_id;
    }
    public void setNote_id(String note_id) {
        this.note_id = note_id;
    }
}
