import org.example.DataLoader;
import org.example.Order;
import org.example.Store;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataLoaderTest {

    @Test
    void testGetOrders() {
        String ordersPath = "src/test/resources/self-test-data/advanced-allocation/orders.json";
        String storePath = "src/test/resources/self-test-data/advanced-allocation/store.json";
        DataLoader dataLoader = new DataLoader();
        List<Order> orders = dataLoader.getOrders(ordersPath,storePath);

        Order firstExpectedOrder = new Order(
                "order-1",
                new BigDecimal("0.00"),
                Duration.ofMinutes(15),
                LocalTime.of(9,15)
        );

        assertEquals(firstExpectedOrder, orders.get(0));
    }

    @Test
    void testGetStore() {
        String fileName = "src/test/resources/self-test-data/advanced-allocation/store.json";
        DataLoader dataLoader = new DataLoader();
        Store store = dataLoader.getStore(fileName);

        Store expectedStore = new Store(
                List.of("P1", "P2"),
                LocalTime.of(9,0),
                LocalTime.of(11,0)
        );

        assertEquals(expectedStore, store);
    }
}