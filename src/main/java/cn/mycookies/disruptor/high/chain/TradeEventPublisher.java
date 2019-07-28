package cn.mycookies.disruptor.high.chain;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.dsl.Disruptor;
import org.springframework.beans.BeanUtils;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * 事件生产者
 *
 * @author Jann Lee
 * @date 2019-07-23 22:58
 **/
public class TradeEventPublisher implements Runnable{

    private Disruptor<TradeEvent> disruptor;
    private CountDownLatch countDownLatch;

    public TradeEventPublisher(Disruptor<TradeEvent> disruptor, CountDownLatch countDownLatch) {
        this.disruptor = disruptor;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        for(int i = 0; i< 10; i++){
            TradeEvent tradeEvent = new TradeEvent();
            tradeEvent.setId(UUID.randomUUID().toString());
            tradeEvent.setName("hello"+ i);
            tradeEvent.setPrice(i);
            disruptor.publishEvent(new TradeEventTranslator(tradeEvent));
        }
        countDownLatch.countDown();

    }
}

class TradeEventTranslator implements EventTranslator<TradeEvent> {
    private TradeEvent tradeEvent;

    public TradeEventTranslator(TradeEvent tradeEvent) {
        this.tradeEvent = tradeEvent;
    }

    @Override
    public void translateTo(TradeEvent event, long sequence) {
        BeanUtils.copyProperties(tradeEvent, event);
    }
}