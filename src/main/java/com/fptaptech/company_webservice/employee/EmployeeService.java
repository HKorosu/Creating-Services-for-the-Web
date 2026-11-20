package com.fptaptech.company_webservice.employee;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional(readOnly = true)
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee addEmployee(Employee employee) {
        validate(employee);
        if (employee.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A new employee must not have an id");
        }
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employee) {
        validate(employee);
        if (employee.getId() != null && !id.equals(employee.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee id does not match the request");
        }

        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
        existing.setName(employee.getName().trim());
        existing.setSalary(employee.getSalary());
        return employeeRepository.save(existing);
    }

    private void validate(Employee employee) {
        if (employee == null || employee.getName() == null || employee.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee name is required");
        }
        if (employee.getName().trim().length() > 150) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee name must be at most 150 characters");
        }
        BigDecimal salary = employee.getSalary();
        if (salary == null || salary.signum() < 0 || salary.scale() > 2 || salary.precision() > 12) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Salary must be non-negative with at most 2 decimal places");
        }
        employee.setName(employee.getName().trim());
    }
}
