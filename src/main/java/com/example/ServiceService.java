package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServiceService {
    @Autowired
    private ServicoRepository servicoRepository;

    public List<Servico> getAllServices() {
        return servicoRepository.findAll();
    }

    public Servico addService(Servico service) {
        return servicoRepository.save(service);
    }

    public void deleteService(Long id) {
        servicoRepository.deleteById(id);
    }
}
