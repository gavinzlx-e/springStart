
package hello;

/**
 * Created by @author linxin on 05/09/2017.  <br>
 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@RibbonClient(name = "say-hello", configuration = SayHelloConfiguration.class)
public class UserApplication {

    @LoadBalanced
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;
    //makes an HTTP GET request to the Say Hello service’s URL as we provide it and gives us the result as a String.
    // (For more information on using Spring to consume a RESTful service,
    // see the Consuming a RESTful Web Service guide.)


    private static Logger log = LoggerFactory.getLogger(UserApplication.class);

    @RequestMapping("/hi")
    public String hi(@RequestParam(value="name", defaultValue="Artaban") String name) {

//        String greeting = this.restTemplate.getForObject("http://localhost:8801/greeting", String.class);
        String greeting = this.restTemplate.getForObject("http://say-hello/greeting", String.class);
        log.info("hi "+greeting+" "+name);
        return String.format("%s, %s!", greeting, name);
    }

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
