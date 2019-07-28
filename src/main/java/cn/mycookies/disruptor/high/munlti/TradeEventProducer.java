package cn.mycookies.disruptor.high.munlti;

import com.lmax.disruptor.RingBuffer;

/**
 * 生产者
 * @author Jann Lee
 * @date 2019-07-28 18:36
 **/
public class TradeEventProducer {

    private RingBuffer<TradeEvent> ringBuffer;

    public TradeEventProducer(RingBuffer<TradeEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void sendData(String id) {
        long sequence = ringBuffer.next();
        try {
            TradeEvent tradeEvent = ringBuffer.get(sequence);
            tradeEvent.setId(id);
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
