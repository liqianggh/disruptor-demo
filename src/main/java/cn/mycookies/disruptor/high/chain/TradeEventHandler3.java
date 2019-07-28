package cn.mycookies.disruptor.high.chain;

import com.lmax.disruptor.EventHandler;

/**
 * @author Jann Lee
 * @date 2019-07-25 0:06
 */
public class TradeEventHandler3 implements EventHandler<TradeEvent>{

    @Override
    public void onEvent(TradeEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("Handler3 ....................");
    }
}
