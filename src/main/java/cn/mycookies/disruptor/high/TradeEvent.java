package cn.mycookies.disruptor.high;

import javafx.event.Event;
import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Event对象
 *
 * @author Jann Lee
 * @date 2019-07-23 22:52
 **/
@Data
public class TradeEvent extends Event {

    private String id;

    private String name;

    private double price;

    private AtomicInteger count;

}
