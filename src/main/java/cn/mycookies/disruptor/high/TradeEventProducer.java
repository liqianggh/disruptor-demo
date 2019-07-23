package cn.mycookies.disruptor.high;

import com.lmax.disruptor.RingBuffer;
import org.springframework.beans.BeanUtils;

/**
 * 事件生产者
 *
 * @author Jann Lee
 * @date 2019-07-23 22:58
 **/
public class TradeEventProducer {

    private RingBuffer<TradeEvent> eventRingBuffer;

    public TradeEventProducer(RingBuffer<TradeEvent> eventRingBuffer) {
        this.eventRingBuffer = eventRingBuffer;
    }

    /**
     * 发布事件/生产消息
     * @param tradeEvent
     */
    public void publishEvent(TradeEvent tradeEvent) {
        // 获取可用序号
        long sequence = eventRingBuffer.next();
        // 获取空event
        TradeEvent trade = eventRingBuffer.get(sequence);
        // 空event赋值
        BeanUtils.copyProperties(tradeEvent, trade);
        eventRingBuffer.publish(sequence);
    }
}
