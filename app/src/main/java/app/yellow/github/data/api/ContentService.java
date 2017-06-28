package app.yellow.github.data.api;

import app.yellow.github.bean.repositorydetail.ContentBean;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Url;
import rx.Observable;

public interface ContentService {
    @Headers("Cache-Control: public, max-age=180")
    @GET("")
    Observable<ContentBean> getContentByUrl(@Url String url);

}