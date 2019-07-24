package cn.mycookies.disruptor.high;

import javafx.event.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Event对象
 *
 * @author Jann Lee
 * @date 2019-07-23 22:52
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeEvent {

    private String id;

    private String name;

    private double price;

    private AtomicInteger count;

}
