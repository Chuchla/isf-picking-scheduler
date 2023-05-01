import org.example.DataLoader;
import org.example.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProstyTest {
    @Test
    void masno() {
        String ordersPath = "src/test/resources/self-test-data/advanced-allocation/orders.json";
        String storePath = "src/test/resources/self-test-data/advanced-allocation/store.json";
        DataLoader dataLoader = new DataLoader();
        List<Order> orders = dataLoader.getOrders(ordersPath,storePath);
        Queue<Order> queue = new PriorityQueue<>(orders);
        ArrayList<String > ordersList = new ArrayList<>();
        while (!queue.isEmpty()) {
            ordersList.add(queue.poll().getOrderId());
        }
        assertEquals(
            List.of(
                "order-1",
                "order-2",
                "order-5",
                "order-7",
                "order-6",
                "order-3",
                "order-4"),
                ordersList
        );
    }
}
