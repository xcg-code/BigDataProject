# BigDataProject
基于spring+hibernate web的大数据应用项目案例

## 项目架构图
---
![](https://github.com/xcg-code/BigDataProject/blob/master/image/project.png)

## 相关技术
---
1.电商业务部分使用spring mvc 作为web层实现，springframework作为业务层框架，采用hibernate实现数据持久化，并采用servlet3.0规范实现纯注解编程。

2.采用maven实现项目依赖管理，并搭建局域网内的maven仓库服务器。

3.使用Linux的cron调度任务实现nginx日志滚动，hive的周期性数据清洗。

4.使用nginx作为反向代理服务器和静态资源的web服务器，tomcat作为动态业务处理web服务器，与nginx服务实现动静分离。

5.使用flume对nginx web服务器的周期日志进行收集，数据进入kafka集群。

6.kafka中对同一主题使用两个消费者，分属不同组，一个消费者将原生log数据写入到hdfs进行存储，而一个消费者对log进行清洗，并将清洗之后的数据写入到hive的分区表中。

7.使用cron定期动态添加hive的表分区，并执行hiveQL脚本，对数据进行统计。

8.统计结果存放在hive的统计表中，该表使用hbase存储引擎，最终数据存放在hbase库中。

该上传部分只含有web端代码，以及kafka消费者相关代码。

 
 
