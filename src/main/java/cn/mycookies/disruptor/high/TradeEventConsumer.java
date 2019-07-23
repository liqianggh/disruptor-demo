package cn.mycookies.disruptor.high;


import com.lmax.disruptor.EventHandler;

/**
 * @author Jann Lee
 * @date 2019-07-23 22:56
 **/
public class TradeEventConsumer implements EventHandler<TradeEvent> {
    @Override
    public void onEvent(TradeEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("消费事件：");
        System.out.println(event);
        System.out.println("-----------------------------------------");
    }
}
