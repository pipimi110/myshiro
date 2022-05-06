//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package top.myshiro.demo.springshiro.config;

import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.util.Factory;
import top.myshiro.demo.MyRealm;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;

@Configuration
public class ShiroConfig {
    public ShiroConfig() {
    }

    @Bean
    MyRealm myRealm() {
        return new MyRealm();
    }

    @Bean
    DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(this.myRealm());
        return manager;
    }


    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(this.securityManager());
        bean.setLoginUrl("/login");
        bean.setSuccessUrl("/index");
        bean.setUnauthorizedUrl("/unauthorizedurl");
        Map<String, String> map = new LinkedHashMap();
        map.put("/doLogin", "anon");
        map.put("/admin/*", "authc");
        bean.setFilterChainDefinitionMap(map);
        return bean;
    }
    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions)
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
