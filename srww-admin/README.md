
# srwww-admin（统一管理后台开发集成框架）

#### 集成框架

参考 pom.xml 配置内容

#### 特性

* 采用 luna 结构模型
* 采用前后不分离模式 
* 集成慢SQL自动记录
* 集成登录用户的行为自动记录  
* 集成登录界面（由 Bcf 框架提供支持）
* 集成动态菜单加载与导航框架（由 Bcf 框架提供支持）
* 集成bcf账号与权限体系（由 Bcf 框架提供支持）
* 集成几个通用自定义控件  
* 集成 durian ui css 框架（纯 CSS 语义化标签框架）
* 集成 jtadmin js 框架（含 jquery ）  

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
        <version>1.0.3-m2</version>
    </parent>
    
    <dependencies>
        <dependency>
            <groupId>org.noear</groupId>
            <artifactId>srww-admin</artifactId>
        </dependency>
    </dependencies>
</project>
```