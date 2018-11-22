package boot.mq;


import boot.listener.mq.MessageListener;
import boot.mq.processor.MessageProcessor;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
 
 
/**
 * Created by eggyer on 2017/3/25.
 */
@SpringBootConfiguration
public class RocketMQConsumerConfiguration {
    public static final Logger LOGGER = LoggerFactory.getLogger(RocketMQConsumerConfiguration.class);
    @Value("${rocketmq.consumer.namesrvAddr}")
    private String namesrvAddr;
    @Value("${rocketmq.consumer.groupName}")
    private String groupName;
    @Value("${rocketmq.consumer.topic}")
    private String topic;
    @Value("${rocketmq.consumer.groupSencondName}")
    private String groupSencondName;
    @Value("${rocketmq.consumer.sencond.topic}")
    private String sencondTopic;
    @Value("${rocketmq.consumer.consumeThreadMin}")
    private int consumeThreadMin;
    @Value("${rocketmq.consumer.consumeThreadMax}")
    private int consumeThreadMax;
 
    @Autowired
    @Qualifier("messageProcessorImpl")
    private MessageProcessor messageProcessor;
    
    @Autowired
    @Qualifier("sencondMessageProcessorImpl")
    private MessageProcessor sencondMessageProcessor;
    /**
     * 
     * @param namesrvAddr 消息地址
     * @param groupName 组名
     * @param messageProcessor 监听实现类
     * @param model 消息消费类型
     * @return
     * @throws RocketMQException
     */
    private DefaultMQPushConsumer innitConsumerBean(String groupName,MessageProcessor messageProcessor,MessageModel model) throws RocketMQException{
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.setConsumeThreadMin(consumeThreadMin);
        consumer.setConsumeThreadMax(consumeThreadMax);
        MessageListener messageListener = new MessageListener();
        messageListener.setMessageProcessor(messageProcessor);
        consumer.registerMessageListener(messageListener);
        consumer.setMessageModel(model); //广播模式
		return consumer;
    }
    
    @Bean("mqPushConsumer")
    public DefaultMQPushConsumer getFirstRocketMQConsumer() throws RocketMQException {
    	DefaultMQPushConsumer consumer = innitConsumerBean(groupName,messageProcessor,MessageModel.CLUSTERING);
        try {
            consumer.subscribe(topic,"*");
            consumer.start();
            LOGGER.info("consumer is start !!! groupName:{},topic:{},namesrvAddr:{}",groupName,topic,namesrvAddr);
        }catch (MQClientException e){
            LOGGER.error("consumer is start !!! groupName:{},topic:{},namesrvAddr:{}",groupName,topic,namesrvAddr,e);
            throw new RocketMQException(e);
        }
        return consumer;
    }
    
    @Bean("sencondMQPushConsumer")
    public DefaultMQPushConsumer getSencondRocketMQConsumer() throws RocketMQException {
    	DefaultMQPushConsumer consumer = innitConsumerBean(groupSencondName,sencondMessageProcessor,MessageModel.CLUSTERING);
        try {
            consumer.subscribe(sencondTopic,"*");
            consumer.start();
            LOGGER.info("consumer is start !!! groupName:{},topic:{},namesrvAddr:{}",groupSencondName,sencondTopic,namesrvAddr);
        }catch (MQClientException e){
            LOGGER.error("consumer is start !!! groupName:{},topic:{},namesrvAddr:{}",groupSencondName,sencondTopic,namesrvAddr,e);
            throw new RocketMQException(e);
        }
        return consumer;
    }
}
