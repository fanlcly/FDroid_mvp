package com.fancy.rx_android_mvp.entity;

/**
 * file explain
 *
 * @author fanlei
 * @version 1.0 2019/5/9 0009
 * @since JDK 1.8
 */
public class NewsEntity {


    /**
     * author_name : 纵相新闻
     * category : 头条
     * date : 2019-05-08 17:03
     * thumbnail_pic_s : http://04imgmini.eastday.com/mobile/20190508/2019050817_6c688178a6cd4a87a4576c6ba9388096_5155_mwpm_03200403.jpg
     * thumbnail_pic_s02 : http://04imgmini.eastday.com/mobile/20190508/2019050817_85fb43c6657141259cc2beae73ed3475_0733_mwpm_03200403.jpg
     * thumbnail_pic_s03 : http://04imgmini.eastday.com/mobile/20190508/2019050817_2e93fd19b84047a2b9f3d2a041e64ced_2014_mwpm_03200403.jpg
     * title : 刺激战场停服，和平精英上线！分析人士：或给腾讯带来20亿利润
     * uniquekey : 145f05223ab5067b35b3f5b694813023
     * url : http://mini.eastday.com/mobile/190508170318120.html
     */

    private String author_name;
    private String category;
    private String date;
    private String thumbnail_pic_s;
    private String thumbnail_pic_s02;
    private String thumbnail_pic_s03;
    private String title;
    private String uniquekey;
    private String url;

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getThumbnail_pic_s() {
        return thumbnail_pic_s;
    }

    public void setThumbnail_pic_s(String thumbnail_pic_s) {
        this.thumbnail_pic_s = thumbnail_pic_s;
    }

    public String getThumbnail_pic_s02() {
        return thumbnail_pic_s02;
    }

    public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
        this.thumbnail_pic_s02 = thumbnail_pic_s02;
    }

    public String getThumbnail_pic_s03() {
        return thumbnail_pic_s03;
    }

    public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
        this.thumbnail_pic_s03 = thumbnail_pic_s03;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUniquekey() {
        return uniquekey;
    }

    public void setUniquekey(String uniquekey) {
        this.uniquekey = uniquekey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
