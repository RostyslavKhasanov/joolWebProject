package ua.jool.service;

import ua.jool.domain.SigninRequest;
import ua.jool.domain.UserDTO;

public interface AuthService {

    void signup(UserDTO userDto);

    String signin(SigninRequest request);
}
