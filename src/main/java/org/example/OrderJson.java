package org.example;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

public class OrderJson {
    private final String orderId;
    private final String orderValue;
    private final String pickingTime;
    private final String completeBy;

    public OrderJson(String orderId, String orderValue, String pickingTime, String completeBy) {
        this.orderId = orderId;
        this.orderValue = orderValue;
        this.pickingTime = pickingTime;
        this.completeBy = completeBy;
    }

    Order toOrder(){
        BigDecimal orderValue = new BigDecimal(this.orderValue);
        Duration duration = Duration.parse(this.pickingTime);
        LocalTime completeBy = LocalTime.parse(this.completeBy);
        return new Order(orderId, orderValue, duration, completeBy);
    }
}
