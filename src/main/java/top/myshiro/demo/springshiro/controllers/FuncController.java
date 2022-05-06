package top.myshiro.demo.springshiro.controllers;

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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.shiro.mgt.SecurityManager;
import top.myshiro.demo.springshiro.model.TableModel;
import top.myshiro.demo.springshiro.model.UserModel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
    public String userIndex(Model model) {
        UserModel userModel = new UserModel();
        userModel.setUsername("pipimi");
        userModel.setAge(20);
        userModel.setSex("FeMale");
        userModel.setHobby("FeMale");
        model.addAttribute("user", userModel);
        return "userIndex";
    }
    @RequiresUser
    @GetMapping({"/userMailbox"})
    public String userMailbox(Model model) {
        TableModel tableModel = new TableModel();
        tableModel.setColumns(new String[]{"编号","日期","内容","回答内容"});
        model.addAttribute("table",tableModel);
        return "userMailbox";
    }
    @RequiresUser
    @GetMapping({"/userMood"})
    public String userMood(Model model) {
        TableModel tableModel = new TableModel();
        tableModel.setColumns(new String[]{"编号","日期","心情指数","备注"});
        model.addAttribute("table",tableModel);

        return "userMood";
    }
    @RequiresUser
    @GetMapping({"/userQues"})
    public String userQues(Model model) {
        TableModel tableModel = new TableModel();
        tableModel.setColumns(new String[]{"编号","日期","内容","有无回答"});
        model.addAttribute("table",tableModel);
        return "userQues";
    }
    @RequiresUser
    @GetMapping({"/userSay"})
    public String userSay(Model model) {
        TableModel tableModel = new TableModel();
        tableModel.setColumns(new String[]{"编号","日期","内容","是否公开"});
        model.addAttribute("table",tableModel);
        return "userSay";
    }

//    @RequiresUser
    @RequiresGuest
    @GetMapping({"/fun2"})
    public String fun2() {
        return "userIndex";
    }

    @GetMapping({"/fun3"})
    public String fun3() {
        return "public/html/footer";
    }

    @GetMapping({"/fun4"})
    public String fun4() {
        return "public/html/insert";
    }
}