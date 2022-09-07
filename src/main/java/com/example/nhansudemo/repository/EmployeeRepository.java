package com.example.nhansudemo.repository;

import com.example.nhansudemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * Phan Duy Thái
 * 9/7/2022
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
