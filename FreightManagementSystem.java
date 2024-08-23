// File: FreightManagementSystem.java

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FreightManagementSystem {

    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<ShippingRequest> shippingRequests = new ArrayList<>();
    private static ArrayList<Station> stations = new ArrayList<>();
    private static int nextInvoiceNumber = 9001;
    private static Boxcar boxcar = new Boxcar("B01");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeStations();
        char mainMenuSelection;

        do {
            System.out.println("Main Menu");
            System.out.println("-------------------------------");
            System.out.println("* A - Freight Operations");
            System.out.println("* B - Train Operations");
            System.out.println("* Z - Exit Program");
            System.out.println("-------------------------------");
            System.out.print("Enter your choice: ");
            mainMenuSelection = scanner.next().toUpperCase().charAt(0);

            switch (mainMenuSelection) {
                case 'A':
                    freightOperationsMenu(scanner);
                    break;
                case 'B':
                    trainOperationsMenu(scanner);
                    break;
                case 'Z':
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid selection. Try again.");
            }
        } while (mainMenuSelection != 'Z');

        scanner.close();
    }

    private static void freightOperationsMenu(Scanner scanner) {
        int subMenuSelection = 0;  // Initialize variable

        do {
            System.out.println("Submenu A - Freight Operations");
            System.out.println("-------------------------------------------");
            System.out.println("* 1 – Add Customer");
            System.out.println("* 2 – Display Customers");
            System.out.println("* 3 – Create Shipping Requests");
            System.out.println("* 4 – Display All Shipping Requests");
            System.out.println("* 5 – Process Freight");
            System.out.println("* 6 – Display Train Manifest");
            System.out.println("* 9 – Return to Main Menu");
            System.out.println("-------------------------------------------");
            System.out.print("Enter your choice: ");
            try {
                subMenuSelection = scanner.nextInt();
                scanner.nextLine(); // Clear the buffer
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // clear invalid input
                continue;
            }

            switch (subMenuSelection) {
                case 1:
                    addCustomer(scanner);
                    break;
                case 2:
                    displayCustomers();
                    break;
                case 3:
                    createShippingRequest(scanner);
                    break;
                case 4:
                    displayAllShippingRequests();
                    break;
                case 5:
                    processFreight();
                    break;
                case 6:
                    displayTrainManifest();
                    break;
                case 9:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid selection. Try again.");
            }
        } while (subMenuSelection != 9);
    }

    private static void trainOperationsMenu(Scanner scanner) {
        int subMenuSelection = 0;  // Initialize variable

        do {
            System.out.println("Submenu B - Train Operations");
            System.out.println("-----------------------------------------");
            System.out.println("* 1 - Train Operations (Not Operational)");
            System.out.println("* 9 - Return to Main Menu");
            System.out.println("-----------------------------------------");
            System.out.print("Enter your choice: ");
            try {
                subMenuSelection = scanner.nextInt();
                scanner.nextLine(); // Clear the buffer
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // clear invalid input
                continue;
            }

            switch (subMenuSelection) {
                case 1:
                    System.out.println("Train Operations not operational yet.");
                    break;
                case 9:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid selection. Try again.");
            }
        } while (subMenuSelection != 9);
    }

    private static void addCustomer(Scanner scanner) {
        System.out.print("Enter Customer Code (e.g., C01): ");
        String customerCode = scanner.next();
        System.out.print("Enter Customer Name: ");
        scanner.nextLine(); // Consume newline
        String customerName = scanner.nextLine();
        customers.add(new Customer(customerCode, customerName));
        System.out.println("Customer added successfully.");
    }

    private static void displayCustomers() {
        System.out.println("-------------------------------");
        System.out.println("Customer Code    Customer Name");
        System.out.println("-------------------------------");
        for (Customer customer : customers) {
            System.out.printf("%-15s%s\n", customer.getCustomerCode(), customer.getCustomerName());
        }
        System.out.println("-------------------------------");
    }

    private static void createShippingRequest(Scanner scanner) {
        System.out.print("Enter Customer Code: ");
        String customerCode = scanner.next();
        Customer customer = findCustomer(customerCode);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        int sourceStation;
        int destinationStation;
        try {
            System.out.print("Enter Source Station Code (1-4): ");
            sourceStation = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter Destination Station Code (1-4): ");
            destinationStation = scanner.nextInt();
            scanner.nextLine(); // Consume newline
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid station code.");
            scanner.next(); // clear invalid input
            return;
        }

        String shippingID = customer.getCustomerCode() + "-" + nextInvoiceNumber++ + "-" + sourceStation + destinationStation;

        ShippingRequest shippingRequest = new ShippingRequest(shippingID, customer, sourceStation, destinationStation);
        shippingRequests.add(shippingRequest);

        boolean addMoreFreight = false;  // Initialize the variable
        int freightCount = 1;
        do {
            System.out.print("Enter Freight Item Description: ");
            String itemDescription = scanner.nextLine();
            int weight;
            try {
                System.out.print("Enter Freight Item Weight: ");
                weight = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid weight.");
                scanner.next(); // clear invalid input
                continue;
            }
            String freightID = shippingID + "-" + freightCount;
            Freight freight = new Freight(freightID, itemDescription, weight, "S0" + sourceStation);
            shippingRequest.addFreightItem(freight);
            stations.get(sourceStation - 1).getWarehouse().addFreight(freight);

            System.out.print("Add another freight item? (true/false): ");
            try {
                addMoreFreight = scanner.nextBoolean();
                scanner.nextLine(); // Consume newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter true or false.");
                scanner.next(); // clear invalid input
                addMoreFreight = false; // assuming no more freight to add if input is invalid
            }
            freightCount++;
        } while (addMoreFreight && freightCount <= 9);

        System.out.println("Shipping Request created successfully.");
    }

    private static Customer findCustomer(String customerCode) {
        for (Customer customer : customers) {
            if (customer.getCustomerCode().equalsIgnoreCase(customerCode)) {
                return customer;
            }
        }
        return null;
    }

    private static void displayAllShippingRequests() {
        System.out.println("----------------------------------------------------");
        System.out.println("Shipping ID      Items         Weight(kg) Status");
        System.out.println("----------------------------------------------------");
        for (ShippingRequest request : shippingRequests) {
            for (Freight freight : request.getFreightItems()) {
                System.out.printf("%-15s%-13s%-11d%s\n", freight.getFreightID(), freight.getItemDescription(), freight.getWeight(), freight.getStatus());
            }
        }
        System.out.println("----------------------------------------------------");
    }

    private static void processFreight() {
        System.out.println("Processing freight...");
        for (ShippingRequest request : shippingRequests) {
            for (Freight freight : request.getFreightItems()) {
                if (freight.getStatus().startsWith("S01")) {
                    boxcar.loadFreight(freight);
                    freight.setStatus("B01"); // Update status to indicate it's in the boxcar
                    stations.get(0).getWarehouse().removeFreight(freight);
                }
            }
        }
        System.out.println("Freight processing complete. All items are loaded into boxcar B01.");
    }

    private static void displayTrainManifest() {
        System.out.println("------------------------------------------------------------");
        System.out.println("Car ID  Type      Shipping ID         Items    Weight(kg)");
        System.out.println("------------------------------------------------------------");
        System.out.println(boxcar.getManifest());
        System.out.println("------------------------------------------------------------");
    }

    private static void initializeStations() {
        for (int i = 1; i <= 4; i++) {
            stations.add(new Station(i));
        }
    }
}
