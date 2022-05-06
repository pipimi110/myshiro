package top.popko.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan(
    basePackages = {"top.popko.demo"}
)
@SpringBootApplication
public class SpringShiroApplication {
    public SpringShiroApplication() {
    }

    public static void main(String[] args) {
//        ch.qos.logback.classic.Level
        SpringApplication.run(SpringShiroApplication.class, args);
    }
}
