package com.example.nhansudemo.repository;

import com.example.nhansudemo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * Phan Duy Thái
 * 9/7/2022
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

    Role findByUsername(String username);
}
