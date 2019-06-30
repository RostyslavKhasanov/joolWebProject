package ua.jool.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.jool.domain.UserDTO;
import ua.jool.entity.UserEntity;
import ua.jool.exception.AlreadyExistsException;
import ua.jool.exception.PasswordConfirmException;
import ua.jool.exception.ResourceNotFoundException;
import ua.jool.repository.UserRepository;
import ua.jool.service.UserService;
import ua.jool.utils.ObjectMapperUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapperUtils modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void create(UserDTO userDTO) {
        if (userDTO.getPassword().equals(userDTO.getPasswordConfirm())) {
            UserEntity user = modelMapper.map(userDTO, UserEntity.class);
            userRepository.save(user);
        } else throw new PasswordConfirmException("PasswordConfirm is not equals password");
    }

    @Override
    public UserDTO findUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User with id " + id + " not found")
        );
        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
        return userDTO;
    }

    @Override
    public void addImageToUser(String image, Long id) {
        UserEntity gameEntity = userRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User with id [" + id + "] not found")
                );
        gameEntity.setImage(image);
        userRepository.save(gameEntity);
    }

    @Override
    public UserDTO findByName(String username) {
        UserEntity user = userRepository.findByUsername(username).get();
        UserDTO userDto = modelMapper.map(user, UserDTO.class);
        return userDto;
    }

    @Override
    public void updatePassword(String username, String password, String passwordConfirm) {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("User not found")
        );

        if (passwordEncoder.matches(passwordConfirm, userEntity.getPassword())) {
            userEntity.setPassword(passwordEncoder.encode(password));
            userRepository.save(userEntity);
        } else {
            throw new PasswordConfirmException("Wrong confirm password");
        }
    }

    @Override
    public void updateUsername(String currentUsername, String newUsername) {
        UserEntity userEntity = userRepository.findByUsername(currentUsername).orElseThrow(
                () -> new ResourceNotFoundException("User not found")
        );

        if (userRepository.existsByUsername(newUsername)) {
            throw new AlreadyExistsException("Username already exists");
        } else {
            userEntity.setUsername(newUsername);
            userRepository.save(userEntity);
        }
    }
}
