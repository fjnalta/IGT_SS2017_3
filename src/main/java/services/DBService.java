package services;

/**
 * Created by ajo on 02.06.17.
 */

import data.EntityCustomer;

import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Interface which represents the CRUD operations
 * C - create
 * R - read
 * U - update
 * D - delete
 * @author ajo
 */
public interface DBService {

    void createCustomer(EntityCustomer customer);
    List<EntityCustomer> getCustomers();
    EntityCustomer updateCustomer(EntityCustomer customer);
    boolean deleteCustomer(int id);

    void disconnect();

}
