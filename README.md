* [Github-可能是最好用的github客户端](#github-可能是最好用的github客户端)
	* [项目的起因](#项目的起因)
* [本文的目的](#本文的目的)
* [功能介绍](#功能介绍)
	* [Explore](#explore)
	* [RepositoryDetail](#repositorydetail)
	* [UserDetail](#userdetail)
	* [RepositoryList](#repositorylist)
	* [UserList](#userlist)
	* [Event](#event)
	* [Setting](#setting)

# Github-可能是最好用的github客户端
## 项目的起因

Github上一个看起来很漂亮的Github客户端

成功引起了我的注意

但是，它不开源

作者在贴了一堆截图后留下了自己的商务合作邮箱

我不开心所以也想做一个

# 本文的目的

 1. 如果你是青铜玩家：希望本文提供的一些资料（如API，架构，三方，功能实现思路等）可以给你一些参考价值
 2. 如果你是王者玩家：欢迎指出问题和建议，尤其是优化和架构方面

  
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