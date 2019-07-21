package cn.mycookies.disruptor.quickstart;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @author Jann Lee
 * @date 2019-07-21 23:12
 **/
public class OrderEventProducer {

    private RingBuffer<OrderEvent> ringBuffer;

    public OrderEventProducer(RingBuffer<OrderEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void sendData(ByteBuffer data) {
        /**
         * 1. 生产者发送消息时候，首先需要从ringBuffer里面获取一个可用序号
         */
        long sequence = ringBuffer.next();
        try {
            /**
             * 2. 根据序号找到具体的“OrderEvent元素”
             */
            OrderEvent orderEvent = ringBuffer.get(sequence);
            /**
             * 3. 进行实际赋值处理
             */
            orderEvent.setOrderId(data.getInt(0));
            /**
             * 4. 发布事件
             */
        } finally {
            ringBuffer.publish(sequence);
        }

    }

}
