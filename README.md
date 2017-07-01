* [Github](#github)
* [项目的起因](#项目的起因)
* [本文的目的](#本文的目的)
* [架构和三方](#架构和三方)
	* [Rx](#rx)
	* [快速开发工具](#快速开发工具)
	* [网络相关](#网络相关)
	* [图片加载](#图片加载)
	* [UI](#ui)
* [功能介绍](#功能介绍)
	* [Explore](#explore)
	* [RepositoryDetail](#repositorydetail)
	* [UserDetail](#userdetail)
	* [RepositoryList](#repositorylist)
	* [UserList](#userlist)
	* [Event](#event)
	* [Setting](#setting)
* [架构分析](#架构分析)
	* [MVP](#mvp)
	* [RxJava](#rxjava)

# Github
[Github地址][1]
# 项目的起因

Github上一个看起来很漂亮的Github客户端

成功引起了我的注意

但是，它不开源

作者在贴了一堆截图后留下了自己的商务合作邮箱

我不开心所以也想做一个

# 本文的目的

 1. 如果你是青铜玩家：希望本文提供的一些资料（如API，架构，三方，功能实现思路等）可以给你一些参考价值
 2. 如果你是王者玩家：欢迎指出问题和建议，尤其是优化和架构方面

# 架构和三方

基于 [google-android-architecture-mvp-rxjava][2] 的RxJava + Retrofit + Mvp架构，该架构会在后面详细讲解，三方库如下

## Rx

- [RxJava][3] 
- [RxAndroid][4]


## 快速开发工具

- [butterknife][5] 大名鼎鼎的黄油刀，让你不再findById
- [android_dbinspector][6] 不需要root就可以查看真机上数据库内容
- [fastjson][7] 最快的json解析工具，阿里巴巴出品

## 网络相关

 - [Retrofit][8] 新一代网络请求神器
 - [OkHttp logging interceptor][9] 请求日志拦截器

## 图片加载

- [Glide][10] 图片加载框架

## UI

- [MaterialSearchView][11] Material Design风格的搜索
- [CircleImageview][12] 圆形头像
- [BaseRecyclerViewAdapterHelper][13] 强大的RecyclerView万能适配器
- [FloatingActionButton][14] Material Design风格的浮动按钮
- [spots-dialog][15] 闪烁的loding进度条
- [materialish-progress][16] 旋转的菊花圈
- [MarkdownView][17] 将markdown格式的字符串显示成漂亮的html页面
- [WaitingDots][18] 闪烁的loding动画
- [CodeView][19] 将代码显示成漂亮的样式
- [material-dialogs][20] Material风格的Dialog

  
# 功能介绍
## Explore

 1. 浏览Repository和User，使用选项卡切换，并且将浏览过的数据缓存在本地（本应用所有数据都做了缓存，并且缓存时间可由用户定制）

<img src="https://raw.githubusercontent.com/yellowbaby1991/github/master/images/explore_4.png" width = "50%" /><img src="https://raw.githubusercontent.com/yellowbaby1991/github/master/images/explore_1.png" width = "50%" />


 2. 支持关键字搜索Repository和User，可以选择排序方式（Most star，Best match，Most fork，Rencent update等），可以按标签换语言分类（排序方式和标签可用用户定制）

<img src="https://raw.githubusercontent.com/yellowbaby1991/github/master/images/explore_2.png" width = "50%" /><img src="https://raw.githubusercontent.com/yellowbaby1991/github/master/images/explore_3.png" width = "50%" /> 

## RepositoryDetail

 1. Repository简要信息查看，并且可以进行star，unstar，fork操作

<img src="https://raw.githubusercontent.com/yellowbaby1991/github/master/images/repositorydetail_1.png" width = "50%" /><img src="https://raw.githubusercontent.com/yellowbaby1991/github/master/images/repositorydetail_2.png" width = "50%" /> 

 2. 异步加载ReadMe，按markdown格式显示ReadMe
 
 <img src="https://raw.githubusercontent.com/yellowbaby1991/github/master/images/repositorydetail_3.png" width = "50%" />
 
 3. 显示code树，查看代码内容，因为缓存的原因，点击加载过的节点可以秒加载
 
 <img src="https://raw.githubusercontent.com/yellowbaby1991/github/master/images/repositorydetail_4.png" width = "50%" /><img src="https://raw.githubusercontent.com/yellowbaby1991/github/master/images/repositorydetail_5.png" width = "50%" />
 
## UserDetail

 1. User简要信息查看，并且可以进行follow，unfollow操作

<img src="https://raw.githubusercontent.com/yellowbaby1991/github/master/images/userdetail_1.png" width = "50%" /><img src="https://raw.githubusercontent.com/yellowbaby1991/github/master/images/userdetail_2.png" width = "50%" />

## RepositoryList

 1. 查询自己的Repository
 
<img src="https://raw.githubusercontent.com/yellowbaby1991/github/master/images/repository_1.png" width = "50%" /><img src="https://raw.githubusercontent.com/yellowbaby1991/github/master/images/repository_2.png" width = "50%" />
 
 2. 查询自己Star过的Repository

<img src="https://raw.githubusercontent.com/yellowbaby1991/github/master/images/repository_3.png" width = "50%" /><img src="https://raw.githubusercontent.com/yellowbaby1991/github/master/images/repository_4.png" width = "50%" />

 3. 查询RepositoryDetail被fork过的Repository

<img src="https://raw.githubusercontent.com/yellowbaby1991/github/master/images/repository_5.png" width = "50%" /><img src="https://raw.githubusercontent.com/yellowbaby1991/github/master/images/repository_6.png" width = "50%" />

 4. 查询UserDetail被拥有的Repository

<img src="https://raw.githubusercontent.com/yellowbaby1991/github/master/images/repository_7.png" width = "50%" /><img src="https://raw.githubusercontent.com/yellowbaby1991/github/master/images/repository_8.png" width = "50%" />

## UserList

 1. 查询自己，以及其他User的following和follower

 <img src="https://raw.githubusercontent.com/yellowbaby1991/github/master/images/user_1.png" width = "50%" /><img src="https://raw.githubusercontent.com/yellowbaby1991/github/master/images/user_2.png" width = "50%" />
 
 2. 查询RepositoryDetail的Contributors和Stargazers
 
 <img src="https://raw.githubusercontent.com/yellowbaby1991/github/master/images/user_3.png" width = "50%" /><img src="https://raw.githubusercontent.com/yellowbaby1991/github/master/images/user_4.png" width = "50%" />


## Event

 1. 可以查询，自己，User，Repository的Event

<img src="https://raw.githubusercontent.com/yellowbaby1991/github/master/images/event_1.png" width = "50%" /><img src="https://raw.githubusercontent.com/yellowbaby1991/github/master/images/event_2.png" width = "50%" />

## Setting

 1. 设置Explore页面首先查询的语言，右下方应该有几个语言选项，默认的排序方式

<img src="https://raw.githubusercontent.com/yellowbaby1991/github/master/images/setting_1.png" width = "50%" /><img src="https://raw.githubusercontent.com/yellowbaby1991/github/master/images/setting_2.png" width = "50%" />


# 架构分析
## MVP
谷歌去年在github上发布一整套的它推荐的Android架构Demo，[todo-mvp-rxjava][21] 是之中用来示范rxjava的sample

关于它的这套架构，我画了一个栩栩如生的草图，嗯，栩栩如生

<img src="https://raw.githubusercontent.com/yellowbaby1991/github/master/images/rxjava_mvp.png"  width = "100%"/>

是不是已经被我的美术功底震惊的说不出话来，就冲这图你不给Star一个？

<img src="https://raw.githubusercontent.com/yellowbaby1991/github/master/images/huaji.jpg" width = "20%" />

只看图可能容易蒙蔽，用代码来解释一下

先看接口类

``` java
public interface Contract {

    interface View {
        
        void showLoading();

        void hideLoading();

        void showError();

        void showEmpty();

        void showList(List list);

        void setPresenter(Contract.Presenter presenter);
    }

    interface Presenter {

        void loadList();

    }
}
```

然后是Presenter的实现类，持有了view的对象和repository的对象，分别用来加载数据和展现数据，这里偷懒了没有切换线程，后面用RxJava完成

``` java
public class PrensenterImpl implements Contract.Presenter {

    private Contract.View mView;
    private Repository mRepository;

    public PrensenterImpl(Contract.View view, Repository repository) {
        mView = view;
        mRepository = repository;
        mView.setPresenter(this);
    }

    @Override
    public void loadList() {

        //UI 线程
        mView.showLoading();

        try{
            //IO 线程
            List list = mRepository.loadList();

            //切回UI 线程
            if (list.isEmpty()){
                mView.showEmpty();
            }else {
                mView.showList(list);
            }

        }catch (Exception e){
            mView.showError();
        }finally {
            mView.hideLoading();
        }

    }

```

再看View的实现类，也就是Activity，持有一个Presenter的对象，并且在创建该对象的时候将自身和数据仓库类传了过去

``` java
public class MainActivity extends AppCompatActivity implements Contract.View {

    private Contract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Repository repository = new Repository(new RemoteDataSource(), new LocalDataSource());//得到数据仓库
        mPresenter = new PrensenterImpl(this, repository); //将自身和数据仓库类传了过去

        mPresenter.loadList();

    }

    public void setPresenter(Contract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoading() {
        // 展示进度条
    }

    @Override
    public void hideLoading() {
        // 加载成功隐藏进度条
    }

    @Override
    public void showError() {
        // 加载失败
    }

    @Override
    public void showEmpty() {
        // 数据是空的
    }

    @Override
    public void showList(List list) {
        // 加载成功并且有数据耶，展示起来
    }

}
```

最后数据仓库先这么简单的写，后面再补充

``` java
public interface DataSource {
    List loadList();
}

public class LocalDataSource implements DataSource{
    @Override
    public List loadList() {
        return new ArrayList();//从本地读取缓存
    }
}

public class RemoteDataSource implements DataSource{
    @Override
    public List loadList() {
        return new ArrayList();//从网络加载数据
    }
}

public class Repository implements DataSource{

    private DataSource mRemoteDataSource;
    private DataSource mLocalDataSource;

    public Repository(DataSource remoteDataSource,DataSource lemoteDataSource){
        mRemoteDataSource = remoteDataSource;
        mLocalDataSource = lemoteDataSource;
    }


    @Override
    public List loadList() {
        List remote = mRemoteDataSource.loadList();
        List local = mLocalDataSource.loadList();
        // ....让两个数据源同时去加载数据，谁先加载完成就返回谁的
        return null;
    }
}
```

所以整个MVP的请求逻辑如下

 1. 用户到达View后开始请求数据
 2. MainActivity将请求委托给Presenter去处理
 3. Presenter通过Repository去请求数据，根据结果的不同分发回View

从来实现了数据的展示，请求，分发三层分离，时序图如下图：

<img src="https://raw.githubusercontent.com/yellowbaby1991/github/master/images/mvp_seq.png"  width = "100%"/>

## RxJava
上一节中有两个地方是十足的伪代码，mPresenter.loadList和Repository的具体实现，使用RxJava可以很容易的完成这两个部分的实现

对RxJava还没有概念的请看 [给Android 开发者的 RxJava 详解][22]

这里直接展示用法

添加依赖

``` java
compile 'io.reactivex:rxjava:1.0.8'
compile 'io.reactivex:rxandroid:1.2.1'
```

 1. 使用RxJava将两个数据源的结果合并返回，返回类型改成了Observable，使用了concat操作符来合并两个数据源，使用first操作符来返回第一个结果

``` java
public interface DataSource {
    Observable loadList();
}

public class LocalDataSource implements DataSource {
    @Override
    public Observable loadList() {
        return Observable.create(new Observable.OnSubscribe<List>() {

            @Override
            public void call(Subscriber<? super List> subscriber) {
                List list = new ArrayList();//从本地读取的缓存
                subscriber.onNext(list);
            }
        });//从本地读取缓存
    }
}

public class RemoteDataSource implements DataSource{
    @Override
    public Observable loadList() {
        return null;//从网络加载数据
    }
}

public class Repository implements DataSource{

    private DataSource mRemoteDataSource;
    private DataSource mLocalDataSource;

    public Repository(DataSource remoteDataSource,DataSource lemoteDataSource){
        mRemoteDataSource = remoteDataSource;
        mLocalDataSource = lemoteDataSource;
    }


    @Override
    public Observable loadList() {

        Observable localTask = mLocalDataSource.loadList();
        Observable remoteTask = mRemoteDataSource.loadList();

        return Observable.concat(localTask, remoteTask).first();//让本地缓存先读取，网络拉去后执行，谁先拿到数据就返回谁
    }
}
```

 2. 使用RxJava实现mPresenter.loadList中的跳转逻辑，避免了使用八百个回调来切换线程

``` java
public class PrensenterImpl implements Contract.Presenter {

    ...

    @Override
    public void loadList() {

        //UI 线程
        mView.showLoading();

        mRepository.loadList()
                .subscribeOn(Schedulers.io()) //指定上游在IO线程执行
                .observeOn(AndroidSchedulers.mainThread()) //指定下游在UI线程
                .subscribe(new Observer<List>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideLoading();
                        mView.showError();
                    }

                    @Override
                    public void onNext(List list) {
                        mView.hideLoading();
                        if (list.isEmpty()) {
                            mView.showEmpty();
                        } else {
                            mView.showList(list);
                        }
                    }
                });


    }
}
```

以上就是Google-Mvp-RxJava 的大体设计思路


## 框架搭建
从业务上来看，我们的功能看起来比较多，但是很多页面都很相似，来梳理一下

 1. 双List页面，比如[Explore][23]，[Follow][24]
 2. 单List页面，比如[Event][25]，[RepositoryList][26]，[UserList][27]
 3. 详情页面，比如[UserDetai][28]，[RepositoryDetail][29]

并且，可以看出多处跳转的页面是类似的

所以我做了很多抽取，详情可以看 [rx-mvp-sample][30]，简化了逻辑，单纯的显示了一个加载UserList的界面，用来展现架构思路，将流程化公共部分抽取到基类中，将需要实现的具体业务逻辑下放到子类

首先接口的抽取

<img src="https://raw.githubusercontent.com/yellowbaby1991/github/master/images/base_interface.png"  width = "100%"/>

 - BaseView：大部分的View都需要这几个方法，所以抽取出来
 - BaseListView：如果显示的是列表，需要增加，显示List，显示下拉加载数据，下拉加载错误，下拉结束等方法
 - BasePresenter：订阅和反订阅方法，一般用来控制RxJava任务的生命周期

然后抽取两个基类

 1. BaseSingleListPageFragment 用来显示单页List的Fragment，内部持有一个BaseListFragment来展示数据
 2. BasePresenterImpl，基本Presenterr的实现类

<img src="https://raw.githubusercontent.com/yellowbaby1991/github/master/images/base_impl.png"  width = "100%"/>

经过抽取后的UserListPresenter和UserListPageFragment异常简单

``` java
public class UserListPresenter extends BasePresenterImpl<UserListContract.View> implements UserListContract.Presenter {

    public UserListPresenter(@NonNull Repository repository, UserListContract.View view) {
        super(repository, view);
    }

    @Override
    public void searchUsersByUrl(String url) {
        mView.showLoading();
        SubscriptionCreator.searchListSubscription(mSubscriptions, mRepository.getUsersByUrl(url, 1), mView);
    }

    @Override
    public void loadMoreUsersByUrl(String url, int page) {
        SubscriptionCreator.loadMoreSubscription(mSubscriptions, mRepository.getUsersByUrl(url, page), mView);
    }
}
```

``` java
public class UserListPageFragment extends BaseSingleListPageFragment<UserListContract.Presenter> implements UserListContract.View,UserListFragment.UserListListener {

    private String mUrl;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mPresenter = new UserListPresenter(mRepository, this);

        mPresenter.searchUsersByUrl(mUrl);
    }

    @Override
    protected BaseListFragment getListFragment() {
        return new UserListFragment(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.user_list_page;
    }

    @Override
    public void loadMoreUser(int nextPage) {
        mPresenter.loadMoreUsersByUrl(mUrl, nextPage);
    }

    public void setUrl(String url) {
        mUrl = url;
    }

}
```


  [1]: https://github.com/yellowbaby1991/github
  [2]: https://github.com/googlesamples/android-architecture/tree/todo-mvp-rxjava/
  [3]: https://github.com/ReactiveX/RxJava
  [4]: https://github.com/ReactiveX/RxAndroid
  [5]: https://github.com/JakeWharton/butterknife
  [6]: https://github.com/infinum/android_dbinspector
  [7]: https://github.com/alibaba/fastjson
  [8]: https://github.com/square/retrofit
  [9]: https://github.com/square/okhttp/wiki/Interceptors
  [10]: https://github.com/bumptech/glide
  [11]: https://github.com/MiguelCatalan/MaterialSearchView
  [12]: https://github.com/hdodenhof/CircleImageView
  [13]: https://github.com/CymChad/BaseRecyclerViewAdapterHelper
  [14]: https://github.com/makovkastar/FloatingActionButton
  [15]: https://github.com/d-max/spots-dialog
  [16]: https://github.com/pnikosis/materialish-progress
  [17]: https://github.com/mukeshsolanki/MarkdownView-Android
  [18]: https://github.com/tajchert/WaitingDots
  [19]: https://github.com/Thereisnospon/CodeView
  [20]: https://github.com/afollestad/material-dialogs
  [21]: https://github.com/googlesamples/android-architecture/tree/todo-mvp-rxjava/
  [22]: http://gank.io/post/560e15be2dca930e00da1083
  [23]: #explore
  [24]: #userlist
  [25]: #event
  [26]: #repositoryList
  [27]: #userList
  [28]: #userDetai
  [29]: #repositoryDetail
  [30]: https://github.com/yellowbaby1991/github/tree/master/rx-mvp-sample
## 缓存设计
一开始我的想法是将讲本次访问的URL和得到的Json内容存在本地数据库中，然后采用谷歌的设计方案，两个数据源，得到结果合并，如果本地没有缓存，在拉取网路缓存后就存入本地后再返回，流程如下

<img src="https://raw.githubusercontent.com/yellowbaby1991/github/master/images/mvp_cache.png"  width = "100%"/>

代码结构就是这样的

``` java
public class GithubDataRepository implements GithubDataSource {
	...
    @Override
    public Observable getUsersByUrl(String url, int page) {

        final String key = KeyFactory.getUrlKey(url, page);

        Observable localTask = mLocalDataSource.getUsersByUrl(url, page);

        Observable remoteTask = mRemoteDataSource.getUsersByUrl(url, page).doOnNext(new BaseSaveAction<List<UserBean>>(key));

        return Observable.concat(localTask, remoteTask).first();
    }
	...
}
```

同样也做了一些基类的抽取，看起来比较简洁，两个数据源，一个读本地，一个读网路，第一个返回成功了就不再执行第二个请求

嗯，我一开始的确是这样做的，直到我发现了Retrofit+Okhttp自带了很强大的缓存

只需要配置拦截器和加上相应的请求头，Retrofit会自动的缓存，可以控制有网的时候缓存多长时间，没网情况强制读取缓存

使用步骤为，具体看代码

 1. 配置缓存目录
 2. 配置request Cache-Control
 3. 配置response Cache-Control