package com.spring.service;

import com.spring.entity.Employee;
import com.spring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Page<Employee> getAllEmployees(int page, int limit) {
        return employeeRepository.findAll(PageRequest.of(page-1, limit));
    }

    @Override
    public void createEmp(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void updateEmp(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmp(Integer id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee getDetailEmp(Integer id) {
        if (employeeRepository.findById(id).isPresent())
            return employeeRepository.findById(id).get();
        return null;
    }

    @Override
    public List<Employee> searchByName(String q) {
        return employeeRepository.findAllByNameContaining(q);
    }
}
