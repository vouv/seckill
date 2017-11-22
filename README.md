# seckill

用SpringBoot + Mybatis + mysql + redis 实现的秒杀案例

数据库连接池使用hikari

大部分组件用spring-starter快速配置 运行起来简单快速


## 项目的来源

项目的来源于慕课网,由四个系列的课程组成

1. Java高并发秒杀API之业务分析与DAO层
2. Java高并发秒杀API之web层
3. Java高并发秒杀API之Service层
4. Java高并发秒杀API之高并发优化

**Why SpringBoot**

使用springBoot能快速搭建项目，约定优于配置，省去了大量配置xml的工作。


## 项目环境


- JDK 1.8

- 项目使用Idea搭建

- 操作系统苹果macOS Sierra 10.12.6

- mysql redis基于brew安装

- 使用SpringBoot内嵌Tomcat容器运行服务


## 项目效果图


- 秒杀商品列表
![效果图](/demoImage/1.png)

- 商品详情页1
![效果图](/demoImage/2.png)

- 商品详情页2
![效果图](/demoImage/3.png)


## Getting Started

1. git clone https://github.com/monigo/seckill.git  <项目位置>

2. idea打开项目 打开src/main/resources/application.properties

3. 第一次运行时，配置 `spring.datasource.initialize=ture` 来让程序初始化数据库

4. 配置mysql登录用户名和密码

5. Run！





