package org.niit.BEJ_C13_S3_PC_Mongo.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.niit.BEJ_C13_S3_PC_Mongo.domain.Customer;
import org.niit.BEJ_C13_S3_PC_Mongo.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


    @ExtendWith(SpringExtension.class)
    @DataMongoTest
    class ProductRepositoryTest {

        @Autowired
        private ProductRepository productRepository;
        private Product product;
        private Customer customer;
        @BeforeEach
        void setUp() {
            product=new Product(1,"Apple","Laptop");
            customer=new Customer(1,"Suresh","897654326",product);
        }

        @AfterEach
        void tearDown() {
            product=null;
            customer=null;
            productRepository.deleteAll();
        }
        @Test
        @DisplayName("Test case for saving customer object")
        void giveCustomerToSaveReturnSavedCustomer(){
            productRepository.save(customer);
            Customer customer1=productRepository.findById(customer.getCustomerId()).get();
            assertNotNull(customer1);
            assertEquals(customer.getCustomerId(),customer1.getCustomerId());
        }
        @Test
        @DisplayName("Test case for deleting customer object")
        public void givenCustomerToDeleteShouldDeleteCustomer() {
            productRepository.insert(customer);
            Customer customer1 = productRepository.findById(customer.getCustomerId()).get();
           productRepository.delete(customer1);
            assertEquals(Optional.empty(), productRepository.findById(customer.getCustomerId()));
        }
        @Test
        @DisplayName("Test Case for retrieving all the customer object")
        void givenCustomerReturnAllCustomerDetail(){
          productRepository.insert(customer);
            product=new Product(2,"Vivo","Mobile");
            customer=new Customer(2,"Ramesh","112765436",product);
          productRepository.insert(customer);

            List<Customer> list = productRepository.findAll();
            assertEquals(2, list.size());
            assertNotEquals(6, list.size());
            assertEquals("Ramesh", list.get(1).getCustomerName());
            assertNotEquals("Ram", list.get(1).getCustomerName());
        }
        @Test
        @DisplayName("Test Case for retrieving all the customer by product Name")
        public void getCustomerReturnAllProductName(){
          productRepository.insert(customer);
            product=new Product(2,"Vivo","Mobile");
            customer=new Customer(2,"Ramesh","112765436",product);
           productRepository.insert(customer);
            List<Customer> customers=productRepository.findAllCustomerByProductName(customer.getCustomerProduct().getProductName());
            assertEquals(1,customers.size());
            assertEquals(customer.getCustomerProduct().getProductName(),customers.get(0).getCustomerProduct().getProductName());
        }
    }

