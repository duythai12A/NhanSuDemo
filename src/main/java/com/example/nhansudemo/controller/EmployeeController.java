package com.example.nhansudemo.controller;


import com.example.nhansudemo.dto.EmployeeDTO;
import com.example.nhansudemo.entity.Employee;
import com.example.nhansudemo.entity.Role;
import com.example.nhansudemo.repository.EmployeeRepository;
import com.example.nhansudemo.repository.RoleRepository;
import com.example.nhansudemo.security.JwtUtil;
import com.example.nhansudemo.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * Phan Duy Thai
 * 9/7/2022
 * */
@RestController

public class EmployeeController {
    @Autowired
    private EmployeeService service;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    ModelMapper modelMapper;
    @PostMapping("/addEmp")//Them mot nhan vien
    public Employee addEmp(@RequestBody Employee emp){
        emp.getRole().setPassword(passwordEncoder.encode(emp.getRole().getPassword()));
        return service.saveEmp(emp);
    }
    @PostMapping("/addEmps")//Them nhieu nhan vien
    public List<Employee> addEmps(@RequestBody List<Employee> emps){

        return service.saveEmps(emps);
    }
    @GetMapping("/getEmps")//Lay danh sach nhan vien
    public List<Employee> findAllEmp(){
        return service.getEmployees();
    }

    @PostMapping("/authenticate")//API tao token
    public String generateToken(@RequestBody Role account) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(account.getUsername(),account.getPassword()));
        }catch (Exception e){
            throw new Exception("invalid username/password");
        }
        return jwtUtil.generateToken(account.getUsername());
    }
    @GetMapping("/{id}")
    public EmployeeDTO findEmp(@PathVariable(value = "id") int id){
        EmployeeDTO emp = modelMapper.map(service.getEmp(id),EmployeeDTO.class);
        return emp;
    }
    @DeleteMapping("/delete/{id}")//xoa nhan vien theo id
    public String deleteEmp(@PathVariable(value = "id") int id){
        return service.deleteEmp(id);
    }
    @PutMapping("/update")
    public Employee updateEmp(@RequestBody Employee emp){
        return service.updateEmp(emp);
    }

}
