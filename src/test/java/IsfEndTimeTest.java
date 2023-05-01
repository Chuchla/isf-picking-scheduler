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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IsfEndTimeTest {
    @Test
    void testIsfEndTime() {
        String storePath = "src/test/resources/self-test-data/isf-end-time/store.json";
        String ordersPath = "src/test/resources/self-test-data/isf-end-time/orders.json";
        DataLoader dataLoader = new DataLoader();
        Scheduler scheduler = new Scheduler(dataLoader);
        List<Picker> schedule = scheduler.createSchedule(storePath, ordersPath);

        List<Picker> pickerList = new ArrayList<>();
        Picker picker = new Picker(
                "P1",
                LocalTime.of(9, 0),
                LocalTime.of(9, 30));
        Order order = new Order(
                "order-1",
                new BigDecimal(0.00),
                Duration.ofMinutes(20),
                LocalTime.of(9, 30));
        picker.assignOrder(order);
        pickerList.add(picker);

        assertEquals(pickerList, schedule);
    }
}
