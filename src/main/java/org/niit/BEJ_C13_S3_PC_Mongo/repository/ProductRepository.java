package org.niit.BEJ_C13_S3_PC_Mongo.repository;

import org.niit.BEJ_C13_S3_PC_Mongo.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Customer,Integer> {

    @Query("{'customerProduct.productName' :{$in:[?0]}}")
    List<Customer> findAllCustomerByProductName(String productName);

}
