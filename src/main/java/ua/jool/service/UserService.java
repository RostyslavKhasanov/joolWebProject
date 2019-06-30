package ua.jool.service;

import ua.jool.domain.UserDTO;

public interface UserService {

    void create(UserDTO userDTO);

    UserDTO findUserById(Long id);

    void addImageToUser(String image, Long id);

    UserDTO findByName(String username);

    void updatePassword(String username, String password, String passwordConfirm);

    void updateUsername(String currentUsername, String newUsername);
}
