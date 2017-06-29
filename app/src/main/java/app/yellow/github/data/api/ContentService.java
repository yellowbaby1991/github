package app.yellow.github.data.api;

import java.util.List;

import app.yellow.github.bean.repositorydetail.ContentBean;
import app.yellow.github.config.GithubConfig;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Url;
import rx.Observable;

public interface ContentService {
    
    @Headers(GithubConfig.CACHE_HEADER)
    @GET("")
    Observable<ContentBean> getContentByUrl(@Url String url);

    @Headers(GithubConfig.CACHE_HEADER)
    @GET("")
    Observable<List<ContentBean>> getContentListByUrl(@Url String url);

}