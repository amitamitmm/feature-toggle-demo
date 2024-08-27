package com.aptota.featuretoggle.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ROLES")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
