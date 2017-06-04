package services;

/**
 * Created by ajo on 02.06.17.
 */

import data.CustomerEntity;

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

    void createCustomer(CustomerEntity customer);
    List<CustomerEntity> getCustomers();
    void updateCustomer(CustomerEntity customer);
    boolean removeCustomer(Long id);

    void disconnect();

}
