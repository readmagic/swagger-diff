package oop.fun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: frandy
 * Date: 2022/9/9
 * Time: 上午10:54
 */
@SpringBootApplication
@EnableAsync
public class Application {
    public static void main(String[] args) {
    		SpringApplication.run(Application.class, args);
    	}
}
