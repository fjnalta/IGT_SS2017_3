package services;

import data.Customer;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by ajo on 02.06.17.
 */
public class MultiDBService implements DBService {

    private static EntityManagerFactory factory;

    /**
     * Constructor initializes database Connection
     * @param database MSQ: MySQL; MDB: MongoDB; N4J: Neo4J; CAS: Cassandra; INF: Infinispan
     */
    public MultiDBService(String database) {
        factory = Persistence.createEntityManagerFactory(database);
    }

    public void createCustomer(Customer customer) {

    }

    public Customer getCustomer() {
        return null;
    }

    public void updateCustomer(Customer customer) {

    }

    public boolean removeCustomer(Long id) {
        return false;
    }

    public void disconnect() {
        factory.close();
    }

}
