package com.zql.app_ji.Bean.Entity;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class MovieEntity {
    @Id
    private Long Id;
    @NotNull
    private String title;
    @NotNull
    private String movie_id;
    @Generated(hash = 1262088786)
    public MovieEntity(Long Id, @NotNull String title, @NotNull String movie_id) {
        this.Id = Id;
        this.title = title;
        this.movie_id = movie_id;
    }
    @Generated(hash = 1226161063)
    public MovieEntity() {
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
    public String getMovie_id() {
        return this.movie_id;
    }
    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
    }

}
