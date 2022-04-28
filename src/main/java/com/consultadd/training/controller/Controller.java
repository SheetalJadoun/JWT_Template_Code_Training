package com.consultadd.training.controller;
import com.consultadd.training.model.Employee;
import com.consultadd.training.service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller
{
    @Autowired
    private Services service;

    @GetMapping("/home")
    public String home()
    {
        return "<h1>Welcome</h1>";
    }
    @GetMapping("/get")
    public List<Employee> getAll()
    {
        List<Employee> li=new ArrayList<>();
        li=service.getAllEmployees();
        return li;
    }

    @PostMapping("/create")
    public  String createEmployee(@RequestBody Employee emp)
    {
        if(emp.getId()!=0 && emp.getName()!="" && emp.getSalary()!=0)
        {
            service.saveEmployee(emp);
            return "Employee saved successfully";
        }
        return "Error Occurs";
    }

    @DeleteMapping("/delete{name}")
    public String deleteEmployee(@RequestParam String name)
    {
        Employee emp=service.findEmployee(name);
        if(emp!=null)
        {
            service.deleteEmployee(emp);
            return "Deleted Successfully";
        }
        return "Error Occurs";
    }


    @PutMapping("/update{name}{salary}")
    public String updateSalary(@RequestParam String name,int salary)
    {
        Employee emp=service.findEmployee(name);
        if(emp!=null)
        {
            service.update(emp,salary);
            return "Updated Successfully";
        }
        return "Error Occurs";
    }

}
