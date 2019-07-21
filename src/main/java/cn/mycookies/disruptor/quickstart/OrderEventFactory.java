package cn.mycookies.disruptor.quickstart;

import com.lmax.disruptor.EventFactory;

/**
 * 2.编写事件工厂
 *
 * @author Jann Lee
 * @date 2019-07-21 22:41
 **/
public class OrderEventFactory implements EventFactory<OrderEvent> {
    /**
     * 此方法目的就是返回空的event对象
     * @return
     */
    @Override
    public OrderEvent newInstance() {
        return new OrderEvent();
    }
}
