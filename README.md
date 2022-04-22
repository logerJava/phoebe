## 起源
最近公司在做整体业务到 saas 的转型, 在原来做推送的时候因为是内部使用, 所以实现方式很随便

但考虑到 saas 的实现, 涉及到各个租户的消息推送, 若依旧按照原方案耦合性太大, 无灵活可言

在查阅了很多博客后决定自己撸一套代码, 本着不重复造轮子的原则, 其中大部分借鉴了 3y 的 austin 的实现方式 😉

我觉得 3y 的消息平台整体思路是很完善的, 但本项目较 austin 内部架构变动较大, 毕竟是内部项目的 demo 需要符合实际情况

## 为什么叫 phoebe ?

phoebe 中译为 福柏 , 是古希腊神话中的光亮女神, 拥有神谕的发布权, 发布神谕与推送消息是不是很相符 😎


## 项目介绍

暂无

## 核心流程

![核心流程](https://github.com/logerJava/phoebe/blob/master/pic/phoebe%E6%A0%B8%E5%BF%83%E6%B5%81%E7%A8%8B.png)