package com.wfrocha.employee_api.service;

import com.wfrocha.employee_api.dao.EmployeeDAO;
import com.wfrocha.employee_api.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee findById(int theId) {
        return employeeDAO.findById(theId);
    }

    @Override
    @Transactional()
    public Employee save(Employee theEmployee) {
        return employeeDAO.save(theEmployee);
    }

    @Override
    @Transactional
    public Employee update(Employee theEmployee) {
        return employeeDAO.update(theEmployee);
    }

    @Override
    @Transactional()
    public void deleteById(int theId) {
        employeeDAO.deleteById(theId);
    }
}
