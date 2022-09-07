package com.example.nhansudemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
@Data
/*
* Phan Duy Thái
* 9/7/2022
*/
//Lớp nhân viên
public class Employee {
    @Id
    @Column(name = "emp_id")
    private int emp_id;
    @NotBlank(message = "Tên không được để trống")
    @Size(max = 100)
    @Column(name = "name")
    private String name;
    @Column(name = "birth_date")
    private Date birth_date;
    @Column(name = "gender")
    private String gender;
    @Pattern(regexp = "^\\d{10}$",message = "số điện thoại không hợp lệ")
    @Column(name = "phone")
    private String phone;
    @Size(max = 150,message = "độ dài không lớn hơn 150 ký tự")
    @Column(name = "address")
    private String address;
    @OneToOne(targetEntity = Role.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id",referencedColumnName = "account_id")
    private Role role;
}
