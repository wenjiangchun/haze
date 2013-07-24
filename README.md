框架说明

使用Spring+Hibernate+Spring Data JPA+Spring MVC开发技术。
权限验证采用Apache Shiro控制，目前系统权限基于角色分配，权限资源包括菜单和操作权限。
数据持久化采用基于注解JPA方式，JPA实现框架使用Hibernate，缓存使用Echache。
集成Activiti工作流
使用Maven构建项目
前台样式使用BootStrap + Jquery


运行方式：

首先初始化数据库：运行命令mvn initialize -Pinitialize-db

然后运行mvn jetty:run
