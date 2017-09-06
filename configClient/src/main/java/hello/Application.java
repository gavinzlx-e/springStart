package hello;
/*

没有加上 包名
Failed to read candidate component class: URL [jar:file:/Users/ericens/.m2/repository/org/springframework/boot/spring-boot-autoconfigure/1.2.3.RELEASE/spring-boot-autoconfigure-1.2.3.RELEASE.jar!/org/springframework/boot/autoconfigure/jdbc/DataSourceAutoConfiguration$JdbcTemplateConfiguration.class]; nested exception is java.lang.IllegalStateException: Could not evaluate condition on org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration$JdbcTemplateConfiguration due to internal class not found. This can happen if you are @ComponentScanning a springframework package (e.g. if you put a @ComponentScan in the default package by mistake)

 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by @author linxin on 05/09/2017.  <br>
 */
@SpringBootApplication
@RestController
public class Application {

    @RequestMapping("/")
    public String home() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);


    }


    // this is test for config server
    @Value("${foo}")
    String foo;
    @RequestMapping(value = "/fooTest")
    public String hi(){
        return foo;
    }


}
