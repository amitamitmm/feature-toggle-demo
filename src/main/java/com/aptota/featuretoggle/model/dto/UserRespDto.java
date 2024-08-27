package com.aptota.featuretoggle.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRespDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String username;
    private String password;
    private List<RoleReqDto> roles;
}
