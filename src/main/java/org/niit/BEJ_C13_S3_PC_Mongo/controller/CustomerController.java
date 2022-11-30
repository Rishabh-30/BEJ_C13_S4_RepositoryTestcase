package org.niit.BEJ_C13_S3_PC_Mongo.controller;

import org.niit.BEJ_C13_S3_PC_Mongo.domain.Customer;
import org.niit.BEJ_C13_S3_PC_Mongo.exception.CustomerNotFoundException;
import org.niit.BEJ_C13_S3_PC_Mongo.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2")
public class CustomerController {

    public ICustomerService customerService;




    @Autowired
    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }




    @PostMapping("/customers")
    public ResponseEntity<?> addCustomer (@RequestBody Customer c){
        return new ResponseEntity<>(customerService.saveCustomer(c), HttpStatus.CREATED);
    }
    @GetMapping("/customers")
    public ResponseEntity<?> getAllCustomers(){
        ResponseEntity responseEntity =null;
        try {
            responseEntity= new ResponseEntity<>(customerService.getAllCustomer(),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseEntity;
    }


    @GetMapping("/customer/{productName}")
    public ResponseEntity<?> getAllCustomersByProductName(@PathVariable String productName) throws CustomerNotFoundException{
        ResponseEntity responseEntity=null;
        try{
            responseEntity=new ResponseEntity(customerService.getAllCustomerByProductName(productName),HttpStatus.OK);

        } catch (CustomerNotFoundException e) {
//            responseEntity=new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
            throw new CustomerNotFoundException();
        }
        return responseEntity;
    }












    @DeleteMapping( "/customer/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable int customerId) throws CustomerNotFoundException {
        ResponseEntity responseEntity =null;
        try {
            customerService.deleteCustomer(customerId);
            responseEntity = new ResponseEntity<>("successfully deleted one record",HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
            throw new CustomerNotFoundException();
        }catch (Exception e){
            responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}

