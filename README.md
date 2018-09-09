# seckill


[![Gitter](https://img.shields.io/gitter/room/nwjs/nw.js.svg)](https://gitter.im/monigo-dev/project-seckill)

![License](https://img.shields.io/packagist/l/doctrine/orm.svg)

用SpringBoot实现的秒杀案例

- Mybatis
- Mysql
- redis

数据库连接池使用hikari

优点：快速配置、运行简单快速

### 更新日志

- 更新springboot2.0
- 更新相关依赖
- 引入lombok
- 优化代码

### Why SpringBoot

使用springBoot能快速搭建项目，约定优于配置，省去了大量配置xml的工作。


## 项目环境


- JDK 1.8

- 项目使用Idea搭建

- 操作系统苹果macOS


## 项目效果图


- 秒杀商品列表
![效果图](/demoImage/1.png)

- 商品详情页1
![效果图](/demoImage/2.png)

- 商品详情页2
![效果图](/demoImage/3.png)


## Getting Started

1. git clone https://github.com/awlsx/seckill.git

2. idea打开项目 打开src/main/resources/application.yml

3. 第一次运行时，先把sql文件夹下的sql文件放到resources文件夹下，然后配置 `spring.datasource.initialize=ture` 来让程序初始化数据库

4. 配置mysql登录用户名和密码

5. Run！





