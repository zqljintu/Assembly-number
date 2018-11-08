package com.zql.app_ji.Prestener;

import com.zql.app_ji.Bean.DetailDoubanMovie;
import com.zql.app_ji.Bean.DoubanMovie;
import com.zql.app_ji.Bean.Entity.MovieEntity;
import com.zql.app_ji.Bean.InterfaceState;
import com.zql.app_ji.Model.DatabaseJi;
import com.zql.app_ji.Model.DatabasejiImp;
import com.zql.app_ji.Model.GetDoubangsonModel;
import com.zql.app_ji.Model.GetDoubangsonModelImp;
import com.zql.app_ji.Model.UserSeting;
import com.zql.app_ji.View.Activitys.DoubanMovieActivityImp;
import com.zql.app_ji.View.Fragments.MovieFragmentImp;
import com.zql.app_ji.View.Fragments.Movie_intheatersFragmentImp;
import com.zql.app_ji.View.Fragments.Movie_topFragmentImp;

public class PrestenerMovieFragment implements PrestenerMovieFragmentImp {
    private Movie_intheatersFragmentImp movie_intheatersFragmentImp;
    private Movie_topFragmentImp movie_topFragmentImp;
    private GetDoubangsonModelImp getDoubangsonModelImp;
    private DoubanMovieActivityImp doubanMovieActivityImp;
    private MovieFragmentImp movieFragmentImp;
    private DatabasejiImp databasejiImp;

    public PrestenerMovieFragment(MovieFragmentImp movieFragmentImp){
        this.movieFragmentImp=movieFragmentImp;
    }
    public PrestenerMovieFragment(Movie_intheatersFragmentImp mmovie_intheatersFragmentImp){
        this.movie_intheatersFragmentImp=mmovie_intheatersFragmentImp;
        this.getDoubangsonModelImp=new GetDoubangsonModel(this);
        databasejiImp=new DatabaseJi(movie_intheatersFragmentImp.getBaseContext());
    }
    public PrestenerMovieFragment(Movie_topFragmentImp mmovie_topFragmentImp){
        this.movie_topFragmentImp=mmovie_topFragmentImp;
        this.getDoubangsonModelImp=new GetDoubangsonModel(this);
        databasejiImp=new DatabaseJi(mmovie_topFragmentImp.getBaseContext());
    }
    public PrestenerMovieFragment(DoubanMovieActivityImp mdoubanMovieActivityImp){
        this.doubanMovieActivityImp=mdoubanMovieActivityImp;
        this.getDoubangsonModelImp=new GetDoubangsonModel(this);
        databasejiImp=new DatabaseJi(mdoubanMovieActivityImp.getBasecontext());
    }

    /**
     * 最新热映界面接口实现
     */
    @Override
    public void getDoubanMoviefromDoubanAPI() {
        if (movie_intheatersFragmentImp!=null){
            getDoubangsonModelImp.getMoviefromDoubanAPI();
        }
    }

    @Override
    public void setDoubanMovietoRecyclerView(DoubanMovie doubanMovie) {
        if (movie_intheatersFragmentImp!=null){
            movie_intheatersFragmentImp.setRecyclerItemonPagefromDouban(doubanMovie);
        }
    }

    @Override
    public void setErrorMessageOnFragment() {
        if (movie_intheatersFragmentImp!=null){
            movie_intheatersFragmentImp.setErrorMessageonFragment("testtest");
        }
    }

    @Override
    public void setNightStateBackgroundtoIntheaters() {
        UserSeting userSeting=(UserSeting)movie_intheatersFragmentImp.getbaseapplication();
        movie_intheatersFragmentImp.setTheNightstateonFragment(userSeting.getInterfaceState());
    }

    @Override
    public InterfaceState getIntheatersInterfacestatefromUserseting() {
        UserSeting userSeting=(UserSeting)movie_intheatersFragmentImp.getbaseapplication();
        return userSeting.getInterfaceState();
    }

    /**
     * 关于top界面的接口实现
     */
    @Override
    public void getDoubantopMoviefromDoubanAPI() {
        if (movie_topFragmentImp!=null){
            getDoubangsonModelImp.getMovie_top_fromDoubanAPI();
        }
    }

    @Override
    public void getMoreDoubantopMoviefromDoubanAPI(int start, int count) {
        if (movie_topFragmentImp!=null){
            getDoubangsonModelImp.getMoreMovie_top_fromDoubanAPI(start,count);
        }
    }

    @Override
    public void setDoubantopMovietoRecyclerView(DoubanMovie doubanMovie) {
        if (movie_topFragmentImp!=null){
            movie_topFragmentImp.setRecyclerItemonPagefromDouban(doubanMovie);
        }
    }

    @Override
    public void setNightStateBackgroundtoTop() {
        UserSeting userSeting=(UserSeting)movie_topFragmentImp.getbaseapplication();
        movie_topFragmentImp.setTheNightstateonFragment(userSeting.getInterfaceState());
    }

    @Override
    public InterfaceState getTopInterfacestatefromUserseting() {
        UserSeting userSeting=(UserSeting)movie_topFragmentImp.getbaseapplication();
        return userSeting.getInterfaceState();
    }

    @Override
    public void setErrorMessageOntopFragment() {
        if (movie_topFragmentImp!=null){
            movie_topFragmentImp.setErrorMessageonFragment("test");
        }
    }
    /**
     * 关于详情界面接口的实现
     */
    @Override
    public void getDetailDoubanMoviefromDoubanAPI(String id) {
        getDoubangsonModelImp.getDetailDoubanMoviefromAPI(id);
    }

    @Override
    public void setDetailDoubanMovietoActivity(DetailDoubanMovie detailDoubanMovie) {
        doubanMovieActivityImp.setDetailDoubanMovieonActivity(detailDoubanMovie);
    }

    @Override
    public void setErrorMessagetoActivity() {

    }
    /**
     * 收藏影片到数据库
     */
    @Override
    public void addFavoritetoDataBase(DoubanMovie.SubjectsBean subjectsBean) {
        MovieEntity movieEntity=new MovieEntity();
        movieEntity.setTitle(subjectsBean.getTitle());
        movieEntity.setMovie_id(subjectsBean.getId());
        databasejiImp.insertMovieEntitytoDB(movieEntity);
    }
    /**
     * 设置夜间模式于MovieFragment
     */
    @Override
    public void setNightStateBackgroundtoFragment() {
        UserSeting userSeting=(UserSeting)movieFragmentImp.getbaseapplication();
        movieFragmentImp.setTheNightstatefromUserseting(userSeting.getInterfaceState());
    }

    @Override
    public InterfaceState getActivityInterfacefromUserting() {
        UserSeting userSeting=(UserSeting)doubanMovieActivityImp.getbaseapplication();
        return userSeting.getInterfaceState();
    }
}
