package org.niit.BEJ_C13_S3_PC_Mongo.service;

import org.niit.BEJ_C13_S3_PC_Mongo.domain.Customer;
import org.niit.BEJ_C13_S3_PC_Mongo.exception.CustomerNotFoundException;
import org.niit.BEJ_C13_S3_PC_Mongo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerServiceImpl implements ICustomerService{

    @Autowired
    public ProductRepository productRepository;
    @Override
    public Customer saveCustomer(Customer c) {
        return productRepository.insert(c);
    }

    @Override
    public List<Customer> getAllCustomer()throws Exception {
        return productRepository.findAll();
    }

    @Override
    public boolean deleteCustomer(int customerId) throws CustomerNotFoundException {
        boolean flag = false;
        if(productRepository.findById(customerId).isEmpty()){
            throw new CustomerNotFoundException();
        }
        else{
          productRepository.deleteById(customerId);
            flag=true;
        }
        return flag;
    }

    @Override
    public List<Customer> getAllCustomerByProductName(String productName) throws CustomerNotFoundException {
        if(productRepository.findAllCustomerByProductName(productName).isEmpty()){
            throw new CustomerNotFoundException();
        }
        return productRepository.findAllCustomerByProductName(productName);
    }



}

