package com.custom.bdyx.model.http;


import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;

import com.huaxi100.hxcommonlib.utils.Utils;

import java.util.Map;

import okhttp3.RequestBody;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * API包装类
 */
public class ApiWrapper extends RetrofitUtil {
    public static String DEVICE_ID = "";
    public static final String KEY = "07A4A8DAC4D7C27AFF893F2208B0D60B";
    private static ApiWrapper apiWrapper;

    public static ApiWrapper getApiWrapper() {
        if (apiWrapper == null) {
            synchronized (ApiWrapper.class) {
                if (apiWrapper == null) {
                    apiWrapper = new ApiWrapper();
                }
            }
        }
        return apiWrapper;
    }


    public static Map<String, RequestBody> bindMultipartParams(Activity activity, RetrofitUtil.PostParams querymap, int isObj) {
//        SpUtil sp = new SpUtil(activity, UrlConstants.SP_NAME);
//        querymap.put("sid", sp.getStringValue(UrlConstants.SID));
//        querymap.put("token", sp.getStringValue(UrlConstants.TOKEN));
//        querymap.put("client", UrlConstants.CLIENT);
//        querymap.put("w", "" + AppUtils.getWidth(activity));
//        querymap.put("version", AppUtils.getPackageInfo(activity).versionName);
//        querymap.put("device_id", getDeviceId(activity));
//        querymap.put("server_version", UrlConstants.SERVER_VERSION);
        querymap.put("objorarr", isObj == 1 ? "obj" : "arr");
//        //城市ID默认为1
//        int city_id=sp.getIntegerValue(UrlConstants.CITY_ID);
//        city_id=city_id==0?1:city_id;
//        querymap.put("city_id", city_id + "");
//        try {
//            String tempTime = System.currentTimeMillis() / 1000 + "";
//            querymap.put("timestamp", tempTime);
//            querymap.put("sign", MD5.SHA1(MD5.getMD5(KEY + tempTime)));
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
        return querymap.getMap();
    }

    public static String getDeviceId(Activity activity) {
        if (Utils.isEmpty(DEVICE_ID)) {
            TelephonyManager tm = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);
            DEVICE_ID = tm.getDeviceId();
        }
        return DEVICE_ID;
    }

//    public Observable<ResultVo<List<FoodTypeVo>>> getFoodType(final Activity activity, PostParams params) {
//        return getService().getFoodType(bindMultipartParams(activity, params, 0))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    public Observable<ResultVo<List<CityVo>>> getCity(final Activity activity, PostParams params) {
//        return getService().getCity(bindMultipartParams(activity, params, 0))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    public Observable<ResultVo<VersionInfo>> getVersion(final Activity activity, PostParams params) {
//        return getService().getVersion(bindMultipartParams(activity, params, 0))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    /**
//     * 获取首页数据
//     *
//     * @param activity
//     * @param params
//     * @return
//     */
//    public Observable<ResultVo<List<Article>>> getHomeIndex(Activity activity, PostParams params) {
//        return getService().getHomeIndex(bindMultipartParams(activity, params, 0)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
//    }
//
//    /**
//     * 获取card位跳转页数据
//     *
//     * @param activity
//     * @param url
//     * @param params
//     * @return
//     */
//    public Observable<ResultVo<TagSubjectVo>> getTagSubject(Activity activity, String url, PostParams params) {
//        return getService().getTagSubject(url, bindMultipartParams(activity, params, 1)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
//    }
//
//    /**
//     * 我的收录
//     *
//     * @param activity
//     * @param params
//     * @return
//     */
//    public Observable<ResultVo<List<FavVo>>> getMyFav(Activity activity, PostParams params) {
//        return getService().getMyFav(bindMultipartParams(activity, params, 0)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
//    }
//
//    /**
//     * 查询搜索结果
//     *
//     * @param activity
//     * @param params
//     * @return
//     */
//    public Observable<ResultVo<SearchResultVo>> getSearchResult(Activity activity, PostParams params) {
//        return getService().getSearchResult(bindMultipartParams(activity, params, 1)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
//    }
//
//    /**
//     * 点击标签列表
//     *
//     * @param activity
//     * @param params
//     * @return
//     */
//    public Observable<ResultVo<List<Article>>> getTagList(Activity activity, String url, PostParams params) {
//        return getService().getTagList(url, bindMultipartParams(activity, params, 0)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
//    }
//
//    /**
//     * 查询是否需要动态加载补丁
//     *
//     * @param activity
//     * @param params
//     * @return
//     */
//    public Observable<ResultVo<PatchVo>> getPatchVo(Activity activity, PostParams params) {
//        return getService().getPatchVo(bindMultipartParams(activity, params, 1));
//
//    }
//
//    /**
//     * 评论上传图片
//     *
//     * @param activity
//     * @param params
//     * @return
//     */
//    public Observable<ResultVo<CommentImageVo>> uploadCommentImage(Activity activity, PostParams params) {
//        return getService().uploadCommentImage(bindMultipartParams(activity, params, 1)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
//    }
//
//    /**
//     * 查询达人信息
//     *
//     * @param activity
//     * @param params
//     * @return
//     */
//    public Observable<ResultVo<AuthorInfoVo>> getAuthorInfo(Activity activity, PostParams params) {
//        return getService().getAuthorInfo(bindMultipartParams(activity, params, 1))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    /**
//     * 获取文章详情
//     *
//     * @param activity
//     * @param url
//     * @param params
//     * @return
//     */
//    public Observable<ResultVo<ArticleDetail>> queryDetail(Activity activity, String url, PostParams params) {
//        return getService().getDetail(url, bindMultipartParams(activity, params, 1)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
//    }
//
//    /**
//     * 文章详情,对文章进行收录
//     *
//     * @param activity
//     * @param
//     * @param params
//     * @return
//     */
//    public Observable<ResultVo<DoLikeResp>> doFavArticle(Activity activity, PostParams params) {
//        return getService().doFavArticle(bindMultipartParams(activity, params, 1)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
//    }
//
//    /**
//     * 点赞
//     *
//     * @param activity
//     * @param params
//     * @return
//     */
//    public Observable<ResultVo> doLike(Activity activity, PostParams params) {
//        return getService().dolike(bindMultipartParams(activity, params, 1)).subscribeOn(Schedulers.io()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
//    }
//
//    /**
//     * 发现页数据
//     *
//     * @param activity
//     * @param params
//     * @return
//     */
//    public Observable<ResultVo<List<Article>>> getFindIndex(Activity activity, PostParams params) {
//        return getService().getFindIndex(bindMultipartParams(activity, params, 0)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
//    }
//
//    /**
//     * 查询话题详情
//     *
//     * @param activity
//     * @param
//     * @return
//     */
//    public Observable<ResultVo<TopicVo>> queryTopic(Activity activity, String url) {
//        return getService().queryTopic(url, bindMultipartParams(activity, new PostParams(), 1)).subscribeOn(Schedulers.io()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
//    }
//    /**
//     * 话题赞
//     *
//     * @param activity
//     * @param
//     * @return
//     */
//    public Observable<ResultVo> zanTopic(Activity activity, PostParams params) {
//        return getService().zanTopic(bindMultipartParams(activity, params, 1)).subscribeOn(Schedulers.io()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
//    }
//    /**
//     * 话题踩
//     *
//     * @param activity
//     * @param
//     * @return
//     */
//    public Observable<ResultVo> caiTopic(Activity activity, PostParams params) {
//        return getService().caiTopic(bindMultipartParams(activity, params, 1)).subscribeOn(Schedulers.io()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
//    }
//    /**
//     * 话题评论详情
//     *
//     * @param activity
//     * @param
//     * @return
//     */
//    public Observable<ResultVo<TopicCommentVo>> topicCommentDetail(Activity activity, PostParams params) {
//        return getService().topicCommentDetail(bindMultipartParams(activity, params, 1)).subscribeOn(Schedulers.io()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
//    }
//
//    /**
//     * 我的页面中的广告轮播
//     *
//     * @param activity
//     * @param params
//     * @return
//     */
//    public Observable<ResultVo<List<ArticleDetail.AdInfo>>> getMineAds(Activity activity, PostParams params) {
//        return getService().getMineAds(bindMultipartParams(activity, params, 0))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    /**
//     * 上传头像
//     *
//     * @param activity
//     * @param params
//     * @return
//     */
//    public Observable<ResultVo<UpdataAvatarVo>> uploadAvatar(Activity activity, PostParams params) {
//        return getService().uploadAvatar(bindMultipartParams(activity, params, 1))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    /**
//     * 推荐达人列表页
//     *
//     * @param activity
//     * @param params
//     * @return
//     */
//    public Observable<ResultVo<List<AuthorInfoVo.Index>>> getRecommendDaren(Activity activity, PostParams params) {
//        return getService().getRecommendDaren(bindMultipartParams(activity, params, 0))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    /**
//     * 达人美食之旅列表页
//     *
//     * @param activity
//     * @param params
//     * @return
//     */
//    public Observable<ResultVo<List<ExperUpdateVo>>> getDarenFoodTripList(Activity activity, PostParams params) {
//        return getService().getDarenFoodTripList(bindMultipartParams(activity, params, 0))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    /**
//     * 数据埋点上传接口
//     *
//     * @param params
//     * @return
//     */
//    public Observable<ResultVo<DataMonitorResultVo>> updataDataMonitor(PostParams params) {
//        return getService().updataDataMonitor(params.getMap())
//                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.io());
//    }
//
//    /**
//     * 周四抢购
//     *
//     * @param params
//     * @return
//     */
//    public Observable<ResultVo<List<SaleVo>>> getOnSale(Activity activity,String url,PostParams params) {
//        return getService().getOnSale(url,bindMultipartParams(activity, params, 0))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
}
