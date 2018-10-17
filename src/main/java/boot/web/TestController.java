package boot.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import boot.shedule.model.CrawlerData;
import boot.shedule.service.master.CrawlerDataMasterService;
import boot.shedule.service.slave.CrawlerDataSlaveService;

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
    DefaultMQProducer defaultMQProducer;
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
}
