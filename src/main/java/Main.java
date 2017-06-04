/**
 * * Basic Client for CRUD Operations on different noSQL Database Types
 * Hibernate supports ORM for the following noSQL types
 * - document: mongoDB
 * - graph: neo4j
 * - column: cassandra
 * - key-value: infinispan, ehcache
 * Created by ajo on 02.06.17.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.List;

import data.EntityCustomer;
import services.DBService;
import services.MultiDBService;

public class Main {

    private static DBService currentService;

    public static void main(String[] args) {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String dbConnection = "";
        String dbOperation = "";

        boolean loop = true;
        EntityCustomer currentEntity = null;

        System.out.println("Please select the Database");
        System.out.println("[mysql; mongodb; neo4j; cassandra; infinispan]");
        System.out.print("Selection: ");

        try {
            dbConnection = br.readLine();
            currentService = new MultiDBService(dbConnection);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (loop) {
            System.out.println("Please select the Operation");
            System.out.println("[c - Create; r - Read; u - Update; d - Delete]");
            System.out.print("Selection: ");

            try {
                dbOperation = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            switch (dbOperation) {
                // Create
                case "c":
                    System.out.println("Creating customer with current Date ( " + Calendar.getInstance().getTime() + ")");

                    EntityCustomer current = new EntityCustomer();
                    current.setBirthdate(Calendar.getInstance().getTime());
                    currentService.createCustomer(current);

                    System.out.println("CustomerEntity Created: " + current.toString() + "; Birthdate: " + current.getBirthdate());
                    break;
                // Read
                case "r":
                    System.out.println("Getting all Customers");
                    List<EntityCustomer> customers = currentService.getCustomers();
                    for (EntityCustomer entity : customers) {
                        System.out.println("CustomerEntity: " + entity.toString() + "; Birthdate: " + entity.getBirthdate());
                    }
                    break;
                // Update
                case "u":
                    currentEntity = readSelection();
                    currentService.updateCustomer(currentEntity);
                    System.out.println("updated " + currentEntity);
                    break;
                // Delete
                case "d":
                    currentEntity = readSelection();
                    currentService.deleteCustomer(currentEntity.getIdcustomer());
                    System.out.println("deleted " + currentEntity);
                    break;
                case "q":
                    loop = false;
                    break;
                default:
                    break;

            }
        }

        // Disconnect to prevent concurrent connections
        System.out.println("Disconnect");
        currentService.disconnect();

    }

    private static EntityCustomer readSelection() {
        int counter = 0;
        int customerNumber = 0;

        System.out.println("Select the customer:");
        List<EntityCustomer> deletableCustomers = currentService.getCustomers();
        for (EntityCustomer entity : deletableCustomers) {
            counter++;
            System.out.println("NR: " + counter + " CustomerEntity: " + entity.toString() + "; Birthdate: " + entity.getBirthdate());
        }

        System.out.print("Selection: ");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            customerNumber = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return deletableCustomers.get(customerNumber-1);
    }
}
