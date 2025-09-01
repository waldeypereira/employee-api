package com.wfrocha.employee_api.service;

import com.wfrocha.employee_api.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

}
