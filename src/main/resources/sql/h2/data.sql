--初始化动态脚本语言--
insert into DYNAMIC_SCRIPT values (1,'GROOVY','2013-04-08 13:34:07.557','GroovyMessenger.groovy','import com.xinyuan.haze.demo.groovy.Messenger;
  class GroovyMessenger implements Messenger {
       String message;public String getMessage() {
          return this.message+"即时更新";
        }
 } ');
 
--初始化用户表"admin"用户--
insert into sec_user values(0,'admin','超级管理员','1e62a9765ffd4185c89de5292f5da99f26120355','bc6a63ff359aee7c','M',NULL,'E',NULL);

--初始化配置表--
insert into SEC_CONFIG values(1,'validateCode','是否启用登录时验证码','S','D','’E‘表示启用，’D‘表示禁用');