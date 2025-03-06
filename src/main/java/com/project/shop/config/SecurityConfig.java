package com.project.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "SELECT username, password, enabled FROM users WHERE username=?"
        );

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT username, role FROM roles WHERE username=?"
        );

        return jdbcUserDetailsManager;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/home", "/gallery", "/css/**", "/img/**", "/js/**").permitAll()  // Головна, галерея та статичні файли доступні без логіну
                        .requestMatchers("/order-status", "/profile").authenticated() // Потрібен логін
                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/login")  // Власна сторінка входу
                        .loginProcessingUrl("/process-login") // Spring Security сам обробляє цей шлях
                        .defaultSuccessUrl("/home", true)  // Куди перенаправляти після успіху
                        .failureUrl("/login?error=true")  // Куди перенаправляти після невдалого входу
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/home")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .permitAll()
                )

                .httpBasic(Customizer.withDefaults())
                .csrf(csfr -> csfr.disable());

        return http.build();
    }



}
