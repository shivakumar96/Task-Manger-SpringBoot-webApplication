package com.app.taskmanger.firstapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.function.Function;

@Configuration
public class SpringSecurityConfiguration {
    //LDAP of Database
    //In Memory

    @Bean
    public InMemoryUserDetailsManager createUserDetailsManger(){

        UserDetails userDetails1 = createNewUser("admin","admin");
        UserDetails userDetails2 = createNewUser("user","user");
        return new InMemoryUserDetailsManager(userDetails1,userDetails2);
    }

    private UserDetails createNewUser(String username, String password) {
        Function<String, String> passWordEncoder
                = input->passWordEncoder().encode(input);

        UserDetails userDetails = User.builder()
                .passwordEncoder(passWordEncoder)
                .username(username)
                .password(password)
                .roles("USER","ADMIN")
                .build();
        return userDetails;
    }

    @Bean
    public PasswordEncoder passWordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    //all URLs are protected
    // Alogin form is shown for unauthorized requests
    // so we beed to CSRF disable
    // H2 usesFrames, spring security blocks it

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(
          auth->auth.anyRequest().authenticated()
        );
        http.formLogin(withDefaults());
        http.csrf().disable();
        http.headers().frameOptions().disable();
        return http.build();
    }
}
