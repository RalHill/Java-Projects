// File: Warehouse.java

import java.util.ArrayList;

public class Warehouse {

    private ArrayList<Freight> storedFreight;

    public Warehouse() {
        this.storedFreight = new ArrayList<>();
    }

    public void addFreight(Freight freight) {
        storedFreight.add(freight);
    }

    public void removeFreight(Freight freight) {
        storedFreight.remove(freight);
    }

    public ArrayList<Freight> getStoredFreight() {
        return storedFreight;
    }
}
