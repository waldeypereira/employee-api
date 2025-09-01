package com.wfrocha.employee_api.dao;

import com.wfrocha.employee_api.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();

    Employee findById(int theId);

    Employee save(Employee theEmployee);

    Employee update(Employee theEmployee);

    void deleteById(int theId);


}
