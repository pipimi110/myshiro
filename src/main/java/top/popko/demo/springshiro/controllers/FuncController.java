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
import java.util.Arrays;

//@RestController
@Controller
@RequestMapping({"/"})
public class FuncController {
    public FuncController() {
    }

//    @PostMapping({"/doLogin"})
//    public void doLogin(String username, String password) {
//        Subject subject = SecurityUtils.getSubject();
//
//        try {
//            subject.login(new UsernamePasswordToken(username, password));
//            System.out.println("success");
//        } catch (AuthenticationException var5) {
//            var5.printStackTrace();
//            System.out.println("fail!");
//        }
//
//    }

    @RequiresUser
    @GetMapping({"/userIndex"})
    public String userIndex() {
        return "userIndex";
    }

//    @RequiresUser
    @RequiresGuest
    @GetMapping({"/fun2"})
    public String fun2() {
        return "userIndex";
    }
}