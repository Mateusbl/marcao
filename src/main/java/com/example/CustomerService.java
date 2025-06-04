package com.example;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private List<Customer> customers = new ArrayList<>();

    public List<Customer> getAllCustomers() {
        return customers;
    }

    public Customer addCustomer(Customer customer) {
        customers.add(customer);
        return customer;
    }

    public void deleteCustomer(String id) {
        customers.removeIf(customer -> customer.getId().equals(id));
    }
}
