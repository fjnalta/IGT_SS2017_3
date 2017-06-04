package services;

import data.CustomerEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Calendar;
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
    public CustomerEntity updateCustomer(CustomerEntity customer) {
        EntityManager entityManager = factory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        CustomerEntity result = entityManager.find(CustomerEntity.class, customer.getIdcustomer());
        result.setBirthdate(Calendar.getInstance().getTime());

        transaction.commit();
        entityManager.close();

        return result;
    }

    /**
     * Database Interface which deletes a Customer
     * @param id
     * @returns true if the delete was successful
     */
    public boolean deleteCustomer(int id) {
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        CustomerEntity customer = entityManager.find(CustomerEntity.class, id);
        if (customer != null) {
            entityManager.remove(customer);
        }

        transaction.commit();
        entityManager.close();

        if(customer != null) {
            return false;
        } else {
            return true;
        }
    }

    public void disconnect() {
        factory.close();
    }

}
