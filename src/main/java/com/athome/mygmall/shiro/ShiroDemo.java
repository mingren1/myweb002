package com.athome.mygmall.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroDemo {

	private static final Logger log = LoggerFactory.getLogger(ShiroDemo.class);
    public static void main(String[] args) {
        //1.创建SecurityManagerFactory
        IniSecurityManagerFactory factory =
                new IniSecurityManagerFactory("classpath:shiro.ini");
        //2.获取SecurityManager,绑定到SecurityUtils中
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
//        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3.获取一个用户识别信息
        Subject currentUser = SecurityUtils.getSubject();
        //4.判断是否已经身份验证
        if (!currentUser.isAuthenticated()) {
            // 4.1把用户名和密码封装为 UsernamePasswordToken 对象
            UsernamePasswordToken token = new UsernamePasswordToken("guest", "guest");
            // 4.2设置rememberme
            token.setRememberMe(true);
            try {
                // 4.3登录.
                currentUser.login(token);
            }
            catch (UnknownAccountException uae) { //用户不存在异常
                log.info("****---->用户名不存在： " + token.getPrincipal());
                return;
            }
            catch (IncorrectCredentialsException ice) {// 密码不匹配异常
                log.info("****---->" + token.getPrincipal() + " 的密码错误!");
                return;
            }
            catch (LockedAccountException lae) {// 用户被锁定
                log.info("****---->用户 " + token.getPrincipal() + " 已被锁定");
            }
            catch (AuthenticationException ae) { // 其他异常，认证异常的父类
                log.info("****---->用户" + token.getPrincipal() + " 验证发生异常");
            }
        }

        // 5.权限测试：
        //5.1判断用户是否有某个角色
        if (currentUser.hasRole("guest")) {
            log.info("****---->用户拥有角色guest!");
        } else {
            log.info("****---->用户没有拥有角色guest");
            return;
        }
        //5.2判断用户是否执行某个操作的权限
        if (currentUser.isPermitted("see")) {
            log.info("****----> 用户拥有执行此功能的权限");
        } else {
            log.info("****---->用户没有拥有执行此功能的权限");
        }

        //6.退出
        System.out.println("****---->" + currentUser.isAuthenticated());
        currentUser.logout();
        System.out.println("****---->" + currentUser.isAuthenticated());

    }
}
