package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> getAllEmployees() {
        return funcionarioRepository.findAll();
    }

    public Funcionario addEmployee(Funcionario employee) {
        if (employee.getDataContratacao() == null) {
            employee.setDataContratacao(new Date());
        }
        return funcionarioRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        funcionarioRepository.deleteById(id);
    }
}
