package ua.jool.domain;

import lombok.Data;

@Data
public class SignupRequest {

    private String username;

    private String password;

    private String email;

    private int age;

}
