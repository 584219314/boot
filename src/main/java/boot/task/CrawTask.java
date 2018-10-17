package boot.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import boot.RedisConfig;
import boot.util.RedisUtil;

@Component
public class CrawTask {
/*    @Autowired
    private RedisUtil redisUtil;
    @Scheduled(fixedRate = 3000)
    public void process(){
		redisUtil.set("lsz", "lsz", 1120);
		redisUtil.set("lsz1", "lsz1", 2120);
		redisUtil.set("lsz2", "lsz2", 3120);
		redisUtil.set("lsz3", "lsz3", 4120);
    }*/
}
