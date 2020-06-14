# Yunke

基于 Spring Boot 2.X & Spring Security & OAuth2.0 构建的企业级项目工程

[![https://img.shields.io/badge/SpringBoot-2.3.0.RELEASE-yellow.svg?style=flat-square](https://img.shields.io/badge/SpringBoot-2.3.0.RELEASE-yellow.svg?style=flat-square)](https://img.shields.io/badge/SpringBoot-2.3.0.RELEASE-yellow.svg?style=flat-square)  [![https://img.shields.io/badge/MyBatisPlus-3.3.2-blueviolet.svg?style=flat-square](https://img.shields.io/badge/MyBatisPlus-3.3.2-blueviolet.svg?style=flat-square)](https://img.shields.io/badge/MyBatis-Plus-3.3.2-blueviolet.svg?style=flat-square) [![https://img.shields.io/badge/SpringSecurityOAuth-brightgreen.svg?style=flat-square](https://img.shields.io/badge/SpringSecurityOAuth-2.0-brightgreen.svg?style=flat-square)](https://img.shields.io/badge/SpringSecurityOAuth-2.0-brightgreen.svg?style=flat-square) 

Yunke 是一个构建于 Spring Boot 2.3.0.RELEASE & Spring Security OAuth 2 & MyBatisPlus 之上的认证服务授权服务分离、可插拔的数据源、缓存层、APM 监控服务，服务 Docker 化，第三方登录、架构清晰，开箱即用。核心技术使用了 Spring Boot、Spring Security、Spring-Admin、OAuth 2.0、MyBatisPlus、Redis 等主流框架和中间件。

## 系统特点

| index |                      feature                       |
| :---: | :------------------------------------------------: |
|   1   |    前后端分离架构，客户端和服务端纯 Token 交互     |
|   2   |             可插拔的数据源、Redis 缓存行为控制            |
|   3   |                   服务外部化配置                   |
|   4   |              模块拆分，分离式架构，系统耦合度低            |
|   5   |              RBAC 基于角色访问控制，细粒度交互控制         |



## 现有服务或模块

### 系统模块：

| 名称 | 端口 | 描述  |
| -------------- | :----: | ---------------- |
| yunke| 无 | 系统服务与模块总控 |
| yunke-core    | 8300 | 云课内部管理系统核心系统 |
| yunke-apm| 无   | 系统 APM 系统服务总控|
| yunke-apm-spring-admin | 8400   | 系统 APM 系统服务 Spring-Admin 服务监控子系统|
| yunke-common| 无 | 系统通用模块与自定义 starter 依赖总控 |
| yunke-common-core | 无 | 服务通用分离模块|
| yunke-common-datasource-starter | 无 | 分离数据源自定义 starter |
| yunke-common-redis-starter | 无 | 分离 Redis 缓存自定义 starter |
| yunke-common-security-starter | 无 | 分离服务认证授权与资源保护自定义 starter |
### 三方模块：

| 服务名称 | 端口 | 描述                            |
| -------- | ---- | ------------------------------- |
| MySQL    | 3306 | MySQL 数据库                    |
| Redis    | 6379 | K-V 缓存数据库                  |
| ELK | 4560 | 日志收集与分析系统             |