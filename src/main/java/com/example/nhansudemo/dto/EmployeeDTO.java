package com.example.nhansudemo.dto;

import com.example.nhansudemo.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {


    private String name;

    private Date birth_date;

    private String gender;

    private String phone;

    private String address;

    //e thai ngu
    //alo e thai
}
