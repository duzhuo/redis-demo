# redis-demo
redis学习事例

## 准备工作
1. 下载redis程序
2. 解压
3. 编译
4. 运行
5. 测试服务运行状态

## 环境搭建说明
本事例是基于maven的spring boot程序
需要安装jdk1.8

redis配置在application.properties中如下配置
spring.redis.host=127.0.0.1
spring.redis.port=6379

## 环境运行说明
* 运行RedisDemoApplication.java的main方法即可看到最基本的运行结果
* 运行test/java/目录下的测试代码，即可看到redis各数据类型的处理
