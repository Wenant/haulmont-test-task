package com.haulmont.test_task.service;

import com.haulmont.test_task.model.Customer;
import com.haulmont.test_task.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;


    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public Customer findById(UUID id){
        return customerRepository.getById(id);
    }

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public void deleteById(UUID id){
        customerRepository.deleteById(id);
    }
}