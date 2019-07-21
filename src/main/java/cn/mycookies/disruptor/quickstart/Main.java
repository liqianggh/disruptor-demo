package cn.mycookies.disruptor.quickstart;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.*;

/**
 * @author Jann Lee
 * @date 2019-07-21 22:49
 **/
public class Main {
    public static void main(String[] args) {
        // 1. 实例化disruptor对象
        OrderEventFactory orderEventFactory = new OrderEventFactory();
        int RING_BUFFER_SIZE = 1024*1024;
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 20, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
        WaitStrategy waitStrategy = new BlockingWaitStrategy();
        /**
         * 一、参数准备工作
         * 1. eventFactory: 事件公共场对象
         * 2. 容器的长度
         * 3. ringBufferSize: 容器的大小
         * 4. executor: 线程池
         * 5. waitStrategy: 等待策略
         */
        Disruptor<OrderEvent> disruptor = new Disruptor<OrderEvent>(orderEventFactory, RING_BUFFER_SIZE, threadPool, ProducerType.SINGLE, waitStrategy);

        /**
         * 二、添加消费者的监听
         */
        disruptor.handleEventsWith(new OrderEventHandler());

        /**
         * 三、启动disruptor
         */
        disruptor.start();

        /**
         * 四、获取实际存储数据的容器：RingBuffer
         */
        RingBuffer<OrderEvent> ringBuffer = disruptor.getRingBuffer();
        OrderEventProducer producer = new OrderEventProducer(ringBuffer);
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for (int i = 0; i < 100; i++) {
            byteBuffer.putInt(0, i);
            producer.sendData(byteBuffer);
        }
        /**
         *
         * 五、释放资源
         */
        disruptor.shutdown();
        threadPool.shutdown();
    }
}
