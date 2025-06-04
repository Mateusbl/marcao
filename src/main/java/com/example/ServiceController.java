package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    @GetMapping
    public List<Service> getAllServices() {
        return serviceService.getAllServices();
    }

    @PostMapping
    public Service addService(@RequestBody Service service) {
        return serviceService.addService(service);
    }

    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable String id) {
        serviceService.deleteService(id);
    }
}
