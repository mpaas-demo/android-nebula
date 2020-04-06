# mPaaS Nebula Demo

本 demo 为 [H5容器&离线包](https://help.aliyun.com/document_detail/59192.html?spm=a2c4g.11186623.6.999.45963bb7w63V2a) 单组件 demo，clone 后直接运行工程即可。

### 控制台环境

- 本 demo 对应的是公有云 mPaaS Demo 应用。
- 如需切换到您的 app，可在控制台中下载配置文件后替换 demo 中的 Ant-mpaas-570DA89281533-default-Android.config 文件。

### 支持基线与接入方式
支持 inside 和 aar 两种接入方式，支持基线：

- 10.1.60，支持 inside 方式
- 10.1.68，支持 inside 和 aar 方式
 
demo 中默认使用10.1.68基线。

### 切换接入方式

- demo中通过 gradle.properties 文件中 mPaasBuildType 字段区分接入方式，默认aar，可修改为inside（等号前后不要有空格）。
- app/build.gradle 和 根目录/build.gradle 文件中也通过 mPaasBuildType 字段针对不同的接入方式添加了相应配置，自行集成时请留意。

### 降级10.1.60
如需降级10.1.60，请按照如下步骤：

1. 删除 mpaas_packages.json 文件。
2. mPaaS IDEA插件 -> 组件管理 -> 选择基线 -> 确认。
3. mPaaS IDEA插件 -> 组件管理 -> 安装日志组件。
4. 同步工程。

注意：
* aar 接入方式无法降级10.1.60。
* 降级至 10.1.60 后，H5 自定义标题栏需要按照 10.1.60 的要求进行实现，具体可以参考这里[H5 容器 10.1.60 自定义标题栏](https://help.aliyun.com/document_detail/126890.html?spm=a2c4g.11186623.6.1014.7f62597eFmdtIq)

3. mPaaS IDEA插件 -> 组件管理 -> 安装日志组件。至
3. mPaaS IDEA插件 -> 组件管理 -> 安装日志组件。
