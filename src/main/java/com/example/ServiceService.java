package com.example;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceService {
    private List<Servico> services = new ArrayList<>();

    public List<Servico> getAllServices() {
        return services;
    }

    public Servico addService(Servico service) {
        services.add(service);
        return service;
    }

    public void deleteService(Integer idServico) {
        services.removeIf(service -> service.getIdServico().equals(idServico));
    }
}
