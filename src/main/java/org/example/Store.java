package org.example;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Store {
    private final List<String> pickers;
    private final LocalTime pickingStartTime;
    private final LocalTime pickingEndTime;

    public Store(List<String> pickers, LocalTime pickingStartTime, LocalTime pickingEndTime) {
        this.pickers = pickers;
        this.pickingStartTime = pickingStartTime;
        this.pickingEndTime = pickingEndTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return Objects.equals(pickers, store.pickers) && Objects.equals(pickingStartTime, store.pickingStartTime) && Objects.equals(pickingEndTime, store.pickingEndTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pickers, pickingStartTime, pickingEndTime);
    }

    @Override
    public String toString() {
        return "org.example.Store{" +
                "pickers=" + pickers +
                ", pickingStartTime=" + pickingStartTime +
                ", pickingEndTime=" + pickingEndTime +
                '}';
    }

    List<Picker> getPickers() {
        List<Picker> pickerList = new ArrayList<>();
        for (String pickerId : pickers) {
            Picker picker = new Picker(pickerId, pickingStartTime, pickingEndTime);
            pickerList.add(picker);
        }
        return pickerList;
    }

    public LocalTime getPickingStartTime() {
        return pickingStartTime;
    }
}
