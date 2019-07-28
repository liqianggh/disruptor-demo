package cn.mycookies.disruptor.high.chain;


import com.lmax.disruptor.EventHandler;

/**
 * @author Jann Lee
 * @date 2019-07-23 22:56
 **/
public class TradeEventHandler implements EventHandler<TradeEvent> {
    @Override
    public void onEvent(TradeEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("Handler1-----------------------------------------");
    }
}
