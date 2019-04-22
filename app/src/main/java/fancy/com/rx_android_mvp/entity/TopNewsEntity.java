package fancy.com.rx_android_mvp.entity;

import java.util.List;

import fancy.com.rxmvp.net.IModel;

/**
 * 头条新闻实体
 *
 * @author fanlei
 * @version 1.0 2019/4/19 0019
 * @since JDK 1.8
 */
public class TopNewsEntity implements IModel {
    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean isAuthError() {
        return false;
    }

    @Override
    public boolean isBizError() {
        return false;
    }

    @Override
    public String getErrorMsg() {
        return null;
    }


    private List<Data1> auto;





    static class Data1 {

        /**
         * category : 汽车
         * channel : auto
         * digest : 仅就外观来说，荣放已经与诸如奇骏、C-RV这样的对手有了明显
         * docid : EDB09NJ00008856R
         * imgsrc3gtype : 1
         * link : https://3g.163.com/all/article/EDB09NJ00008856R.html
         * picInfo : [{"url":"http://cms-bucket.ws.126.net/2019/04/22/0971265ab5c643ef98a63b0b3638cf85.jpeg"}]
         * ptime : 2019-04-22 00:33:18
         * source : 网易汽车
         * tag :
         * tcount : 687
         * title : 先用外观解解馋 丰田全新荣放变身小硬汉
         * type : doc
         * typeid :
         * unlikeReason : 重复、旧闻/6,内容质量差/6
         */

        private String category;
        private String channel;
        private String digest;
        private String docid;
        private int imgsrc3gtype;
        private String link;
        private String ptime;
        private String source;
        private String tag;
        private int tcount;
        private String title;
        private String type;
        private String typeid;
        private String unlikeReason;
        private List<PicInfoBean> picInfo;

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public String getDocid() {
            return docid;
        }

        public void setDocid(String docid) {
            this.docid = docid;
        }

        public int getImgsrc3gtype() {
            return imgsrc3gtype;
        }

        public void setImgsrc3gtype(int imgsrc3gtype) {
            this.imgsrc3gtype = imgsrc3gtype;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public int getTcount() {
            return tcount;
        }

        public void setTcount(int tcount) {
            this.tcount = tcount;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTypeid() {
            return typeid;
        }

        public void setTypeid(String typeid) {
            this.typeid = typeid;
        }

        public String getUnlikeReason() {
            return unlikeReason;
        }

        public void setUnlikeReason(String unlikeReason) {
            this.unlikeReason = unlikeReason;
        }

        public List<PicInfoBean> getPicInfo() {
            return picInfo;
        }

        public void setPicInfo(List<PicInfoBean> picInfo) {
            this.picInfo = picInfo;
        }

        public static class PicInfoBean {
            /**
             * url : http://cms-bucket.ws.126.net/2019/04/22/0971265ab5c643ef98a63b0b3638cf85.jpeg
             */

            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
