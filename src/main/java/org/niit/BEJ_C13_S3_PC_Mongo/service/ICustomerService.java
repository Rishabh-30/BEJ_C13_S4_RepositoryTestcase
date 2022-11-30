package org.niit.BEJ_C13_S3_PC_Mongo.service;

import org.niit.BEJ_C13_S3_PC_Mongo.domain.Customer;
import org.niit.BEJ_C13_S3_PC_Mongo.exception.CustomerNotFoundException;

import java.util.List;

public interface ICustomerService  {

    public Customer saveCustomer(Customer c);
    public List<Customer> getAllCustomer()throws Exception;

    boolean  deleteCustomer(int id) throws CustomerNotFoundException;
    public List<Customer> getAllCustomerByProductName(String productName) throws CustomerNotFoundException ;
}
