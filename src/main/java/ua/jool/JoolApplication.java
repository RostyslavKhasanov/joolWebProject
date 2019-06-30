package ua.jool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ua.jool.entity.RoleEntity;
import ua.jool.entity.UserEntity;
import ua.jool.enums.GenderEnum;
import ua.jool.exception.ResourceNotFoundException;
import ua.jool.repository.RoleRepository;
import ua.jool.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@EnableWebMvc
@SpringBootApplication
public class JoolApplication implements WebMvcConfigurer, CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JoolApplication.class, args);
    }

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if (roleRepository.count() == 0) {
            RoleEntity entity = new RoleEntity();
            entity.setName("ADMIN");

            roleRepository.save(entity);

            entity = new RoleEntity();
            entity.setName("USER");

            roleRepository.save(entity);
        }


        /*if (true) {
            UserEntity user = new UserEntity();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("111111111"));
            user.setGender(GenderEnum.MALE);
            user.setEmail("rostuk.khasanovee@gmail.com");
            user.setAge(18);

            RoleEntity role = roleRepository.findByName("ADMIN")
                    .orElseThrow(() -> new ResourceNotFoundException("Role not found"));

            Set<RoleEntity> roles = new HashSet<>();
            roles.add(role);
            user.setRoles(roles);
            userRepository.save(user);
        }*/
    }
}
