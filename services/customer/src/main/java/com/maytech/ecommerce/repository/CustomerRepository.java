package com.maytech.ecommerce.repository;

import com.maytech.ecommerce.customer.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
