package cn.mycookies.disruptor.high;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.List;
import java.util.concurrent.*;

/**
 * @author Jann Lee
 * @date 2019-07-23 22:51
 **/
public class Main {

    public static void main(String[] args) throws InterruptedException {
        // 用于提交任务的线程池
        ExecutorService es = Executors.newFixedThreadPool(10);

        // 1. 生产任务的工厂
        EventFactory<TradeEvent> tradeEventEventFactory = new TradeEventFactory();
        // 2. 执行任务的线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 100, 6000, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
        // 3. 生产者类型，single or multi
        ProducerType producerType = ProducerType.SINGLE;
        // 4. 等待策略
        WaitStrategy waitStrategy = new BlockingWaitStrategy();
        // 5. ringBuffer的size
        int ringBufferSize = 2 * 1024;

        Disruptor<TradeEvent> disruptor = new Disruptor<TradeEvent>(tradeEventEventFactory, ringBufferSize, threadPoolExecutor, producerType, waitStrategy);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        /**
         * 串行执行
         */
//        disruptor
//                .handleEventsWith(new TradeEventHandler())
//                .handleEventsWith(new TradeEventHandler2())
//                .handleEventsWith(new TradeEventHandler3())
//                .handleEventsWith(new TradeEventHandler4());
        /**
         * 并行执行
         * 2中写法
         */
//      1.  disruptor.handleEventsWith(new TradeEventHandler(), new TradeEventHandler2(), new TradeEventHandler3(), new TradeEventHandler4());
        disruptor.handleEventsWith(new TradeEventHandler());
        disruptor.handleEventsWith(new TradeEventHandler2());
        disruptor.handleEventsWith(new TradeEventHandler3());
        disruptor.handleEventsWith(new TradeEventHandler4());

        disruptor.start();
        es.submit(new TradeEventPublisher(disruptor, countDownLatch));

        countDownLatch.await();
        es.shutdown();
        disruptor.shutdown();

    }
}
