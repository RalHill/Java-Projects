// File: Freight.java

public class Freight {

    private String freightID;
    private String itemDescription;
    private int weight;
    private String status;

    public Freight(String freightID, String itemDescription, int weight, String status) {
        this.freightID = freightID;
        this.itemDescription = itemDescription;
        this.weight = weight;
        this.status = status;
    }

    public String getFreightID() {
        return freightID;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public int getWeight() {
        return weight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
