
[![Maven Central](https://img.shields.io/maven-central/v/org.noear/srww.base.svg)](https://mvnrepository.com/search?q=g:org.noear%20AND%20srww.base)

` QQ交流群：22200020 `


# srww 集成框架（solon + rock + weed + water）


## 一、srwww-api（统一接口开发集成框架）

#### 集成框架

参考 srwww-api/pom.xml 配置内容

#### 特性

* 采用 luna 结构组织模型
* 采用集成式网关
* 集成大量常用网关拦截器
* 集成 jwt token
* 集成接口性能、日志自动记录（由 Water 提供支持）
* 集成慢SQL自动记录
* 集成渠道能力（由 Rock rpc 提供支持）
* 集成状态码自动国际化（由 Rock rpc 提供支持）
* 集成配置服务、事件总线、日志服务、监测服务
* 集成静态内容国际化支持
* 集成缓存控制、事务控制
* 集成solon.boot

#### 要求

* 每接口一个文件
* 以开发控制器的形式开发接口
* 使用 xml sql 开发dao（以强调sql透明性和可审核性）
* 采用 service 层进行缓存与事务控制

#### 依赖配置

```xml
<project>
    <parent>
        <groupId>org.noear</groupId>
        <artifactId>srww-parent</artifactId>
        <version>1.0.7</version>
    </parent>
    
    <dependencies>
        <dependency>
            <groupId>org.noear</groupId>
            <artifactId>srww-api</artifactId>
        </dependency>
    </dependencies>
</project>
```


## 二、srwww-admin（统一管理后台开发集成框架）

#### 集成框架

参考 srww-admin/pom.xml 配置内容

#### 特性

* 采用 luna 结构组织模型
* 采用前后不分离模式，避免前后扯皮
* 集成慢SQL自动记录
* 集成登录用户的行为自动记录
* 集成登录界面
* 集成动态菜单加载与导航框架
* 集成bcf账号与权限体系
* 集成分页等必要的通用自定义控件
* 集成 durian ui css 框架（纯 CSS 语义化标签框架）***
* 集成 jtadmin js 框架（含 jquery ）
* 集成配置服务、事件总线、日志服务、监测服务
* 集成静态内容国际化支持
* 集成solon.boot
* 支持bcfdock跨系统整合

#### 要求

* 只需要开发具体内容页
* 使用 weed3.table 接口开发 Dao 层，以快速开发
* 视图模型采用统一的 ModelAndView 模型组装并输出

#### 依赖配置

```xml
<project>
    <parent>
        <groupId>org.noear</groupId>
        <artifactId>srww-parent</artifactId>
        <version>1.0.7</version>
    </parent>
    
    <dependencies>
        <dependency>
            <groupId>org.noear</groupId>
            <artifactId>srww-admin</artifactId>
        </dependency>
    </dependencies>
</project>
```
