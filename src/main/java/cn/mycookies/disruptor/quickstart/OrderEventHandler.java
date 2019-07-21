package cn.mycookies.disruptor.quickstart;


import com.lmax.disruptor.EventHandler;

/**
 * 3. 事件消费者
 *
 * @author Jann Lee
 * @date 2019-07-21 22:45
 **/
public class OrderEventHandler implements EventHandler<OrderEvent> {
    /**
     * 消费事件
     */
    @Override
    public void onEvent(OrderEvent orderEvent, long l, boolean b) throws Exception {
        System.out.println("订单id："+ orderEvent.getOrderId());
    }

}
