package ua.jool.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ua.jool.config.jwt.JwtAuthenticationEntryPoint;
import ua.jool.config.jwt.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizeHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();

        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth/signup").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/signin").permitAll()
                .antMatchers(HttpMethod.GET, "/games/image").permitAll()
                .antMatchers(HttpMethod.GET, "/games/pages").permitAll()
                .antMatchers(HttpMethod.GET, "/games/search").permitAll()
                .antMatchers(HttpMethod.GET, "/games/filter/**").permitAll()
                .antMatchers(HttpMethod.GET, "/games/filter**").permitAll()
                .antMatchers(HttpMethod.GET, "/categories").permitAll()
                .antMatchers(HttpMethod.GET, "/developers").permitAll()
                .antMatchers(HttpMethod.GET, "/users/image**").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/games/{gameId}/image").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/games").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/categories").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/developers").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/orders").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST,"/update/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST,"/users**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE,"/games/{gameId}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/categories/{categoryId}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/developers/{developerId}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/games/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET,"/orders/{id}").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET,"/users/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET,"/orders/all").hasRole("ADMIN")
                .anyRequest().authenticated();

        http.exceptionHandling().authenticationEntryPoint(unauthorizeHandler)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public JwtAuthenticationFilter authenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
