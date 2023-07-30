## personnel

personnel = Spring Boot后端 + Vue管理员前端，该项目非常适合新手学习的人事管理系统。

## 项目介绍

##### 功能

![输入图片说明](images/%E4%BA%BA%E4%BA%8B%E7%AE%A1%E7%90%86%E7%B3%BB%E7%BB%9F.png)

##### 项目演示

1. 组织管理
   
   ![输入图片说明](images/1.%E7%BB%84%E7%BB%87%E7%AE%A1%E7%90%86.png)

2. 部门管理
   
   ![输入图片说明](images/2.%E9%83%A8%E9%97%A8%E7%AE%A1%E7%90%86.png)

3. 人员登记
   
   ![输入图片说明](images/3.%E4%BA%BA%E5%91%98%E7%99%BB%E8%AE%B0.png)

4. 添加人员信息
   
   ![输入图片说明](images/4.%E6%B7%BB%E5%8A%A0%E4%BA%BA%E5%91%98%E4%BF%A1%E6%81%AF.png)

5. 人员合同
   
   ![输入图片说明](images/5.%E4%BA%BA%E5%91%98%E5%90%88%E5%90%8C.png)

6. 操作日志
   
   ![输入图片说明](images/6.%E6%93%8D%E4%BD%9C%E6%97%A5%E5%BF%97.png)

## 技术栈

##### 后端技术

| 技术               | 说明            | 官网                                             |
| ---------------- | ------------- | ---------------------------------------------- |
| SpringBoot       | Web应用开发框架     | https://spring.io/projects/spring-boot         |
| MyBatis          | ORM框架         | http://www.mybatis.org/mybatis3/zh/index.html  |
| MyBatisGenerator | 数据层代码生成器      | http://www.mybatis.org/generator/index.html    |
| Druid            | 数据库连接池        | https://github.com/alibaba/druid               |
| PageHelper       | MyBatis物理分页插件 | http://git.oschina.net/free/Mybatis_PageHelper |

##### 前端技术

| 技术         | 说明       | 官网                                       |
| ---------- | -------- | ---------------------------------------- |
| Vue        | 前端框架     | https://vuejs.org/                       |
| Vue-router | 路由框架     | https://router.vuejs.org/                |
| Vuex       | 全局状态管理框架 | https://vuex.vuejs.org/                  |
| Element    | 前端UI框架   | https://element.eleme.io                 |
| Axios      | 前端HTTP框架 | https://github.com/axios/axios           |
| Echarts    | 图表框架     | https://echarts.apache.org/zh/index.html |

## 环境搭建

##### 开发工具

| 技术            | 说明          | 官网                                              |
| ------------- | ----------- | ----------------------------------------------- |
| IDEA          | 开发IDE       | https://www.jetbrains.com/idea/download         |
| X-shell       | Linux远程连接工具 | http://www.netsarang.com/download/software.html |
| Navicat       | 数据库连接工具     | http://www.formysql.com/xiazai.html             |
| PowerDesigner | 数据库设计工具     | http://powerdesigner.de/                        |
| Axure         | 原型设计工具      | https://www.axure.com/                          |
| XMind         | 思维导图设计工具    | https://xmind.cn                                |
| Postman       | API接口调试工具   | https://www.postman.com/                        |
| MarkText      | Markdown编辑器 | https://github.com/marktext/marktext            |

##### 开发环境

| 技术    | 说明   | 官网                                                                                   |
| ----- | ---- | ------------------------------------------------------------------------------------ |
| JDK   | 1.8  | https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html |
| Mysql | 5.7  | https://www.mysql.com/                                                               |
| Nginx | 1.22 | http://nginx.org/en/download.html                                                    |

##### 快速启动

1. 启动管理后台
   
   打开命令行，输入以下命令
   
   ```
   cd personnel/ep
   mvn install
   mvn clean package
   java -Dfile.encoding=UTF-8 -jar personnel/ep/target/ep-0.0.1-SNAPSHOT-exec.jar
   ```

2. 启动管理后台前端
   
   打开命令行，输入以下命令
   
   ```
   cd personnel/ep-web
   npm install --registry=https://registry.npm.taobao.org
   npm run dev
   ```
   
   此时，浏览器打开，输入网址`http://localhost:9527`, 此时进入管理后台登录页面。

## 问题

| 微信好友                                                                                          |
| --------------------------------------------------------------------------------------------- |
| <img src="images/%E5%BE%AE%E4%BF%A1%E5%A5%BD%E5%8F%8B.jpg" title="" alt="输入图片说明" width="237"> |

- 加微信，获得sql脚本，并可以加入【java小白学习交流群】学习交流

- 开发者有问题或者好的建议可以用Issues反馈交流，请给出详细信息

- 在开发交流群中应讨论开发、业务和合作问题
