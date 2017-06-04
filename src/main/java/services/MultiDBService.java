package services;

import data.CustomerEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by ajo on 02.06.17.
 * This Class implements the Database Service. It can be used with an OR-Mapper
 * to handle different types of SQL and noSQL databases.
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

    /**
     * Database Interface which creates a new Customer
     * @param customer
     */
    public void createCustomer(CustomerEntity customer) {
        EntityManager entityManager = factory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(customer);

        entityManager.flush();
        transaction.commit();
        entityManager.close();
    }

    /**
     * Database Interface which gets all Customer Entries
     * @returns all CustomerEntities
     */
    public List<CustomerEntity> getCustomers() {
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        List<CustomerEntity> result = entityManager
                .createQuery("SELECT c FROM CustomerEntity c", CustomerEntity.class)
                .getResultList();

        transaction.commit();
        entityManager.close();

        return result;
    }

    /**
     * Database Interface which updates a Customer
     * @param customer the updated CustomerEntity
     */
    public void updateCustomer(CustomerEntity customer) {
    }

    /**
     * Database Interface which deletes a Customer
     * @param id
     * @returns true if the delete was successful
     */
    public boolean removeCustomer(Long id) {
        return false;
    }

    public void disconnect() {
        factory.close();
    }

}
