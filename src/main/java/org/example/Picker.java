package org.example;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Picker implements Comparable {
    private final String pickerId;
    private final LocalTime pickingStartTime;
    private final LocalTime pickingEndTime;
    private final List<Order> assignedOrders;
    private LocalTime nextPickingTime;

    public Picker(String pickerId, LocalTime pickingStartTime, LocalTime pickingEndTime) {
        this.pickerId = pickerId;
        this.pickingStartTime = pickingStartTime;
        this.pickingEndTime = pickingEndTime;
        this.assignedOrders = new ArrayList<>();
        this.nextPickingTime = pickingStartTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Picker picker = (Picker) o;
        return Objects.equals(pickerId, picker.pickerId) && Objects.equals(pickingStartTime, picker.pickingStartTime) && Objects.equals(pickingEndTime, picker.pickingEndTime) && Objects.equals(nextPickingTime, picker.nextPickingTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pickerId, pickingStartTime, pickingEndTime, nextPickingTime);
    }


    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Picker otherPicker)) {
            throw new IllegalStateException();
        }
        int result = nextPickingTime.compareTo(otherPicker.nextPickingTime);
        if (result == 0) {
            result = pickerId.compareTo(otherPicker.getPickerId());
            return result;
        }
        return result;
    }

    boolean isAfterWork(Order order) {
        return !nextPickingTime.plus(order.getPickingTime()).isBefore(pickingEndTime);
    }
    public void assignOrder(Order order){
        assignedOrders.add(order);
        nextPickingTime = nextPickingTime.plus(order.getPickingTime());
    }

    @Override
    public String toString() {
        return "org.example.Picker{" +
                "pickerId='" + pickerId + '\'' +
                ", pickingStartTime=" + pickingStartTime +
                ", pickingEndTime=" + pickingEndTime +
                ", nextPickingTime=" + nextPickingTime +
                '}';
    }

    public LocalTime getNextPickingTime() {
        return nextPickingTime;
    }

    public String getPickerId() {
        return pickerId;
    }

    public List<Order> getAssignedOrders() {
        return assignedOrders;
    }
}
