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

# Github
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

基于 [google-android-architecture-mvp-rxjava][1] 的RxJava + Retrofit + Mvp架构，该架构会在后面详细讲解，三方库如下

## Rx

- [RxJava][2] 
- [RxAndroid][3]


## 快速开发工具

- [butterknife][4] 大名鼎鼎的黄油刀，让你不再findById
- [android_dbinspector][5] 不需要root就可以查看真机上数据库内容
- [fastjson][6] 最快的json解析工具，阿里巴巴出品

## 网络相关

 - [Retrofit][7] 新一代网络请求神器
 - [OkHttp logging interceptor][8] 请求日志拦截器

## 图片加载

- [Glide][9] 图片加载框架

## UI

- [MaterialSearchView][10] Material Design风格的搜索
- [CircleImageview][11] 圆形头像
- [BaseRecyclerViewAdapterHelper][12] 强大的RecyclerView万能适配器
- [FloatingActionButton][13] Material Design风格的浮动按钮
- [spots-dialog][14] 闪烁的loding进度条
- [materialish-progress][15] 旋转的菊花圈
- [MarkdownView][16] 将markdown格式的字符串显示成漂亮的html页面
- [WaitingDots][17] 闪烁的loding动画
- [CodeView][18] 将代码显示成漂亮的样式
- [material-dialogs][19] Material风格的Dialog

  
# 功能介绍
  ## Explore

 1. 浏览Repository和User，使用选项卡切换，并且将浏览过的数据缓存在本地（本应用所有数据都做了缓存，并且缓存时间可由用户定制）

<img src="images/explore_4.png" width = "50%" /><img src="images/explore_1.png" width = "50%" />


 2. 支持关键字搜索Repository和User，可以选择排序方式（Most star，Best match，Most fork，Rencent update等），可以按标签换语言分类（排序方式和标签可用用户定制）

<img src="images/explore_2.png" width = "50%" /><img src="images/explore_3.png" width = "50%" /> 

## RepositoryDetail

 1. Repository简要信息查看，并且可以进行star，unstar，fork操作

<img src="images/repositorydetail_1.png" width = "50%" /><img src="images/repositorydetail_2.png" width = "50%" /> 

 2. 异步加载ReadMe，按markdown格式显示ReadMe
 
 <img src="images/repositorydetail_3.png" width = "50%" />
 
 3. 显示code树，查看代码内容，因为缓存的原因，点击加载过的节点可以秒加载
 
 <img src="images/repositorydetail_4.png" width = "50%" /><img src="images/repositorydetail_5.png" width = "50%" />
 
## UserDetail

 1. User简要信息查看，并且可以进行follow，unfollow操作

<img src="images/userdetail_1.png" width = "50%" /><img src="images/userdetail_2.png" width = "50%" />

## RepositoryList

 1. 查询自己的Repository
 
<img src="images/repository_1.png" width = "50%" /><img src="images/repository_2.png" width = "50%" />
 
 2. 查询自己Star过的Repository

<img src="images/repository_3.png" width = "50%" /><img src="images/repository_4.png" width = "50%" />

 3. 查询RepositoryDetail被fork过的Repository

<img src="images/repository_5.png" width = "50%" /><img src="images/repository_6.png" width = "50%" />

 4. 查询UserDetail被拥有的Repository

<img src="images/repository_7.png" width = "50%" /><img src="images/repository_8.png" width = "50%" />

## UserList

 1. 查询自己，以及其他User的following和follower

 <img src="images/user_1.png" width = "50%" /><img src="images/user_2.png" width = "50%" />
 
 2. 查询RepositoryDetail的Contributors和Stargazers
 
 <img src="images/user_3.png" width = "50%" /><img src="images/user_4.png" width = "50%" />


## Event

 1. 可以查询，自己，User，Repository的Event

<img src="images/event_1.png" width = "50%" /><img src="images/event_2.png" width = "50%" />

## Setting

 1. 设置Explore页面首先查询的语言，右下方应该有几个语言选项，默认的排序方式

<img src="images/setting_1.png" width = "50%" /><img src="images/setting_2.png" width = "50%" />


# 架构分析

谷歌去年在github上发布一整套的它推荐的Android架构Dmo，[todo-mvp-rxjava][20] 是之中用来示范rxjava的sample

关于它的这套架构，我画了一个栩栩如生的草图，嗯，栩栩如生

<img src="images/rxjava_mvp.png"  width = "100%"/>

是不是已经被我的美术功底震惊的说不出话来，就冲这图你不给Star一个？

![enter description here][21]

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

然后是Presenter的实现类，持有了view的对象和repository的对象，分别用来加载数据和展现数据

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
        mView.showError();

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
        }

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
```





  [1]: https://github.com/googlesamples/android-architecture/tree/todo-mvp-rxjava/
  [2]: https://github.com/ReactiveX/RxJava
  [3]: https://github.com/ReactiveX/RxAndroid
  [4]: https://github.com/JakeWharton/butterknife
  [5]: https://github.com/infinum/android_dbinspector
  [6]: https://github.com/alibaba/fastjson
  [7]: https://github.com/square/retrofit
  [8]: https://github.com/square/okhttp/wiki/Interceptors
  [9]: https://github.com/bumptech/glide
  [10]: https://github.com/MiguelCatalan/MaterialSearchView
  [11]: https://github.com/hdodenhof/CircleImageView
  [12]: https://github.com/CymChad/BaseRecyclerViewAdapterHelper
  [13]: https://github.com/makovkastar/FloatingActionButton
  [14]: https://github.com/d-max/spots-dialog
  [15]: https://github.com/pnikosis/materialish-progress
  [16]: https://github.com/mukeshsolanki/MarkdownView-Android
  [17]: https://github.com/tajchert/WaitingDots
  [18]: https://github.com/Thereisnospon/CodeView
  [19]: https://github.com/afollestad/material-dialogs
  [20]: https://github.com/googlesamples/android-architecture/tree/todo-mvp-rxjava/
  [21]: http://img1.imgtn.bdimg.com/it/u=1649390983,416593110&fm=26&gp=0.jpg