package boot.mq.processor.impl;

import boot.mq.processor.MessageProcessor;
import boot.shedule.model.CrawlerData;
import boot.shedule.service.master.CrawlerDataMasterService;

import com.alibaba.rocketmq.common.message.MessageExt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
 
/**
 * Created by eggyer on 2017/3/26.
 */
@Component
public class MessageProcessorImpl implements MessageProcessor {
	
    @Autowired
    CrawlerDataMasterService crawlerDataMasterService;
    @Value("${server.port}")
    String name;
    
    @Override
    public boolean handleMessage(MessageExt messageExt) {
    	CrawlerData record = new CrawlerData();
    	record.setName(name);
		crawlerDataMasterService.insert(record );
        System.out.println("receive : " + messageExt.toString());
        return true;
    }
}
