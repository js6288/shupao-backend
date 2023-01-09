# 用户中心后端
## 项目简介

一个帮助大家找到志同道合的伙伴的移动端网站（APP 风格），包括用户登录注册、更新个人信息、按标签搜索用户、推荐相似用户、组队等功能。

主页：

![image-20230109230729963](http://img-md-js.linjsblog.top/img/202301092308527.png)

找伙伴：

![image-20230109230805077](http://img-md-js.linjsblog.top/img/202301092308537.png)

组队功能：

![image-20230109230921830](http://img-md-js.linjsblog.top/img/202301092309821.png)

创建队伍：

![image-20230109230947834](http://img-md-js.linjsblog.top/img/202301092309973.png)

个人信息

![image-20230109231008752](http://img-md-js.linjsblog.top/img/202301092310807.png)

### 技术选型

#### 前端

1. Vue 3
2. Vant UI 组件库
3. Vite 脚手架
4. Axios 请求库

#### 后端

1. Java SpringBoot 2.7.x 框架
2. MySQL 数据库
3. MyBatis-Plus
4. MyBatis X 自动生成
5. Redis 缓存（Spring Data Redis 等多种实现方式）
6. Redisson 分布式锁
7. Easy Excel 数据导入
8. Spring Scheduler 定时任务
9. Swagger + Knife4j 接口文档
10. Gson：JSON 序列化库
11. 相似度匹配算法

#### 部署

1. Serverless 服务
2. 云原生容器平台


#### 亮点
1. 用户登录：使用 Redis 实现分布式 Session，解决集群间登录态同步问题；并使用 Hash 代替 String 来存储用户信息，节约了 xx% 的内存并便于单字段的修改。（需要自己实际测试对比数据，节省内存的原因是不用保存序列化对象信息或者 JSON 的一些额外字符串）
2. 对于项目中复杂的集合处理（比如为队伍列表关联已加入队伍的用户），使用 Java 8 Stream API 和 Lambda 表达式来简化编码。
3. 使用 Easy Excel 读取收集来的基础用户信息，并通过自定义线程池 + CompletableFuture 并发编程提高批量导入数据库的性能。实测导入 100 万行的时间从 xx 秒缩短至 xx 秒。（需要自己实际测试对比数据）
4. 使用 Redis 缓存首页高频访问的用户信息列表，将接口响应时长从 xx 秒缩短至 xx 秒。且通过自定义 Redis 序列化器来解决数据乱码、空间浪费的问题。
5. 为解决首次访问系统的用户主页加载过慢的问题，使用 Spring Scheduler 定时任务来实现缓存预热，并通过分布式锁保证多机部署时定时任务不会重复执行。
6. 为解决同一用户重复加入队伍、入队人数超限的问题，使用 Redisson 分布式锁来实现操作互斥，保证了接口幂等性。
7. 使用编辑距离算法实现了根据标签匹配最相似用户的功能，并通过优先队列来减少 TOP N 运算过程中的内存占用。 
8. 自主编写 Dockerfile，并通过第三方容器托管平台实现自动化镜像构建及容器部署，提高部署上线效率。
9. 使用 Knife4j + Swagger 自动生成后端接口文档，并通过编写 ApiOperation 等注解补充接口注释，避免了人工编写维护文档的麻烦。
10. 前端使用 Vant UI 组件库，并封装了全局通用的 Layout 组件，使主页、搜索页、组队页布局一致、并减少重复代码。
11. 基于 Vue Router 全局路由守卫实现了根据不同页面来动态切换导航栏标题， 并通过在全局路由配置文件扩展 title 字段来减少无意义的 if else 代码。

。。。。
