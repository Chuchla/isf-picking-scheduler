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

public class AdvancedAllocationTest {
    @Test
    void testAdvancedAllocation() {
        String storePath = "src/test/resources/self-test-data/advanced-allocation/store.json";
        String ordersPath = "src/test/resources/self-test-data/advanced-allocation/orders.json";
        DataLoader dataLoader = new DataLoader();
        Scheduler scheduler = new Scheduler(dataLoader);
        List<Picker> schedule = scheduler.createSchedule(storePath, ordersPath);

        List<Picker> pickerList = new ArrayList<>();
        Picker picker1 = new Picker(
                "P1",
                LocalTime.of(9, 0),
                LocalTime.of(11, 0));
        Picker picker2 = new Picker(
                "P2",
                LocalTime.of(9, 0),
                LocalTime.of(11, 0));

        Order order1 = new Order("order-1",
                new BigDecimal(0.00),
                Duration.ofMinutes(15),
                LocalTime.of(9, 15));

        Order order2 = new Order("order-2",
                new BigDecimal(0.00),
                Duration.ofMinutes(30),
                LocalTime.of(9, 30));

        Order order3 = new Order("order-3",
                new BigDecimal(0.00),
                Duration.ofMinutes(15),
                LocalTime.of(10, 0));

        Order order4 = new Order("order-4",
                new BigDecimal(0.00),
                Duration.ofMinutes(15),
                LocalTime.of(10, 0));

        Order order5 = new Order("order-5",
                new BigDecimal(0.00),
                Duration.ofMinutes(60),
                LocalTime.of(10, 15));

        Order order6 = new Order("order-6",
                new BigDecimal(0.00),
                Duration.ofMinutes(30),
                LocalTime.of(10, 45));
        Order order7 = new Order("order-7",
                new BigDecimal(0.00),
                Duration.ofMinutes(45),
                LocalTime.of(11, 0));

        picker1.assignOrder(order1);
        picker1.assignOrder(order5);
        picker1.assignOrder(order7);
        picker2.assignOrder(order2);
        picker2.assignOrder(order3);
        picker2.assignOrder(order4);
        picker2.assignOrder(order6);
        pickerList.add(picker1);
        pickerList.add(picker2);

        assertEquals(pickerList, schedule);
    }
}
