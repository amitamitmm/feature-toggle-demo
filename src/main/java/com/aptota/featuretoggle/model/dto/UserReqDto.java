package com.aptota.featuretoggle.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserReqDto {
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String username;
    private String password;
    private List<RoleReqDto> roles;
}
