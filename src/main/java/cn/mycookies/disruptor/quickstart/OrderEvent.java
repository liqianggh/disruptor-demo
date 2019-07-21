package cn.mycookies.disruptor.quickstart;

/**
 * 1. 编写事件类
 * @author Jann Lee
 * @date 2019-07-21 22:40
 **/
public class OrderEvent {

    private int orderId;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderEvent{" + "orderId=" + orderId + '}';
    }
}
