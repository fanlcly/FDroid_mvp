package com.fancy.rx_android_mvp.net;

import java.util.List;

import com.fancy.rx_android_mvp.entity.ListEntity;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;

/**
 * ApiService
 *
 * @author fanlei
 * @version 1.0 2019/4/19 0019
 * @since JDK 1.8
 */
public interface ApiService {

    @GET("https://www.apiopen.top/journalismApi/")
    Observable<Response<String>> getTopNews();

    /**
     * 统计表数据
     */
    @GET("open/xiaohua.json")
    Observable<Response<List<ListEntity>>> totalEmployInfo();

}
