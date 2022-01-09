

## 示例说明：


### 1、基于 srww-admin 构建的管理后台示例（从简到繁的演化）：

| 示例   | 介绍                                           |  备注               |
|------|----------------------------------------------|------------------|
| srww-admin-demo0 | 只有一个类，连接water环境即可运行；能看到菜单，但没有功能实现            | 有water环境即可运行     |
| srww-admin-demo1 | srww-admin-demo0 基础上，加了1个控制器；对应的功能菜单可操作 |  有water环境即可运行     |
| srww-admin-demo2 | srww-admin-demo1 基础上，增加更多的功能和体系结构            |  有water环境即可运行     |


### 2、基于 srww-api 构建的接口服务示例：

| 示例   | 介绍                                    | 备注               |
|------|---------------------------------------|------------------|
| srww-api-demo0 | 只有一个网关、一个接口                           | 有water环境即可运行单元测试 |
| srww-api-demo1 | srww-api-demo0 基础上，增加真接实口实现           | 需要water和sponge环境 |
| srww-api-demo2 | srww-api-demo1 基础，增加更多的功能、体系结构及业务接口单测 | 需要water和sponge环境 |


**为了配合演示：**

* water/配置管理/日志配置，添加：demo/demoapi_log
* sponge/应用控制/应用，添加一个应用。并修改单测代码中的 app_key,app_secret_key


一些细节问题，可通过检查和实现完成配置。在过程中不断了解