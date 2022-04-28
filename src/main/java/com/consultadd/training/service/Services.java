package com.consultadd.training.service;

import com.consultadd.training.model.Employee;
import com.consultadd.training.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Services
{
    @Autowired
    private Repository repo;

    public List<Employee> getAllEmployees()
    {
        List<Employee> li=repo.findAll();
        return li;
    }

    public void saveEmployee(Employee emp)
    {
        repo.save(emp);
    }

    public Employee findEmployee(String name)
    {
        Employee emp=repo.findByName(name);
        return emp;
    }

    public void deleteEmployee(Employee emp)
    {
        repo.delete(emp);
    }

    public void update(Employee emp, int salary)
    {
        emp.setSalary(salary);
        repo.save(emp);
    }
}
