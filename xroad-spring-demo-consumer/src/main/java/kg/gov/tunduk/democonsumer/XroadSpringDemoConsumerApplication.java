package kg.gov.tunduk.democonsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class XroadSpringDemoConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(XroadSpringDemoConsumerApplication.class, args);
    }
}
