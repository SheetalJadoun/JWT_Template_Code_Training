package com.consultadd.training.repository;


import com.consultadd.training.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Employee,String>
{
    Employee findByName(String name);
}
