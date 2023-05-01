import org.example.DataLoader;
import org.example.Order;
import org.example.Picker;
import org.example.Scheduler;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CompleteByTest {
    @Test
    void testCompleteBy() {
        String storePath = "src/test/resources/self-test-data/complete-by/store.json";
        String ordersPath = "src/test/resources/self-test-data/complete-by/orders.json";
        DataLoader dataLoader = new DataLoader();
        Scheduler scheduler = new Scheduler(dataLoader);
        List<Picker> schedule = scheduler.createSchedule(storePath, ordersPath);

        List<Picker> pickerList = new ArrayList<>();
        Picker picker = new Picker(
                "P1",
                LocalTime.of(9, 0),
                LocalTime.of(10, 0));
        Order order = new Order("order-1",
                new BigDecimal(0.00),
                Duration.ofMinutes(20),
                LocalTime.of(9, 30));
        picker.assignOrder(order);
        pickerList.add(picker);

        assertEquals(pickerList,schedule);
    }
}
