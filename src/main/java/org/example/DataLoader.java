package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {

    public List<Order> getOrders(String ordersPath, String storePath) {
        try (var ordersStream = new FileInputStream(ordersPath)) {
            try (var reader = new InputStreamReader(ordersStream)) {
                TypeToken<ArrayList<OrderJson>> listType = new TypeToken<>() {
                };
                List<OrderJson> ordersJson = new Gson().fromJson(reader, listType.getType());
                List<Order> orders = new ArrayList<>();
                Store store = getStore(storePath);
                for (OrderJson orderJson : ordersJson
                ) {
                    Order order = orderJson.toOrder();
                    order.setWeight(store);
                    orders.add(order);
                }
                return orders;
            }
        } catch (IOException e) {
            throw new RuntimeException("File loading error");
        }
    }

    public Store getStore(String storePath) {
        try (var storeStream = new FileInputStream(storePath)) {
            try (var reader = new InputStreamReader(storeStream)) {
                StoreJson storeJson = new Gson().fromJson(reader, StoreJson.class);
                return storeJson.toStore();
            }
        } catch (IOException e) {
            throw new RuntimeException("File loading error");
        }
    }
}
