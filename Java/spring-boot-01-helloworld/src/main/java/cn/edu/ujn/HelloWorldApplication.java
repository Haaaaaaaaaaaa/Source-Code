package cn.edu.ujn;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//来标注一个主程序说明这是一个Spring Boot应用
@SpringBootApplication
public class HelloWorldApplication {
    public static void main(String[] args) {
        //Spring应用启动起来
        SpringApplication.run(HelloWorldApplication.class, args);
    }
}
