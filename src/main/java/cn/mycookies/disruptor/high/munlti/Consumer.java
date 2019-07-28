package cn.mycookies.disruptor.high.munlti;

import com.lmax.disruptor.WorkHandler;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Jann Lee
 * @date 2019-07-25 23:46
 **/
public class Consumer implements WorkHandler<TradeEvent> {
    private String consumerId;

    private AtomicInteger count = new AtomicInteger(0);

    private Random random = new Random();
    public Consumer(String consumerId) {
        this.consumerId = consumerId;
    }

    @Override
    public void onEvent(TradeEvent event) throws Exception {
        System.out.println("消费者:"+consumerId +"正在消费"+event.getName()+" "+event.getCount());
        count.addAndGet(1);
        Thread.sleep(random.nextInt(10));
    }

    public AtomicInteger getCount() {
        return count;
    }
}
