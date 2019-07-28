package cn.mycookies.disruptor.high.munlti;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

/**
 * @author Jann Lee
 * @date 2019-07-25 23:36
 **/
public class Main {
    public static void main(String[] args) throws InterruptedException {
        // 1. 创建一个ringBuffer
        RingBuffer<TradeEvent> ringBuffer = RingBuffer
                .create(ProducerType.MULTI,
                        () -> new TradeEvent(),
                        1024*1024,
                        new YieldingWaitStrategy());
        // 2. 创建一个屏障
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();

        // 3. 构建多消费者
        Consumer[] consumers = new Consumer[10];
        for(int i = 0; i < consumers.length ; i++ ){
        consumers[i] = new Consumer("Consumer_"+i);
        }

        // 4. 构建多消费者工工作池
        WorkerPool<TradeEvent> workerPool = new WorkerPool<>(
                ringBuffer,
                sequenceBarrier,
                new EventExceptionHandler(),
                consumers);
        // 5. 设置多个消费者的sequence，用于统计消费进度，并且设置到ringbuffer中
        ringBuffer.addGatingSequences(workerPool.getWorkerSequences());

        // 6. 启动workPool
        workerPool.start(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));

        // 多个生产者投递消息
        CountDownLatch latch = new CountDownLatch(1);
        for(int i = 0; i < 100 ; i++ ){
            TradeEventProducer producer = new TradeEventProducer(ringBuffer);
            new Thread( () -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {

                }
                for (int j = 0; j < 100; j++) {
                    producer.sendData(UUID.randomUUID().toString());
                }

            }).start();
        }
        Thread.sleep(2000);
        System.out.println("现场创建完毕，开始生产数据");
        latch.countDown();
        Thread.sleep(20000);
        System.out.println("第一个消费者处理任务总数" + consumers[0].getCount().toString());
    }

    static class EventExceptionHandler implements ExceptionHandler {

        @Override
        public void handleEventException(Throwable ex, long sequence, Object event) {
            System.out.println("消费中异常");
        }

        @Override
        public void handleOnStartException(Throwable ex) {
            System.out.println("启动异常");

        }

        @Override
        public void handleOnShutdownException(Throwable ex) {
            System.out.println("关闭时异常");
        }
    }
}
