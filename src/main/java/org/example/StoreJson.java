package org.example;

import java.time.LocalTime;
import java.util.List;

public class StoreJson {
    private final List<String> pickers;
    private final String pickingStartTime;
    private final String pickingEndTime;

    public StoreJson(List<String> pickers, String pickingStartTime, String pickingEndTime) {
        this.pickers = pickers;
        this.pickingStartTime = pickingStartTime;
        this.pickingEndTime = pickingEndTime;
    }
    Store toStore(){
        LocalTime pickingStartTime = LocalTime.parse(this.pickingStartTime);
        LocalTime pickingEndTime = LocalTime.parse(this.pickingEndTime);
        return new Store(pickers, pickingStartTime, pickingEndTime);
    }
}
