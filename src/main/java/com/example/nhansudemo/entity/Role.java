package com.example.nhansudemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "role")

/*
* Phan Duy Thái
* 9/7/2022
*/
//Lớp để đăng nhập,phân quyền
public class Role {
    @Id
    @Column(name = "account_id")
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "roles")
    private String roles;
}
