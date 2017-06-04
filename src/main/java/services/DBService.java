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

    public void createCustomer(CustomerEntity customer);
    public List<CustomerEntity> getCustomers();
    public void updateCustomer(CustomerEntity customer);
    public boolean removeCustomer(Long id);

    public void disconnect();

}
