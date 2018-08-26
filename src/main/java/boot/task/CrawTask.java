package boot.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CrawTask {
    
    @Scheduled(fixedRate = 3000)
    public void process(){
       System.out.println("1111");
    }
}
