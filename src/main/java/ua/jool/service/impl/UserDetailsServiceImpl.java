package ua.jool.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.jool.entity.UserEntity;
import ua.jool.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(
                    () ->  new UsernameNotFoundException("User with username: " + username + " not found")
        );
        return User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(getAuthority(user))
                .build();
    }

    private Set<SimpleGrantedAuthority> getAuthority(UserEntity entity) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        entity.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }

}
