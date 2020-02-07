package com.victor.employer.service;

import com.victor.employer.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee save(Employee employee);
}
