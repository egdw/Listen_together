package im.hdy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by hdy on 17/02/2018.
 */
@SpringBootApplication
@ComponentScan
public class Start {
    public static void main(String[] args) {
        SpringApplication.run(Start.class, args);
    }

}
//public class Start extends SpringBootServletInitializer {
//    public static void main(String[] args) {
//        SpringApplication.run(Start.class, args);
//    }
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(Start.class);
//    }
//}
