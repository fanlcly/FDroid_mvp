package com.fancy.rx_android_mvp.net;

import java.util.List;

import com.fancy.rx_android_mvp.entity.GirlEntity;
import com.fancy.rx_android_mvp.entity.ListEntity;
import com.fancy.rx_android_mvp.entity.NewsModel;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * ApiService
 *
 * @author fanlei
 * @version 1.0 2019/4/19 0019
 * @since JDK 1.8
 */
public interface ApiService {

    /**
     * 统计表数据
     */
    @GET("open/xiaohua.json")
    Observable<Response<List<ListEntity>>> totalEmployInfo();

    //    @GET("http://image.baidu.com/channel/listjson?pn=0&rn=30&tag1=明星&tag2=全部&ie=utf8")
    @GET("http://image.baidu.com/channel/listjson")
    Observable<Response<BaseModle<List<GirlEntity>>>> getGirlImage(@Query("pn") int pn
            , @Query("rn") int rn
            , @Query("tag1") String tag1
            , @Query("tag2") String tag2
            , @Query("ftags") String ftags);


    //    @GET("http://image.baidu.com/channel/listjson?pn=0&rn=30&tag1=明星&tag2=全部&ie=utf8")
//    @GET("http://image.baidu.com/search/index?tn=baiduimage&word=%E6%80%A7%E6%84%9F")
    @GET("http://image.baidu.com/search/index")
    Observable<Response<BaseModle<List<GirlEntity>>>> getGirlImage(@Query("tn") String tn
            , @Query("word") String word);


    //    http://v.juhe.cn/toutiao/index?type=top&key=3dc86b09a2ee2477a5baa80ee70fcdf5
    @GET("http://v.juhe.cn/toutiao/index")
    Observable<Response<NewsModel>> getNews(@Query("type") String type
            , @Query("key") String key);

}
