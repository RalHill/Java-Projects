// File: Boxcar.java

import java.util.ArrayList;

public class Boxcar {

    private String carID;
    private ArrayList<Freight> freightItems;

    public Boxcar(String carID) {
        this.carID = carID;
        this.freightItems = new ArrayList<>();
    }

    public void loadFreight(Freight freight) {
        freightItems.add(freight);
    }

    public String getManifest() {
        StringBuilder manifest = new StringBuilder(carID + "     Box Car\n");
        int totalWeight = 0;
        for (Freight freight : freightItems) {
            manifest.append("           ").append(freight.getFreightID()).append("     ").append(freight.getItemDescription()).append("  ").append(freight.getWeight()).append("\n");
            totalWeight += freight.getWeight();
        }
        manifest.append("           Total Weight: ").append(totalWeight).append(" kg");
        return manifest.toString();
    }
}
