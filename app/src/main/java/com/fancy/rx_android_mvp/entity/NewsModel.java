package com.fancy.rx_android_mvp.entity;

import java.util.List;

/**
 * file explain
 *
 * @author fanlei
 * @version 1.0 2019/5/9 0009
 * @since JDK 1.8
 */
public class NewsModel {
    private String error_code;
    private String reason;
    private ResultBean result;

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private List<NewsEntity> data;


        public List<NewsEntity> getData() {
            return data;
        }

        public void setData(List<NewsEntity> data) {
            this.data = data;
        }


    }
}
