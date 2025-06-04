package com.example;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private List<Funcionario> employees = new ArrayList<>();

    public List<Funcionario> getAllEmployees() {
        return employees;
    }

    public Funcionario addEmployee(Funcionario employee) {
        employees.add(employee);
        return employee;
    }

    public void deleteEmployee(Integer idFuncionario) {
        employees.removeIf(employee -> employee.getIdFuncionario().equals(idFuncionario));
    }
}
