package ua.jool.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.jool.config.jwt.JwtTokenProvider;
import ua.jool.domain.SigninRequest;
import ua.jool.domain.UserDTO;
import ua.jool.entity.RoleEntity;
import ua.jool.entity.UserEntity;
import ua.jool.exception.AlreadyExistsException;
import ua.jool.exception.PasswordConfirmException;
import ua.jool.exception.ResourceNotFoundException;
import ua.jool.repository.RoleRepository;
import ua.jool.repository.UserRepository;
import ua.jool.service.AuthService;
import ua.jool.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @Override
    public void signup(UserDTO userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new AlreadyExistsException("User with name " + userDto.getUsername() + " already exists");
        }

        if (!(userDto.getPassword().equals(userDto.getPasswordConfirm()))) {
            throw new PasswordConfirmException("PasswordConfirm and password not equals");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userEntity.setEmail(userDto.getEmail());
        userEntity.setAge(userDto.getAge());
        userEntity.setGender(userDto.getGender());

        RoleEntity role = roleRepository.findByName("USER").orElseThrow(
                () -> new ResourceNotFoundException("Role not found")
        );

        Set<RoleEntity> roles = new HashSet<>();
        roles.add(role);
        userEntity.setRoles(roles);
        userRepository.save(userEntity);
    }

    @Override
    public String signin(SigninRequest request) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsername(),
                                request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        return token;
    }
}
