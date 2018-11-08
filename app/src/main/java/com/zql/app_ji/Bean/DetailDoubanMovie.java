package com.zql.app_ji.Bean;

import java.util.List;

public class DetailDoubanMovie {
    /**
     * rating : {"max":10,"average":8.1,"stars":"40","min":0}
     * reviews_count : 1931
     * wish_count : 260859
     * douban_site :
     * year : 2018
     * images : {"small":"http://img7.doubanio.com/view/photo/s_ratio_poster/public/p2535096871.webp","large":"http://img7.doubanio.com/view/photo/s_ratio_poster/public/p2535096871.webp","medium":"http://img7.doubanio.com/view/photo/s_ratio_poster/public/p2535096871.webp"}
     * alt : https://movie.douban.com/subject/26425063/
     * id : 26425063
     * mobile_url : https://movie.douban.com/subject/26425063/mobile
     * title : 无双
     * do_count : null
     * share_url : http://m.douban.com/movie/subject/26425063
     * seasons_count : null
     * schedule_url : https://movie.douban.com/subject/26425063/cinema/
     * episodes_count : null
     * countries : ["中国大陆","香港"]
     * genres : ["剧情","动作","犯罪"]
     * collect_count : 438556
     * casts : [{"alt":"https://movie.douban.com/celebrity/1044899/","avatars":{"small":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p205.webp","large":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p205.webp","medium":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p205.webp"},"name":"周润发","id":"1044899"},{"alt":"https://movie.douban.com/celebrity/1041390/","avatars":{"small":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p49475.webp","large":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p49475.webp","medium":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p49475.webp"},"name":"郭富城","id":"1041390"},{"alt":"https://movie.douban.com/celebrity/1016668/","avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p146.webp","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p146.webp","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p146.webp"},"name":"张静初","id":"1016668"},{"alt":"https://movie.douban.com/celebrity/1325753/","avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1356356549.76.webp","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1356356549.76.webp","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1356356549.76.webp"},"name":"冯文娟","id":"1325753"}]
     * current_season : null
     * original_title : 無雙
     * summary : 《无双》讲述了以代号“画家”（周润发 饰）为首的犯罪团伙，掌握了制造伪钞技术，难辨真伪，并在全球进行交易获取利益，引起警方高度重视。然而“画家”和其他成员的身份一直成谜，警方的破案进度遭受到了前所未有的挑战。在关键时刻，擅长绘画的李问（郭富城 饰）打开了破案的突破口，而“画家”的真实身份却让众人意想不到……
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1014716/","avatars":{"small":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p3555.webp","large":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p3555.webp","medium":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p3555.webp"},"name":"庄文强","id":"1014716"}]
     * comments_count : 94258
     * ratings_count : 238550
     * aka : ["Project Gutenberg","Mo seung"]
     */

    private RatingBean rating;
    private int reviews_count;
    private int wish_count;
    private String douban_site;
    private String year;
    private ImagesBean images;
    private String alt;
    private String id;
    private String mobile_url;
    private String title;
    private Object do_count;
    private String share_url;
    private Object seasons_count;
    private String schedule_url;
    private Object episodes_count;
    private int collect_count;
    private Object current_season;
    private String original_title;
    private String summary;
    private String subtype;
    private int comments_count;
    private int ratings_count;
    private List<String> countries;
    private List<String> genres;
    private List<CastsBean> casts;
    private List<DirectorsBean> directors;
    private List<String> aka;

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public int getReviews_count() {
        return reviews_count;
    }

    public void setReviews_count(int reviews_count) {
        this.reviews_count = reviews_count;
    }

    public int getWish_count() {
        return wish_count;
    }

    public void setWish_count(int wish_count) {
        this.wish_count = wish_count;
    }

    public String getDouban_site() {
        return douban_site;
    }

    public void setDouban_site(String douban_site) {
        this.douban_site = douban_site;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getDo_count() {
        return do_count;
    }

    public void setDo_count(Object do_count) {
        this.do_count = do_count;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public Object getSeasons_count() {
        return seasons_count;
    }

    public void setSeasons_count(Object seasons_count) {
        this.seasons_count = seasons_count;
    }

    public String getSchedule_url() {
        return schedule_url;
    }

    public void setSchedule_url(String schedule_url) {
        this.schedule_url = schedule_url;
    }

    public Object getEpisodes_count() {
        return episodes_count;
    }

    public void setEpisodes_count(Object episodes_count) {
        this.episodes_count = episodes_count;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public Object getCurrent_season() {
        return current_season;
    }

    public void setCurrent_season(Object current_season) {
        this.current_season = current_season;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(int ratings_count) {
        this.ratings_count = ratings_count;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<CastsBean> getCasts() {
        return casts;
    }

    public void setCasts(List<CastsBean> casts) {
        this.casts = casts;
    }

    public List<DirectorsBean> getDirectors() {
        return directors;
    }

    public void setDirectors(List<DirectorsBean> directors) {
        this.directors = directors;
    }

    public List<String> getAka() {
        return aka;
    }

    public void setAka(List<String> aka) {
        this.aka = aka;
    }

    public static class RatingBean {
        /**
         * max : 10
         * average : 8.1
         * stars : 40
         * min : 0
         */

        private int max;
        private double average;
        private String stars;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public double getAverage() {
            return average;
        }

        public void setAverage(double average) {
            this.average = average;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }

    public static class ImagesBean {
        /**
         * small : http://img7.doubanio.com/view/photo/s_ratio_poster/public/p2535096871.webp
         * large : http://img7.doubanio.com/view/photo/s_ratio_poster/public/p2535096871.webp
         * medium : http://img7.doubanio.com/view/photo/s_ratio_poster/public/p2535096871.webp
         */

        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }

    public static class CastsBean {
        /**
         * alt : https://movie.douban.com/celebrity/1044899/
         * avatars : {"small":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p205.webp","large":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p205.webp","medium":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p205.webp"}
         * name : 周润发
         * id : 1044899
         */

        private String alt;
        private AvatarsBean avatars;
        private String name;
        private String id;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarsBean getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBean avatars) {
            this.avatars = avatars;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class AvatarsBean {
            /**
             * small : http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p205.webp
             * large : http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p205.webp
             * medium : http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p205.webp
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }

    public static class DirectorsBean {
        /**
         * alt : https://movie.douban.com/celebrity/1014716/
         * avatars : {"small":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p3555.webp","large":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p3555.webp","medium":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p3555.webp"}
         * name : 庄文强
         * id : 1014716
         */

        private String alt;
        private AvatarsBeanX avatars;
        private String name;
        private String id;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarsBeanX getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBeanX avatars) {
            this.avatars = avatars;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class AvatarsBeanX {
            /**
             * small : http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p3555.webp
             * large : http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p3555.webp
             * medium : http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p3555.webp
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }
}
