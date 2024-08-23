// File: Station.java

public class Station {

    private int stationID;
    private Warehouse warehouse;

    public Station(int stationID) {
        this.stationID = stationID;
        this.warehouse = new Warehouse();
    }

    public int getStationID() {
        return stationID;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }
}
