package com.saucedemo.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserModelDTO {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String postalCode;
    private String problemUser;
    private String lockedUser;
    private String performanceUser;
}