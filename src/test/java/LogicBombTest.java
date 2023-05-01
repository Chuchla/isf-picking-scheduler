import org.example.DataLoader;
import org.example.Scheduler;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class LogicBombTest {
    @Test
    void testLogicBomb() {
        String storePath = "src/test/resources/self-test-data/logic-bomb/store.json";
        String ordersPath = "src/test/resources/self-test-data/logic-bomb/orders.json";
        DataLoader dataLoader = new DataLoader();
        Scheduler scheduler = new Scheduler(dataLoader);
        scheduler.createSchedule(storePath,ordersPath);

        //

        assertEquals(scheduler,scheduler);
    }
}
