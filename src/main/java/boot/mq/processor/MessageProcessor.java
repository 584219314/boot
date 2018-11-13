package boot.mq.processor;


import com.alibaba.rocketmq.common.message.MessageExt;
 
/**
 * Created by eggyer on 2017/3/25.
 */
public interface MessageProcessor {
    /**
     * 处理消息的接口
     * @param messageExt
     * @return
     */
    public boolean handleMessage(MessageExt messageExt);
}
