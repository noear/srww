

* 使用集成式网关
  * 组装式拦截器
* 每接口单文件
* 使用xml sql开发dao（以强调sql透明性和可审核性）

#### 依赖配置

```xml
<project>
    <parent>
        <groupId>org.noear</groupId>
        <artifactId>srww-parent</artifactId>
        <version>1.0.1</version>
    </parent>
    
    <dependencies>
        <dependency>
            <groupId>org.noear</groupId>
            <artifactId>srww-api</artifactId>
        </dependency>
    </dependencies>
</project>
```