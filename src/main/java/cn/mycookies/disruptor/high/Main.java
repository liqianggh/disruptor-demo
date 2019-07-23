package cn.mycookies.disruptor.high;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Jann Lee
 * @date 2019-07-23 22:51
 **/
public class Main {

    public static void main(String[] args) {
        // 1. 生产任务的工厂
        EventFactory<TradeEvent> tradeEventEventFactory = new TradeEventFactory();
        // 2. 执行任务的线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 100, 6000, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
        // 3. 生产者类型，single or multi
        ProducerType producerType = ProducerType.SINGLE;
        // 4. 等待策略
        WaitStrategy waitStrategy = new BlockingWaitStrategy();

        Disruptor<TradeEvent> disruptor = new Disruptor<TradeEvent>(tradeEventEventFactory, threadPoolExecutor, producerType, waitStrategy);
    }
}
