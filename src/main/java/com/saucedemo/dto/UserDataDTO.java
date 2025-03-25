package com.saucedemo.dto;

public class UserDataDTO {
    public static UserModelDTO userData() {
        return new UserModelDTO(
                "standard_user",
                "secret_sauce",
                "Ricardo",
                "Costa",
                "90619-900",
                "problem_user",
                "locked_out_user",
                "performance_glitch_user"
        );
    }
}