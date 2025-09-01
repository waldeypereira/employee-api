package com.wfrocha.employee_api.rest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wfrocha.employee_api.entity.Employee;
import com.wfrocha.employee_api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.apache.tomcat.jni.SSLConf.apply;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private ObjectMapper objectMapper;

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        Employee theEmployee = employeeService.findById(id);
        if (theEmployee == null) {
            throw new RuntimeException("Employee id not found - " + id);
        }
        return theEmployee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee) {
        theEmployee.setId(0); // to force a save of new item instead of update
        Employee newEmployee = employeeService.save(theEmployee);
        return newEmployee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee) {
        Employee updatedEmployee = employeeService.update(theEmployee);
        return updatedEmployee;
    }

    @PatchMapping("/employees/{id}")
    public Employee patchEmployee(@PathVariable int id, @RequestBody ObjectNode updates) {
        Employee theEmployee = employeeService.findById(id);
        if (theEmployee == null) {
            throw new RuntimeException("Employee id not found - " + id);
        }

        if (updates.has("id")) {
            throw new RuntimeException("Employee id not allowed in request body " + id);
        }

        ObjectMapper mapper = new ObjectMapper();

        // Converte o Employee existente para ObjectNode
        ObjectNode existingNode = mapper.valueToTree(theEmployee);

        // Mescla os updates no nó existente
        existingNode.setAll(updates);

        // Converte de volta para Employee
        Employee patchedEmployee = mapper.convertValue(existingNode, Employee.class);
        employeeService.save(patchedEmployee);
        return patchedEmployee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        Employee theEmployee = employeeService.findById(id);
        if (theEmployee == null) {
            throw new RuntimeException("Employee id not found - " + id);
        }
        employeeService.deleteById(id);
        return "Deleted employee id - " + id;
    }
}
