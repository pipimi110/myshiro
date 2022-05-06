//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package top.popko.demo.springshiro.controllers;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.shiro.mgt.SecurityManager;
//import org.testng.Assert;

import java.util.Arrays;

//@RestController
@Controller
@RequestMapping({"/"})
public class LoginController {
    public LoginController() {
    }

    @RequiresGuest
    @GetMapping({"/login"})
    public String login() {
        return "login";
    }

    @RequiresGuest
    @PostMapping({"/doLogin"})
    public String doLogin(String username, String password) {
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(new UsernamePasswordToken(username, password));
            System.out.println("success");
            return "redirect:/userIndex";
        } catch (AuthenticationException var5) {
            var5.printStackTrace();
            System.out.println("fail!");
            return "redirect:/login";
        }

    }

    @RequiresUser
    @GetMapping({"/logout"})
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.logout();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "please login!";
    }

    public static void main(String[] args) {

        String ini = "classpath:shiro.ini";
//        String ini = "classpath:shiro-realm.ini";
        String username = "zhang";
        String password = "123";
//        String username = "popko";
//        String password = "sakura";

        Subject subject = init(ini);
        login1(subject,username, password);
//        login1(subject, "admin", "admin");
        adminAction();
        subject.getPrincipal();
        Session session = subject.getSession();
        session.setAttribute( "someKey", "someValue");
//        role1();
        permit1();
        subject.logout();
    }

    public static void permit1() {
        Subject subject = SecurityUtils.getSubject();
//        Assert.assertTrue(subject.isPermitted("user:create"));
//        Assert.assertTrue(subject.isPermitted("user:create:1"));
//        Assert.assertTrue(subject.isPermitted("user:*"));
//        Assert.assertTrue(subject.isPermitted("user:*:1"));
        subject.checkPermission("user:*:1");
//        Assert.assertTrue(subject.isPermitted("user:create1"));

    }

    public static void role1() {
        Subject subject = SecurityUtils.getSubject();
//        Assert.assertTrue(subject.hasRole("role1"));
        subject.checkRole("role3");//相当于assert
//        Assert.assertTrue(subject.hasAllRoles(Arrays.asList("role1", "role2")));
//        Assert.assertTrue(subject.hasAllRoles(Arrays.asList("role1","role2","role3")));//hasAll
        boolean[] result = subject.hasRoles(Arrays.asList("role1", "role2", "role3"));//hasRoles返回结果数组
        System.out.println(result[0]);
        System.out.println(result[1]);
        System.out.println(result[2]);
    }

    @RequiresRoles("admin")
    public static void adminAction() {
        System.out.println("adminAction");
    }


    public static Subject init(String ini) {
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(ini);//指定ini文件位置
        //2、得到SecurityManager实例 并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        return subject;
    }
    public static void login1(Subject subject, String username, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            //4、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException var5) {
            //5、身份验证失败
        }
//        Assert.assertEquals(true, subject.isAuthenticated());//断言用户已经登录
        //6、退出
//        subject.logout();
    }
}
