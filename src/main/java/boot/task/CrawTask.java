package boot.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import boot.RedisConfig;
import boot.shedule.service.master.CrawlerDataMasterService;
import boot.util.RedisUtil;

@Component
public class CrawTask {
    @Autowired
    private RedisUtil redisUtil;
    
    @Autowired
    CrawlerDataMasterService crawlerDataMasterService;
    
    //@Scheduled(fixedRate = 1000)
    public void process(){
    	Runnable t= new Runnable() {
    		
			@Override
			public void run() {
				try {
					crawlerDataMasterService.testAspect();
				} catch (Exception e) {
					System.out.println("错误捕捉");
				}
				
			}
		};
		Thread th =new Thread(t);
		th.start();
		new Thread(t).start();
		new Thread(t).start();
		new Thread(t).start();
		new Thread(t).start();
		new Thread(t).start();
    }
}
