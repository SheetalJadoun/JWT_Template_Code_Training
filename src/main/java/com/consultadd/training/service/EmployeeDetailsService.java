package com.consultadd.training.service;
import com.consultadd.training.model.Employee;
import com.consultadd.training.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDetailsService implements UserDetailsService
{
    @Autowired
    private Repository repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Employee emp=repo.findByName(username);
        if(emp==null)
        {
            System.out.println("Employee not found");
            throw new UsernameNotFoundException("Employee not found");
        }
        System.out.println(emp.getName());
        return new EmployeeDetails(emp);

    }
}
