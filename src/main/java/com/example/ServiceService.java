package com.example;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceService {
    private List<Service> services = new ArrayList<>();

    public List<Service> getAllServices() {
        return services;
    }

    public Service addService(Service service) {
        services.add(service);
        return service;
    }

    public void deleteService(String id) {
        services.removeIf(service -> service.getId().equals(id));
    }
}
