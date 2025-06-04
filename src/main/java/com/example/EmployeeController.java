package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Funcionario> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public Funcionario addEmployee(@RequestBody Funcionario employee) {
        return employeeService.addEmployee(employee);
    }    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}
