package app.yellow.rx_mvp_sample.api;

import java.util.List;

import app.yellow.rx_mvp_sample.bean.UserBean;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

public interface UserService {

    @GET("")
    Observable<List<UserBean>> getUsersByUrl(@Url String url, @Query("page") int pageIndex,
                                             @Query("per_page") int pageSize);




}