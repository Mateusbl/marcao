package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Cliente> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping
    public Cliente addCustomer(@RequestBody Cliente customer) {
        return customerService.addCustomer(customer);
    }    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}
