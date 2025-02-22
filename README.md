# SE2321 & SE3353 BookStore-Backend

## 项目情况简介

本项目为SE2321 & SE3353的课程后端项目，主要功能是提供一个在线书店系统，支持用户浏览书籍、添加到购物车、下单购买等操作。
由于本项目实现内容较多，电脑负载较大（16GB RAM Windows 11稍显卡顿），因此，SE3353期中后任务，每个功能单独设立分支，与前端项目分别对应。

## 分支介绍
共有以下分支：
- master
- eureka
- feature-after-middle
- feature-after-middle-graphql
- feature-auth-in-db
- feature-eureka
- feature-serverless-order
-   gateway
- get-author
- serverless

## 分支说明

- master：主分支，包含所有功能
- eureka：Eureka注册中心
- feature-after-middle：期中后任务
- feature-after-middle-graphql：期中后任务（GraphQL）
- feature-auth-in-db：期中后任务（在数据库中进行鉴权，master中使用加盐哈希，在后端进行鉴权）
- feature-eureka：期中后任务（Eureka注册中心）
- feature-serverless-order：期中后任务（无服务器订单）
- gateway：期中后任务（网关）
- get-author：期中后任务（获取作者，微服务demo）
- serverless：期中后任务（无服务相关代码）

## 使用说明

如需运行，请配置application.yml中的数据库密码。


如有说明不正确的地方，请及时联系我<qiweic10@sjtu.edu.cn>
