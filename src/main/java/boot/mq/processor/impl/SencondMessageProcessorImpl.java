package boot.mq.processor.impl;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.rocketmq.common.message.MessageExt;

import boot.enums.CodeEnum;
import boot.mq.processor.MessageProcessor;
import boot.shedule.model.CrawlerData;
import boot.shedule.service.master.CrawlerDataMasterService;

@Component
public class SencondMessageProcessorImpl implements MessageProcessor{
	
	private  Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    CrawlerDataMasterService crawlerDataMasterService;
    @Value("${server.port}")
    String name;
    
	@Override
	public boolean handleMessage(MessageExt messageExt) {
    	CrawlerData record = new CrawlerData();
    	String content = null;
    	try {
			content = new String(messageExt.getBody(),CodeEnum.utf8.getValue());
		} catch (UnsupportedEncodingException e) {
			logger.error("消息内容编码错误 msgId:{}",messageExt.getMsgId());
		}
    	record.setName(content);
    	record.setCode(name);
    	record.setUrl(this.getClass().getName());
		crawlerDataMasterService.insert(record );
        System.out.println("receive : " + messageExt.toString());
        return true;
	}

}
