#FDroidMvp

## 概述

`FDroidMvp`主要会有这些特性：

**无需写`Contract`！ 无需写`Present`接口！  无需写`View`接口!**

新增：

* Mvp实现
* `RxJava` & `RxAndroid`
* 权限适配 `RxPermission`
* 事件订阅默认采用 `RxBus`
* 网络交互：
	* `Retrofit` + `rx`
	* **统一异常处理**
	* **支持多个baseUrl**
	* 。。。。
* 无需担心rx内存泄漏

保留：

* 提供`XActivity`、`XFragment`等基类，可快速进行开发
* 内置了`RxBus`，可自由切换到其他事件订阅库



**先睹为快**

你可以这么使用:

HomeFragment

```java
public class HomeFragment extends XBaseLazyFragment<HomePresent> {

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        getP().LoadData(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public HomePresent newP() {
        return new HomePresent();
    }
}

```
也可以这么用

SecondFragment

```java
public class SecondFragment extends XBaseLazyFragment {
    public static Fragment newInstance() {
        return new SecondFragment();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        getHomeModel();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_second;
    }


    /**
     * 请求数据
     */
    public void getHomeModel() {

        HttpClient.getInstance().getObservable(Api.getApiService().totalEmployInfo())
                .compose(this.<Response<List<Entity>>>bindToLifecycle())
                .subscribe(new RxBaseCallBack<List<Entity>>(context) {
                    @Override
                    public void onSuc(Response<List<Entity>> response) {
                        Log.i("fanlcly", "onSuc: 成功了");
                    }

                    @Override
                    public void onFail(String message, int failCode) {
                        Log.i("fanlcly", "onFail: 失败了");
                    }

                });

    }


    @Override
    public Object newP() {
        return null;
    }
}

```

## Get Started

使用，仅需三步：

### step1  

clone 'FDroid'库到本地:
```groovy
git clone https://github.com/fanlcly/FDroid_mvp.git
```

### step2

将`mvp`作为依赖库，在您的app module 中 添加如下依赖:
```groovy
compile project(':rxmvp')
```

### step3

拷贝`config.gradle`到您的项目根目录，并修改项目gradle文件下引入：
```groovy
apply from: "config.gradle"
```

并添加:

```groovy
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}
```


## 第二种方式，通过JitPack引入

### step1 在根目录的gradle文件中配置:
```groovy
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}
```

### step2 添加依赖:
```groovy
dependencies {
	   implementation 'com.github.fanlcly:FDroid_mvp:0.0.1'
}
```



## 重要说明

* [ButterKnife](https://github.com/JakeWharton/butterknife)使用的是8.4.0版本，重点是 `@BindView`，可以去项目官网查看。
* [Rxlifecycle](https://github.com/trello/RxLifecycle)使用的是1.0版本，具体如何使用可以查看官网。
* [RxPermissions](https://github.com/tbruyelle/RxPermissions)使用的是0.9.1版本，具体如何使用可以查看官网。
* [retrofit](https://github.com/square/retrofit)，具体如何使用可以查看官网。


## 感谢声明
- 感谢所有优秀和MVP相关的开源项目

### End
