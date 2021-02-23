
# srwww-api（统一接口开发集成框架）

#### 集成框架

参考 pom.xml 配置内容

#### 特性

* 采用 luna 结构模型
* 采用集成式网关
* 集成大量网关拦截器
* 集成 jwt token
* 集成接口性能、日志自动记录（由 Water 提供支持）
* 集成慢SQL自动记录 
* 集成渠道能力（由 Rock rpc 提供支持）
* 集成状态码自动国际化（由 Rock rpc 提供支持）

#### 要求

* 每接口一个文件
* 以开发控制器形式开发接口
* 使用 xml sql 开发dao（以强调sql透明性和可审核性）

#### 依赖配置

```xml
<project>
    <parent>
        <groupId>org.noear</groupId>
        <artifactId>srww-parent</artifactId>
        <version>1.0.3-m6</version>
    </parent>
    
    <dependencies>
        <dependency>
            <groupId>org.noear</groupId>
            <artifactId>srww-api</artifactId>
        </dependency>
    </dependencies>
</project>
```