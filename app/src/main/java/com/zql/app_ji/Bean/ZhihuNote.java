package com.zql.app_ji.Bean;

import java.util.List;

public class ZhihuNote {

    /**
     * date : 20181007
     * stories : [{"images":["https://pic2.zhimg.com/v2-1d70090d2749b6c74c4290f2d2331c91.jpg"],"type":0,"id":9697683,"ga_prefix":"100720","title":"我也是著名短腿狗，怎么风光全让柯基占了，哼"},{"images":["https://pic3.zhimg.com/v2-9b69d7c3155ad351d33ace16ed4a7aa2.jpg"],"type":0,"id":9695645,"ga_prefix":"100719","title":"今晚追番 · 这部定级 21+ 的新番，心智成熟我才敢推荐给你"},{"images":["https://pic1.zhimg.com/v2-6bad4d873c5b996dd57a91ba40597984.jpg"],"type":0,"id":9697636,"ga_prefix":"100716","title":"小说真的会影响你的三观吗？"},{"title":"每周一吸 · 你为什么养猫？","ga_prefix":"100713","images":["https://pic4.zhimg.com/v2-041c8d21d7ae191f260c3e6a91e693f7.jpg"],"multipic":true,"type":0,"id":9697615},{"images":["https://pic4.zhimg.com/v2-355076f5f5d1f2106a71a27c5548f55b.jpg"],"type":0,"id":9697897,"ga_prefix":"100712","title":"大误 · 加油吧，白素贞！"},{"images":["https://pic1.zhimg.com/v2-8857b90b31d3d4da21111d493efbd1bc.jpg"],"type":0,"id":9697418,"ga_prefix":"100710","title":"作为面试观察者，我来谈谈很多人面试中的三大误区"},{"title":"游戏不断电 · 这款空前绝后的大杂烩游戏，堪称中国同人界的「头号玩家」","ga_prefix":"100708","images":["https://pic1.zhimg.com/v2-c60a50dc50c91fc8c3b05077558399f8.jpg"],"multipic":true,"type":0,"id":9695905},{"images":["https://pic2.zhimg.com/v2-390c54d4b76e90e6f148a6eb8e1f7445.jpg"],"type":0,"id":9697868,"ga_prefix":"100707","title":"摆事实讲道理，为什么今年陈列平教授与诺奖失之交臂？"},{"images":["https://pic4.zhimg.com/v2-3c4e1d0c0dcdf9e16886237d9b340033.jpg"],"type":0,"id":9696971,"ga_prefix":"100706","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic3.zhimg.com/v2-9ddbade8630a2acc624feaa6118fb58a.jpg","type":0,"id":9697683,"ga_prefix":"100720","title":"我也是著名短腿狗，怎么风光全让柯基占了，哼"},{"image":"https://pic4.zhimg.com/v2-896f54864061f248cc7ae392ff38c79b.jpg","type":0,"id":9697555,"ga_prefix":"100608","title":"游戏不断电 · 你这个刚跳出来的家伙，还记得自己曾死在我手里吗？"},{"image":"https://pic1.zhimg.com/v2-2460378992e4776a68134d6749e61180.jpg","type":0,"id":9697831,"ga_prefix":"100607","title":"癌症免疫疗法得了诺奖，这难道不是莆田系的\u2026\u2026（你想多了）"},{"image":"https://pic4.zhimg.com/v2-f9c8b19176456bc6cd770e8bd82dc253.jpg","type":0,"id":9697145,"ga_prefix":"100507","title":"我用文本挖掘分析了 5 万首《全唐诗》，发现了这些秘密"},{"image":"https://pic3.zhimg.com/v2-b8c5b857410080ffefd9218dae4f7dd6.jpg","type":0,"id":9697590,"ga_prefix":"100413","title":"被国人诟病的海外中餐馆，可能远比我们想象的更伟大"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * images : ["https://pic2.zhimg.com/v2-1d70090d2749b6c74c4290f2d2331c91.jpg"]
         * type : 0
         * id : 9697683
         * ga_prefix : 100720
         * title : 我也是著名短腿狗，怎么风光全让柯基占了，哼
         * multipic : true
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private boolean multipic;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * image : https://pic3.zhimg.com/v2-9ddbade8630a2acc624feaa6118fb58a.jpg
         * type : 0
         * id : 9697683
         * ga_prefix : 100720
         * title : 我也是著名短腿狗，怎么风光全让柯基占了，哼
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
