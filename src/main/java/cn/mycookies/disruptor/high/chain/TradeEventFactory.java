package cn.mycookies.disruptor.high.chain;

import com.lmax.disruptor.EventFactory;

/**
 * 事件工厂
 *
 * @author Jann Lee
 * @date 2019-07-23 22:55
 **/
public class TradeEventFactory implements EventFactory<TradeEvent> {
    @Override
    public TradeEvent newInstance() {
        return new TradeEvent();
    }
}
