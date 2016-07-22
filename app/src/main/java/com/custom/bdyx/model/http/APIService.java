package com.custom.bdyx.model.http;



import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Retrofit 接口定义
 * API接口
 */
public interface APIService {

//    //选择城市
//    @Multipart
//    @POST(UrlConstants.SELECT_CITY)
//    Observable<ResultVo<List<CityVo>>> getCity(@PartMap Map<String, RequestBody> options);
//
//    //获取最新版本
//    @Multipart
//    @POST(UrlConstants.NEW_VERSION)
//    Observable<ResultVo<VersionInfo>> getVersion(@PartMap Map<String, RequestBody> options);
//
//    //美食分类
//    @Multipart
//    @POST(UrlConstants.GET_FOOD_TYPE)
//    Observable<ResultVo<List<FoodTypeVo>>> getFoodType(@PartMap Map<String, RequestBody> options);
//
//    @Multipart
//    @POST(UrlConstants.INDEX)
//    Observable<ResultVo<List<Article>>> getHomeIndex(@PartMap Map<String, RequestBody> options);
//
//    @Multipart
//    @POST()
//    Observable<ResultVo<TagSubjectVo>> getTagSubject(@Url String url, @PartMap Map<String, RequestBody> options);
//
//    @Multipart
//    @POST(UrlConstants.MY_FAV_ARTCLE)
//    Observable<ResultVo<List<FavVo>>> getMyFav(@PartMap Map<String, RequestBody> options);
//
//    @Multipart
//    @POST(UrlConstants.QUERY)
//    Observable<ResultVo<SearchResultVo>> getSearchResult(@PartMap Map<String, RequestBody> options);
//
//    @Multipart
//    @POST()
//    Observable<ResultVo<List<Article>>> getTagList(@Url String url, @PartMap Map<String, RequestBody> options);
//
//    @Multipart
//    @POST(UrlConstants.NUWA_PATCH)
//    Observable<ResultVo<PatchVo>> getPatchVo(@PartMap Map<String, RequestBody> options);
//
//    @Multipart
//    @POST(UrlConstants.UPLOAD_PICTURE)
//    Observable<ResultVo<CommentImageVo>> uploadCommentImage(@PartMap Map<String, RequestBody> options);
//
//    @Multipart
//    @POST(UrlConstants.AUTHOR_INFO)
//    Observable<ResultVo<AuthorInfoVo>> getAuthorInfo(@PartMap Map<String, RequestBody> options);
//
//    @Multipart
//    @POST()
//    Observable<ResultVo<ArticleDetail>> getDetail(@Url String url, @PartMap Map<String, RequestBody> options);
//
//    @Multipart
//    @POST(UrlConstants.LIKE_ARTCLE)
//    Observable<ResultVo<DoLikeResp>> doFavArticle(@PartMap Map<String, RequestBody> options);
//
//    @Multipart
//    @POST(UrlConstants.LIKE_COMMENT)
//    Observable<ResultVo> dolike(@PartMap Map<String, RequestBody> options);
//
//    @Multipart
//    @POST( UrlConstants.MINE_ADS)
//    Observable<ResultVo<List<ArticleDetail.AdInfo>>> getMineAds(@PartMap Map<String, RequestBody> options);
//
//    @Multipart
//    @POST(UrlConstants.UPLOAD_AVATAR)
//    Observable<ResultVo<UpdataAvatarVo>> uploadAvatar(@PartMap Map<String, RequestBody> options);
//
//    @Multipart
//    @POST(UrlConstants.FIND)
//    Observable<ResultVo<List<Article>>> getFindIndex(@PartMap Map<String, RequestBody> options);
//
//    @Multipart
//    @POST()
//    Observable<ResultVo<TopicVo>> queryTopic(@Url String url, @PartMap Map<String, RequestBody> options);
//
//    @Multipart
//    @POST(UrlConstants.ZAN_TOPIC)
//    Observable<ResultVo> zanTopic(@PartMap Map<String, RequestBody> options);
//
//    @Multipart
//    @POST(UrlConstants.CAI_TOPIC)
//    Observable<ResultVo> caiTopic(@PartMap Map<String, RequestBody> options);
//
//    @Multipart
//    @POST(UrlConstants.TOPIC_COMMENT_DETAIl)
//    Observable<ResultVo<TopicCommentVo>> topicCommentDetail(@PartMap Map<String, RequestBody> options);
//
//    @Multipart
//    @POST(UrlConstants.RECOMMEND_DAREN)
//    Observable<ResultVo<List<AuthorInfoVo.Index>>> getRecommendDaren(@PartMap Map<String, RequestBody> options);
//
//    @Multipart
//    @POST(UrlConstants.DAREN_FOOD_TRIP)
//    Observable<ResultVo<List<ExperUpdateVo>>> getDarenFoodTripList(@PartMap Map<String, RequestBody> options);
//
//    @Multipart
//    @POST(DataMonitorConstants.UPDATA_DATA_MONITOR)
//    Observable<ResultVo<DataMonitorResultVo>> updataDataMonitor(@PartMap Map<String, RequestBody> options);
//
//    @Multipart
//    @POST()
//    Observable<ResultVo<List<SaleVo>>> getOnSale(@Url String url, @PartMap Map<String, RequestBody> options);
}
