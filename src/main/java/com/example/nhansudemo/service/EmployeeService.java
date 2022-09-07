package com.example.nhansudemo.service;

import com.example.nhansudemo.entity.Employee;
import com.example.nhansudemo.entity.Role;
import com.example.nhansudemo.repository.EmployeeRepository;
import com.example.nhansudemo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Phan Duy Thai
 * 9/7/2022
 * */
@Service
public class EmployeeService implements UserDetailsService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RoleRepository roleRepository;

    public Employee saveEmp(Employee employee){//luu 1 nhan vien

        return employeeRepository.save(employee);
    }

    public List<Employee> saveEmps(List<Employee> employees){//luu nhieu nhan vien

        return employeeRepository.saveAll(employees);
    }

    public Employee getEmp(int id){
        return employeeRepository.findById(id).get();
    }
    public List<Employee> getEmployees(){//lay danh sach tat ca nhan vien
        return  employeeRepository.findAll();
    }
    public String deleteEmp(int id){//xoa nhan vien theo id
        employeeRepository.deleteById(id);
        return "removed by id = "+id;
    }
    public Employee updateEmp(Employee emp){//cap nhat thong tin nhan vien
        Employee employee = employeeRepository.findById(emp.getEmp_id()).orElse(null);
        employee.setName(emp.getName());
        employee.setAddress(emp.getAddress());
        employee.setPhone(emp.getPhone());
        employee.setGender(emp.getGender());
        employee.setBirth_date(emp.getBirth_date());
        employee.getRole().setRoles(emp.getRole().getRoles());
        employee.getRole().setUsername(emp.getRole().getUsername());
        employee.getRole().setPassword(emp.getRole().getPassword());
        return emp;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {//lay username trong database
        Role customer = roleRepository.findByUsername(username);
        if (customer == null){
            throw new UsernameNotFoundException("Not found username");
        }
        UserDetails userDetails = new User(
                customer.getUsername(),
                customer.getPassword(), AuthorityUtils.createAuthorityList(customer.getRoles())
        );
        return userDetails;
    }
}
