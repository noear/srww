#### 1.8.2
* 升级 solon 为 1.10.6
* 升级 snack 为 3.2.41

#### 1.8.1
* 升级 solon 为 1.10.0
* 升级 water 为 2.8.2（需要 water server 2.8.0 支持）
* 升级 snack 为 3.2.31
* 升级 weed3 为 3.4.27

#### 1.8.0
* 升级 solon 为 1.9.1
* 升级 water 为 2.8.0（需要 water server 2.8.0 支持）
* 升级 snack 为 3.2.31
* 升级 weed3 为 3.4.27

#### 1.7.2
* 升级 solon 为 1.8.3
* 升级 water 为 2.7.2
* 升级 snack 为 3.2.29
* 升级 weed3 为 3.4.26

#### 1.7.1
* 升级 solon 为 1.8.2
* 升级 water 为 2.7.1

#### 1.7.0
* 升级 solon 为 1.8.0
* 升级 snack 为 3.2.26
* 升级 rock 为 2.2.0
* 升级 water 为 2.7.0

#### 1.6.1
* 升级 solon 为 1.7.7

#### 1.6.0
* 升级 solon 为 1.7.6
* 升级 snack 为 3.2.22
* 升级 weed3 为 3.4.25
* 升级 redisx 为 1.4.3
* 升级 rock 为 2.1.4
* 升级 water 为 2.6.3

#### 1.5.3
* 升级 solon 为 1.7.3
* 升级 snack 为 3.2.21
* 升级 weed3 为 3.4.24
* 升级 grit 为 1.1.4
* 升级 redisx 为 1.4.2
* 升级 rock 为 2.1.3
* 升级 water 为 2.6.2

#### 1.5.2
* 升级 solon 为 1.7.2
* 升级 rock 为 2.1.2
* 升级 water 为 2.6.1

#### 1.5.0
* 升级 rock 为 2.1.1
* 升级 snack 为 3.2.20

#### 1.5.0
* 升级 solon 为 1.7.1
* 升级 water 为 2.6.0
* 升级 rock 为 2.1.0
* srww.uapi 取消对 rock.client 的依赖
  * 接口状态码，改由标准的国际化接口 （之前为 rock 专用接口；现在默认为 water 新接口）
  * 应用访问密钥，改为新的 IApp 接口（之前为 rock 专用模型；现为按需适配）

#### 1.4.24
* 升级 solon 为 1.7.0
* 升级 water 为 2.5.10
* srww.uapi 接收 ONode 结果值时，重新转为 Object ；之后，再渲染

#### 1.4.23
* 升级 solon 为 1.6.36

#### 1.4.22
* 升级 solon 为 1.6.35
* 升级 water 为 2.5.8
* 升级 grit 为 1.0.10
* 升级 redisx 为 1.3.6
* 解决 uapi 返回 ONode 类型时，会跳过渲染工厂设定的问题

#### 1.4.18
* 升级 solon 为 1.6.30
* 升级 water 为 2.5.7
* 升级 grit 为 1.0.8

#### 1.4.17
* 升级 solon 为 1.6.29
* 升级 weed3 为 3.4.19

#### 1.4.16
* 升级 solon 为 1.6.28

#### 1.4.15
* 升级 solon 为 1.6.27
* 升级 snack3 为 3.2.16

#### 1.4.14
* 升级 solon 为 1.6.24
* 升级 water 为 2.5.6
* 升级 weed3 为 3.4.18

#### 1.4.12
* 升级 solon 为 1.6.23

#### 1.4.11
* 升级 solon 为 1.6.18
* 升级 water 为 2.5.5
* 升级 snack3 为 3.2.11
* 升级 weed3 为 3.4.16

#### 1.4.10
* 升级 solon 为 1.6.18
* 升级 redisx 为 1.3.3
* 升级 snack3 为 3.2.10
* 升级 weed3 为 3.4.15

#### 1.4.8
* 升级 solon 为 1.6.14
* 升级 water 为 2.5.4
* 升级 redisx 为 1.3.2

#### 1.4.7
* 升级 solon 为 1.6.12
* 升级 snack3 为 3.2.7
* 升级 weed3 为 3.4.12

#### 1.4.6
* 升级 solon 为 1.6.9
* 升级 snack3 为 3.2.3
* 升级 water 为 2.5.3

#### 1.4.5
* 升级 solon 为 1.6.7
* 升级 snack3 为 3.2.2
* 调整 OutputBuildHandler 去掉 ONode 依赖，改为上下文的渲染并返回接口

#### 1.4.4
* 升级 solon 为 1.6.6
* 升级 water 为 2.5.2
* 升级 snack3 为 3.2.1

#### 1.4.3 
* srww.uadmin 组件，将 bcf 切换为 grit
* 升级 solon 为 1.6.5
* 升级 water 为 2.5.1 //开头的触发规则统一为：/_run/ 开头

