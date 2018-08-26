package boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EnableScheduling
public class Application {
      public static void main(String[] args) {
          try {
			SpringApplication.run(Application.class, args);
		} catch (Exception e) {
			System.out.println(e);
		}
      }
      
}

