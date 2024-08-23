// File: ShippingRequest.java

import java.util.ArrayList;

public class ShippingRequest {

    private String shippingID;
    private Customer customer;
    private int sourceStation;
    private int destinationStation;
    private ArrayList<Freight> freightItems;

    public ShippingRequest(String shippingID, Customer customer, int sourceStation, int destinationStation) {
        this.shippingID = shippingID;
        this.customer = customer;
        this.sourceStation = sourceStation;
        this.destinationStation = destinationStation;
        this.freightItems = new ArrayList<>();
    }

    public String getShippingID() {
        return shippingID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getSourceStation() {
        return sourceStation;
    }

    public int getDestinationStation() {
        return destinationStation;
    }

    public void addFreightItem(Freight freight) {
        freightItems.add(freight);
    }

    public ArrayList<Freight> getFreightItems() {
        return freightItems;
    }
}
