## 起源
最近公司在做整体业务到 saas 的转型, 在原来做推送的时候因为是内部使用, 所以实现方式很随便

但考虑到 saas 的实现, 涉及到各个租户的消息推送, 若依旧按照原方案耦合性太大, 无灵活可言

在查阅了很多博客后决定自己撸一套代码, 本着不重复造轮子的原则, 其中大部分借鉴了 3y 的 austin 的实现方式 😉

我觉得 3y 的消息平台整体思路是很完善的, 但本项目较 austin 内部架构变动较大, 毕竟是内部项目的 demo 需要符合实际情况

## 为什么叫 phoebe ?

phoebe 中译为 福柏 , 是古希腊神话中的光亮女神, 拥有神谕的发布权, 发布神谕与推送消息是不是很相符 😎


## 项目介绍

phoebe 用于公司内部消息发送等需求场景, 对各类消息进行统一发送处理记录

更能让开发人员专注于业务, 有利于功能收拢

## 核心流程

目前暂定
![phoebe核心流程.png](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/e4042fff5729417585be589f45087ed8~tplv-k3u1fbpfcp-watermark.image?)

## 涉及技术

- 前端低代码平台 (未定)
- SpringBoot
- Nacos
- Sentinel (或用 Redis 限流算法实现)
- MyBatis-Plus
- Redis
- RocketMQ
- ELK (ElasticSearch + Logstash + Kibana) 视后续情况考虑添加 Filebeat
- 各类服务商 SDK or API (钉钉, Email, 短信, JPush......)
- ......

## 使用须知
目前 phoebe 项目还未成型, 运行需强依赖 MySQL/RocketMQ/Nacos, 请在本地配置好

1. MySQL 我本地使用的是 `8.0.25`, 因为当时去下载没有仔细看, 稳定使用建议更换为 `5.7x`, 要记得修改对应 pom
2. 使用时在对应 application.yml 中添加自己的相关配置
3. 执行 sql 文件夹下 phoebe.sql 创建对应表, 插入测试数据
4. 暂未想好 ...