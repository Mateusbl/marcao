package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllCustomers() {
        return clienteRepository.findAll();
    }

    public Cliente addCustomer(Cliente customer) {
        if (customer.getDataRegistro() == null) {
            customer.setDataRegistro(new Date());
        }
        return clienteRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        clienteRepository.deleteById(id);
    }
}
