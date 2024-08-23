// File: Customer.java

public class Customer {

    private String customerCode;
    private String customerName;

    public Customer(String customerCode, String customerName) {
        this.customerCode = customerCode;
        this.customerName = customerName;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }
}
