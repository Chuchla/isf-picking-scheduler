package org.example;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;

public class Order implements Comparable<Order> {
    private final String orderId;
    private final BigDecimal orderValue;
    private final Duration pickingTime;
    private final LocalTime completeBy;
    private BigDecimal weight;
    private LocalTime pickingStartedTime;


    public Order(String orderId, BigDecimal orderValue, Duration pickingTime, LocalTime completeBy) {
        this.orderId = orderId;
        this.orderValue = orderValue;
        this.pickingTime = pickingTime;
        this.completeBy = completeBy;
    }
    @Override
    public String toString() {
        return "org.example.Order{" +
                "orderId='" + orderId + '\'' +
                ", orderValue=" + orderValue +
                ", pickingTime=" + pickingTime +
                ", completeBy=" + completeBy +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId) && Objects.equals(orderValue, order.orderValue) && Objects.equals(pickingTime, order.pickingTime) && Objects.equals(completeBy, order.completeBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, orderValue, pickingTime, completeBy);
    }


    /*//zrobić osobne komparatory i potem posortować po t
    @Override
    public int compareTo(Order otherOrder) {
        int result = completeBy.compareTo(otherOrder.completeBy);
        if (result == 0) {
            result = pickingTime.compareTo(otherOrder.pickingTime);
            if (result == 0) {
                result = orderId.compareTo(otherOrder.orderId);
            }
        }
        return result;
    }*/
    @Override
    public int compareTo(Order otherOrder) {
        int result = completeBy.compareTo(otherOrder.completeBy);
        if (result == 0) {
            result = pickingTime.compareTo(otherOrder.pickingTime);
            if (result == 0) {
                result = orderId.compareTo(otherOrder.orderId);
            }
        }
        return result;
    }

    /*@Override
    public int compareTo(org.example.Order otherOrder) {
        int result = -weight.compareTo(otherOrder.weight);
        if (result == 0 ){
            return orderId.compareTo(otherOrder.orderId);
        }
        return result;

    }*/

    public boolean wouldBeAfterCompleteBy(Picker picker) {
        return picker.getNextPickingTime().plus(getPickingTime()).isAfter(getCompleteBy());
    }


    public Duration getPickingTime() {
        return pickingTime;
    }

    public LocalTime getCompleteBy() {
        return completeBy;
    }

    public String getOrderId() {
        return orderId;
    }
    public void setPickingStartedTime(LocalTime pickingStartedTime) {
        this.pickingStartedTime = pickingStartedTime;
    }
    public LocalTime getPickingStartedTime() {
        return pickingStartedTime;
    }

    public void setWeight(Store store) {
        double deadline = completeBy.toSecondOfDay()-store.getPickingStartTime().toSecondOfDay();
        weight = BigDecimal.valueOf(pickingTime.toSeconds()/deadline);
    }

    public BigDecimal getWeight() {
        return weight;
    }
}
