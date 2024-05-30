package com.app.security.config;

import static org.springframework.security.config.Customizer.withDefaults;

import com.app.security.service.security.CustomAuthenticationFailureHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationFailureHandler failureHandler;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers("/info").hasAnyRole("VIEW_INFO", "VIEW_ADMIN")
                                .requestMatchers("/admin").hasRole("VIEW_ADMIN")
                                .requestMatchers("/user/*").hasRole("VIEW_ADMIN")
                                .requestMatchers("/secret/provider/").authenticated()
                                .requestMatchers("/secret/provider/**").authenticated()
                                .anyRequest().permitAll())
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login"))
                .formLogin(loginForm -> loginForm
                        .loginPage("/login")
                        .successForwardUrl("/login/success")
                        .failureHandler(failureHandler))
                .httpBasic(withDefaults());
        return http.build();
    }

}
