package boot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EnableScheduling
public class Application implements CommandLineRunner{
      public static void main(String[] args) {
          try {
			SpringApplication.run(Application.class, args);
		} catch (Exception e) {
			System.out.println(e);
		}
      }

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}

