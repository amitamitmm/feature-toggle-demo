package com.aptota.featuretoggle.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String username;
    private String password;

    @OneToMany( mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Role> roles;

}
