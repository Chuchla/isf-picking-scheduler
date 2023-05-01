package org.example;

import java.util.*;

public class Scheduler {
    private final DataLoader dataLoader;

    public Scheduler(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    public List<Picker> createSchedule(String storePath, String ordersPath) {
        Store store = dataLoader.getStore(storePath);
        List<Order> orders = dataLoader.getOrders(ordersPath, storePath);
        PriorityQueue<Order> ordersQueue = new PriorityQueue<>(orders);
        List<Picker> pickers = store.getPickers();
        List<Picker> pickersFinished = new ArrayList<>();
        List<Order> ordersTrash = new ArrayList<>();
        while (!pickers.isEmpty() && !ordersQueue.isEmpty()) {
            Picker nextPicker = pickers.get(0);
            Order nextOrder = ordersQueue.peek();
            if (nextOrder.wouldBeAfterCompleteBy(nextPicker)) {
                ordersTrash.add(ordersQueue.poll());
            } else {
                if (nextPicker.isAfterWork(nextOrder)) {
                    pickersFinished.add(pickers.remove(0));
                } else {
                    nextOrder.setPickingStartedTime(nextPicker.getNextPickingTime());
                    nextPicker.assignOrder(ordersQueue.poll());
                    Collections.sort(pickers);
                }
            }
        }
        pickersFinished.addAll(pickers);
        Collections.sort(pickersFinished);
        return pickersFinished;
    }
    public void printSchedule(String storePath, String ordersPath){
        List<Picker> schedule = createSchedule(storePath, ordersPath);
        StringBuilder stringBuilder = new StringBuilder();
        for (Picker picker : schedule) {
            for (Order order : picker.getAssignedOrders()) {
                stringBuilder.append(picker.getPickerId() + " " + order.getOrderId() + " " + order.getPickingStartedTime() + "\n");
                //stringBuilder.append(order.getOrderId()+"\n");
            }
        }
        System.out.println(stringBuilder.toString());
    }



}
