package com.test.calow.training.util;

import com.test.calow.training.ui.model.CommentEntity;
import com.test.calow.training.ui.model.DynamicEntity;
import com.test.calow.training.ui.model.FavoriteItem;
import com.test.calow.training.ui.model.PhotoInfo;
import com.test.calow.training.ui.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by calow on 18/6/4.
 */

public class DataUtil {

    public static final String[] CONTENTS = {"",
            "哈哈，18123456789,ChinaAr  http://www.ChinaAr.com;一个不错的VR网站。哈哈，ChinaAr  http://www.ChinaAr.com;一个不错的VR网站。哈哈，ChinaAr  http://www.ChinaAr.com;一个不错的VR网站。哈哈，ChinaAr  http://www.ChinaAr.com;一个不错的VR网站。",
            "VR（Virtual Reality，即虚拟现实，简称VR），是由美国VPL公司创建人拉尼尔（Jaron Lanier）在20世纪80年代初提出的。其具体内涵是：综合利用计算机图形系统和各种现实及控制等接口设备，在计算机上生成的、可交互的三维环境中提供沉浸感觉的技术。其中，计算机生成的、可交互的三维环境称为虚拟环境（即Virtual Environment，简称VE）。虚拟现实技术是一种可以创建和体验虚拟世界的计算机仿真系统的技术。它利用计算机生成一种模拟环境，利用多源信息融合的交互式三维动态视景和实体行为的系统仿真使用户沉浸到该环境中。",
            "我勒个去"};

    public static final String[] HEADIMG = {
            "http://img.wzfzl.cn/uploads/allimg/140820/co140R00Q925-14.jpg",
            "http://www.feizl.com/upload2007/2014_06/1406272351394618.png",
            "http://v1.qzone.cc/avatar/201308/30/22/56/5220b2828a477072.jpg%21200x200.jpg",
            "http://v1.qzone.cc/avatar/201308/22/10/36/521579394f4bb419.jpg!200x200.jpg",
            "http://v1.qzone.cc/avatar/201408/20/17/23/53f468ff9c337550.jpg!200x200.jpg",
            "http://cdn.duitang.com/uploads/item/201408/13/20140813122725_8h8Yu.jpeg",
            "http://img.woyaogexing.com/touxiang/nv/20140212/9ac2117139f1ecd8%21200x200.jpg",
            "http://p1.qqyou.com/touxiang/uploadpic/2013-3/12/2013031212295986807.jpg"};

    public static List<User> USERS = new ArrayList<User>();
    public static List<PhotoInfo> PHOTOS = new ArrayList<PhotoInfo>();

    private static int circleId = 0;

    private static int favortId = 0;

    private static int commentId = 0;

    public static final User curUser =  new User("0", "自己", HEADIMG[0]);

    static {
        User user1 = new User("1", "张三", HEADIMG[1]);
        User user2 = new User("2", "李四", HEADIMG[2]);
        User user3 = new User("3", "隔壁老王", HEADIMG[3]);
        User user4 = new User("4", "赵六", HEADIMG[4]);
        User user5 = new User("5", "田七", HEADIMG[5]);
        User user6 = new User("6", "Naoki", HEADIMG[6]);
        User user7 = new User("7", "这个名字是不是很长，哈哈！因为我是用来测试换行的", HEADIMG[7]);

        USERS.add(curUser);
        USERS.add(user1);
        USERS.add(user2);
        USERS.add(user3);
        USERS.add(user4);
        USERS.add(user5);
        USERS.add(user6);
        USERS.add(user7);


        PhotoInfo p1 = new PhotoInfo();
        p1.url = "http://f.hiphotos.baidu.com/image/pic/item/faf2b2119313b07e97f760d908d7912396dd8c9c.jpg";
        p1.w = 640;
        p1.h = 792;

        PhotoInfo p2 = new PhotoInfo();
        p2.url = "http://g.hiphotos.baidu.com/image/pic/item/4b90f603738da977c76ab6fab451f8198718e39e.jpg";
        p2.w = 640;
        p2.h = 792;

        PhotoInfo p3 = new PhotoInfo();
        p3.url = "http://e.hiphotos.baidu.com/image/pic/item/902397dda144ad343de8b756d4a20cf430ad858f.jpg";
        p3.w = 950;
        p3.h = 597;

        PhotoInfo p4 = new PhotoInfo();
        p4.url = "http://a.hiphotos.baidu.com/image/pic/item/a6efce1b9d16fdfa0fbc1ebfb68f8c5495ee7b8b.jpg";
        p4.w = 533;
        p4.h = 800;

        PhotoInfo p5 = new PhotoInfo();
        p5.url = "http://b.hiphotos.baidu.com/image/pic/item/a71ea8d3fd1f4134e61e0f90211f95cad1c85e36.jpg";
        p5.w = 700;
        p5.h = 467;

        PhotoInfo p6 = new PhotoInfo();
        p6.url = "http://c.hiphotos.baidu.com/image/pic/item/7dd98d1001e939011b9c86d07fec54e737d19645.jpg";
        p6.w = 700;
        p6.h = 467;

        PhotoInfo p7 = new PhotoInfo();
        p7.url = "http://pica.nipic.com/2007-10-17/20071017111345564_2.jpg";
        p7.w = 1024;
        p7.h = 640;

        PhotoInfo p8 = new PhotoInfo();
        p8.url = "http://pic4.nipic.com/20091101/3672704_160309066949_2.jpg";
        p8.w = 1024;
        p8.h = 768;

        PhotoInfo p9 = new PhotoInfo();
        p9.url = "http://pic4.nipic.com/20091203/1295091_123813163959_2.jpg";
        p9.w = 1024;
        p9.h = 640;

        PhotoInfo p10 = new PhotoInfo();
        p10.url = "http://pic31.nipic.com/20130624/8821914_104949466000_2.jpg";
        p10.w = 1024;
        p10.h = 768;


        PHOTOS.add(p1);
        PHOTOS.add(p2);
        PHOTOS.add(p3);
        PHOTOS.add(p4);
        PHOTOS.add(p5);
        PHOTOS.add(p6);
        PHOTOS.add(p7);
        PHOTOS.add(p8);
        PHOTOS.add(p9);
        PHOTOS.add(p10);
    }

    public static List<DynamicEntity> createDynamicDatas(){
        List<DynamicEntity> datas = new ArrayList<DynamicEntity>();
        for (int i = 0; i < 15; i++){
            DynamicEntity entity = new DynamicEntity();
            User user = getUser();
            entity.setUser(user);
            entity.setContent(getContent());
            entity.setCreateTime("12月24日");

            entity.setFavorites(createFavoriteList());
            entity.setComments(createCommentList());

            int type = getRandomNum(10) % 2;
            if (type == 0){
                entity.setType("1");// 链接
                entity.setLinkImg("http://pics.sc.chinaz.com/Files/pic/icons128/2264/%E8%85%BE%E8%AE%AFQQ%E5%9B%BE%E6%A0%87%E4%B8%8B%E8%BD%BD1.png");
                entity.setLinkTitle("百度一下，你就知道");
            } else if (type == 1){
                entity.setType("2");// 图片
                entity.setPhotos(createPhotos());
            } else {
//                entity.setType("3");// 视频
//                String videoUrl = "http://yiwcicledemo.s.qupai.me/v/80c81c19-7c02-4dee-baca-c97d9bbd6607.mp4";
//                String videoImgUrl = "http://yiwcicledemo.s.qupai.me/v/80c81c19-7c02-4dee-baca-c97d9bbd6607.jpg";
//                entity.setVideoUrl(videoUrl);
//                entity.setVideoImgUrl(videoImgUrl);
                entity.setType("2");// 图片
                entity.setPhotos(createPhotos());
            }
            datas.add(entity);
        }
        return datas;
    }

    public static User getUser(){
        return USERS.get(getRandomNum(USERS.size()));
    }

    public static String getContent(){
        return CONTENTS[getRandomNum(CONTENTS.length)];
    }

    public static int getRandomNum(int max){
        Random random = new Random();
        int result = random.nextInt(max);
        return result;
    }

    public static List<FavoriteItem> createFavoriteList(){
        int size = getRandomNum(USERS.size());
        List<FavoriteItem> items = new ArrayList<FavoriteItem>();
        List<String> history =  new ArrayList<String>();
        if (size > 0){
            for (int i = 0; i < size; i++){
                FavoriteItem item = createFavorite();
                String id = item.getUser().getId();
                if (!history.contains(id)){
                    items.add(item);
                    history.add(id);
                } else {
                    i--;
                }
            }
        }
        return items;
    }

    public static FavoriteItem createFavorite(){
        FavoriteItem item = new FavoriteItem();
        item.setId(String.valueOf(favortId++));
        item.setUser(getUser());
        return item;
    }

    public static List<CommentEntity> createCommentList(){
        List<CommentEntity> items = new ArrayList<CommentEntity>();
        int size = getRandomNum(10);
        if (size > 0){
            for (int i = 0; i < size; i++){
                CommentEntity item = createComment();
                items.add(item);
            }
        }
        return items;
    }

    public static CommentEntity createComment(){
        CommentEntity item = new CommentEntity();
        item.setId(String.valueOf(commentId++));
        item.setContent("哈哈");
        User user = getUser();
        item.setUser(user);
        if (getRandomNum(10) % 2 ==0){
            while (true) {
                User reply = getUser();
                if (!reply.getId().equals(user.getId())){
                    item.setToReplyUser(reply);
                    break;
                }
            }
        }
        return item;
    }

    public static List<PhotoInfo> createPhotos() {
        List<PhotoInfo> photos = new ArrayList<PhotoInfo>();
        int size = getRandomNum(PHOTOS.size());
        if (size > 0) {
            if (size > 9) {
                size = 9;
            }
            for (int i = 0; i < size; i++) {
                PhotoInfo photo = PHOTOS.get(getRandomNum(PHOTOS.size()));
                if (!photos.contains(photo)) {
                    photos.add(photo);
                } else {
                    i--;
                }
            }
        }
        return photos;
    }

}
