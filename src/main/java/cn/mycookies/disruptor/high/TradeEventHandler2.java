package cn.mycookies.disruptor.high;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * @author Jann Lee
 * @date 2019-07-25 0:06
 */
public class TradeEventHandler2 implements EventHandler<TradeEvent>, WorkHandler<TradeEvent> {

    @Override
    public void onEvent(TradeEvent event) throws Exception {
        System.out.println("Handler2................");

    }

    @Override
    public void onEvent(TradeEvent event, long sequence, boolean endOfBatch) throws Exception {

        this.onEvent(event);

    }
}
