package com.custom.bdyx.model.http;


import com.custom.bdyx.constants.UrlConstants;
import com.custom.bdyx.utils.LogUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by levi on 2016/2/25.
 */
public class RetrofitUtil {
    private static APIService service;
    private static Retrofit retrofit;

    private static Retrofit getRetrofit(String base_url) {
        if (retrofit == null) {
            synchronized (RetrofitUtil.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(base_url)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(okHttpClient())
                            .build();
                }
            }
        }
        return retrofit;
    }

    public static APIService getService() {
        if (service == null) {
            synchronized (RetrofitUtil.class){
                if (service == null) {
                    service = getRetrofit(UrlConstants.DOMAIN).create(APIService.class);
                }
            }
        }
        return service;
    }


    /**
     * http拦截器 负责打印返回的信息 便于调试
     *
     * @return
     */
    private static OkHttpClient okHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                try {
                    Response response = chain.proceed(chain.request());
                    ResponseBody responseBody = response.body();
                    String responseBodyString = response.body().string();
                    LogUtil.i("url==" + chain.request().url() + ";  json==" + responseBodyString);
                    // now we have extracted the response body but in the process
                    // we have consumed the original reponse and can't read it again
                    // so we need to build a new one to return from this method
                    Response newResponse = response.newBuilder().body(ResponseBody.create(responseBody.contentType(), responseBodyString.getBytes())).build();
                    return newResponse;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return chain.proceed(chain.request());
            }
        }).build();
        return okHttpClient;

    }

//    /**
//     * 对网络接口返回的ResultVo进行分割操作
//     *
//     * @param response
//     * @param <T>
//     * @return
//     */
//    public <T> Observable<T> flatResponse(final ResultVo<T> response) {
//        return Observable.create(new Observable.OnSubscribe<T>() {
//
//            @Override
//            public void call(Subscriber<? super T> subscriber) {
////                LogUtil.i("code=="+response.getError_code());
//                if (response.isSucceed()) {
//                    if (!subscriber.isUnsubscribed()) {
//                        subscriber.onNext(response.getData());
//                    }
//                } else {
//                    if (!subscriber.isUnsubscribed()) {
//                        subscriber.onError(new APIException(response.getError_code() + "", response.getMessage()));
//                    }
//                    return;
//                }
//
//                if (!subscriber.isUnsubscribed()) {
//                    subscriber.onCompleted();
//                }
//
//            }
//        });
//    }
//
//    /**
//     * 自定义异常，当接口返回的RespVo的返回码不为成功码时，抛出此异常
//     */
//    public static class APIException extends Exception {
//        public String code;
//        public String message;
//
//        public APIException(String code, String message) {
//            this.code = code;
//            this.message = message;
//        }
//
//        @Override
//        public String getMessage() {
//            return message;
//        }
//    }
//
//    /**
//     * http://www.jianshu.com/p/e9e03194199e
//     * <p/>
//     * Transformer实际上就是一个Func1<Observable<T>, Observable<R>>，
//     * 换言之就是：可以通过它将一种类型的Observable转换成另一种类型的Observable，
//     * 和调用一系列的内联操作符是一模一样的。
//     *
//     * @param <T>
//     * @return
//     */
//    protected <T> Observable.Transformer<ResultVo<T>, T> applySchedulers() {
//        return (Observable.Transformer<ResultVo<T>, T>) transformer;
//    }
//
//    final Observable.Transformer transformer = new Observable.Transformer() {
//        @Override
//        public Object call(Object observable) {
//            return ((Observable) observable).subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .flatMap(new Func1() {
//                        @Override
//                        public Object call(Object response) {
//                            return flatResponse((ResultVo<Object>) response);
//                        }
//                    })
//                    ;
//        }
//    };

    /**
     * 当{@link APIService}中接口的注解为{@link retrofit2.http.Multipart}时，参数为{@link RequestBody}
     * 生成对应的RequestBody
     *
     * @param param
     * @return
     */
    public static RequestBody createRequestBody(int param) {
        return RequestBody.create(MediaType.parse("text/plain"), String.valueOf(param));
    }

    public static RequestBody createRequestBody(long param) {
        return RequestBody.create(MediaType.parse("text/plain"), String.valueOf(param));
    }

    public static RequestBody createRequestBody(String param) {
        return RequestBody.create(MediaType.parse("text/plain"), param);
    }

    public static RequestBody createRequestBody(byte[] param) {
        return RequestBody.create(MediaType.parse("image/*"), param);
    }

    public static RequestBody createRequestBody(File param) {
        return RequestBody.create(MediaType.parse("image/*"), param);
    }

    public static class PostParams {
        Map<String, RequestBody> map;
        Map<String, Object> logMap;

        public PostParams() {
            map = new HashMap<>();
            logMap = new HashMap<>();
        }

        public void put(String key, String value) {
            map.put(key, createRequestBody(value));
            logMap.put(key, value);
        }

        public void put(String key, byte[] value) {
            map.put(key, createRequestBody(value));
            logMap.put(key, value);
        }

        public void put(String key, File value) {
            map.put(key, createRequestBody(value));
            logMap.put(key, value);
        }

        public void putPicFile(String key, File value) {
            map.put(key + "\"; filename=\"" + value.getName() + "", createRequestBody(value));
        }

        public void putPicByte(String key, byte[] value) {
            map.put(key + "\"; filename=\"" + key + ".jpg" + "", createRequestBody(value));
        }

        public Map<String, RequestBody> getMap() {
            return map;
        }

        public void setMap(Map<String, RequestBody> map) {
            this.map = map;
        }

        public Map<String, Object> getLogMap() {
            return logMap;
        }

        public void setLogMap(Map<String, Object> logMap) {
            this.logMap = logMap;
        }
    }
}
