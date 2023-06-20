package org.springbootcourse.service;

import org.springbootcourse.model.Employee;
import org.springbootcourse.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Iterable<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(String id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()){
            return optionalEmployee.get();
        }else{
            return null;
        }
    }

    @Override
    public Employee updateEmployee(String id, Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()){
            employee.setId(id);
            return employeeRepository.save(employee);
        }else {
            return null;
        }
    }

    @Override
    public void deleteEmployee(String id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findByName(String name) {
        return employeeRepository.findByName(name);
    }

    @Override
    public Page<Employee> paginateEmployees(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return employeeRepository.findAll(pageable);
    }

    @Override
    public List<Employee> filterEmployeesBySalaryRange(double min, double max) {
        return employeeRepository.findBySalaryBetween(min,max);
    }


    @Override
    public List<Employee> searchEmployeeByNameMatch(String name) {
        return employeeRepository.findByNameMatchQuery(name);
    }

    @Override
    public List<Employee> searchEmployeeByNameAndSalaryRange(String name, double minSalary) {
        return employeeRepository.findBYMatchNameandSalaryRange(name,minSalary);
    }

    @Override
    public List<Employee> findBySalaryRange(double minSalary, double maxSalary) {
        return employeeRepository.findBySalaryRange(minSalary,maxSalary);
    }

}
