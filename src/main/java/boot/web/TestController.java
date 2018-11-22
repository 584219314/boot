package boot.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import boot.aspect.RedisLock;
import boot.enums.CrawlerEnum;
import boot.factory.service.CrawlerService;
import boot.shedule.model.CrawlerData;
import boot.shedule.model.req.MainReq;
import boot.shedule.service.master.CrawlerDataMasterService;
import boot.shedule.service.slave.CrawlerDataSlaveService;
import boot.util.RedisUtil;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;


@Controller
public class TestController {
	private final Logger logger = LoggerFactory.getLogger(TestController.class);
    @Autowired
    CrawlerDataMasterService crawlerDataMasterService;
    @Autowired
    CrawlerDataSlaveService crawlerDataSlaveService;
    @Autowired
    CrawlerService crawlerService;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    private DefaultMQProducer defaultMQProducer;
    /**
     * 测试 JSON 接口；
     *
     * @param name 名字；
     * @return
     */
    @ResponseBody
    @RequestMapping("/test/{name}/{id}")
    public JSONObject testJson(@PathVariable("name") String name,@PathVariable("id") String cId) {
        JSONObject jsonObject = new JSONObject();
        CrawlerData record = new CrawlerData();
        record.setName(name);
        int id = crawlerDataMasterService.insert(record);
        record = crawlerDataSlaveService.selectByPrimaryKey(Long.valueOf(id));
        if(record == null){
        	record = crawlerDataSlaveService.selectByPrimaryKey(Long.valueOf(cId));
        }
        jsonObject.put("str", record.getName());
        return jsonObject;
    }
    
    @ResponseBody
    @RequestMapping("/send/{msg}")
    public JSONObject mqSend(@PathVariable("msg") String msg) {
        JSONObject jsonObject = new JSONObject();
        logger.info("开始发送消息："+msg);
        Message sendMsg = new Message("DemoTopic","DemoTag",msg.getBytes());
        //默认3秒超时
        SendResult sendResult = null;
		try {
			sendResult = defaultMQProducer.send(sendMsg);
	        logger.info("消息发送响应信息："+sendResult.toString());
	        jsonObject.put("str", sendResult.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("消息发送失败");
			jsonObject.put("str", "失败");
		}
        return jsonObject;
    }
    
    @ResponseBody
    @RequestMapping("/gsny")
    public JSONObject gsny() {
    	JSONObject jsn = new JSONObject();
    	String val = crawlerDataMasterService.testAspect();
    	jsn.put("result", val);
        return jsn;
    }
    
    @ResponseBody
    @RequestMapping("/redis")
    public JSONObject redis() {
    	JSONObject jsn = new JSONObject();
    	redisUtil.set("lsz", "lsz1", 10000);
    	jsn.put("result", "1");
        return jsn;
    }
    
    @ResponseBody
    @RequestMapping("/mq/{topic}/{content}")
    public JSONObject mq(@PathVariable("content")String content,@PathVariable("topic")String topic) {
    	Message msg = new Message(topic,// topic
                "",// tag
                String.valueOf(System.currentTimeMillis()),//key用于标识业务的唯一性
                content.getBytes()// body 二进制字节数组
        );

    	try {
			defaultMQProducer.send(msg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	JSONObject jsn = new JSONObject();
    	redisUtil.set("lsz", "lsz1", 10000);
    	jsn.put("result", "1");
        return jsn;
    }
}
