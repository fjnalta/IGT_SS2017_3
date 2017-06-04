package services;

import data.EntityCustomer;

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
     * Database Interface which creates a new CustomerEntity
     * @param customer
     */
    public void createCustomer(EntityCustomer customer) {
        EntityManager entityManager = factory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(customer);

        entityManager.flush();
        transaction.commit();
        entityManager.close();
    }

    /**
     * Database Interface which gets all CustomerEntity Entries
     * @returns all CustomerEntities
     */
    public List<EntityCustomer> getCustomers() {
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        List<EntityCustomer> result = entityManager
                .createQuery("SELECT c FROM EntityCustomer c", EntityCustomer.class)
                .getResultList();

        transaction.commit();
        entityManager.close();

        return result;
    }

    /**
     * Database Interface which updates a CustomerEntity
     * @param customer the updated CustomerEntity
     */
    public EntityCustomer updateCustomer(EntityCustomer customer) {
        EntityManager entityManager = factory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        EntityCustomer result = entityManager.find(EntityCustomer.class, customer.getIdcustomer());
        result.setBirthdate(Calendar.getInstance().getTime());

        transaction.commit();
        entityManager.close();

        return result;
    }

    /**
     * Database Interface which deletes a CustomerEntity
     * @param id
     * @returns true if the delete was successful
     */
    public boolean deleteCustomer(int id) {
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        EntityCustomer customer = entityManager.find(EntityCustomer.class, id);
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
