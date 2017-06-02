package services;

/**
 * Created by ajo on 02.06.17.
 */

import data.Customer;

/**
 * Interface which represents the CRUD operations
 * C - create
 * R - read
 * U - update
 * D - delete
 * @author ajo
 */
public interface DBService {

    public void createCustomer(Customer customer);
    public Customer getCustomer();
    public void updateCustomer(Customer customer);
    public boolean removeCustomer(Long id);

    public void disconnect();

}
