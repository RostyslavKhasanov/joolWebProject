package ua.jool.domain;

import lombok.Data;

@Data
public class SigninRequest {

    private String username;
    private String password;
}