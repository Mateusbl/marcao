package com.example;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private List<Cliente> customers = new ArrayList<>();

    public List<Cliente> getAllCustomers() {
        return customers;
    }

    public Cliente addCustomer(Cliente customer) {
        customers.add(customer);
        return customer;
    }

    public void deleteCustomer(Integer idCliente) {
        customers.removeIf(customer -> customer.getIdCliente().equals(idCliente));
    }
}
