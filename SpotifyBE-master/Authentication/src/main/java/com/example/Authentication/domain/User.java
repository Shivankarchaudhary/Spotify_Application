package com.example.Authentication.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String email;
    private String password;


}
