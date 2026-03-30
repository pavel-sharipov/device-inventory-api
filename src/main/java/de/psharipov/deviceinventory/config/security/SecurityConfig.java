package de.psharipov.deviceinventory.config.security;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService(SecurityProperties props, PasswordEncoder passwordEncoder) {

        UserDetails viewer = User.withUsername(props.viewerUser())
                .password(passwordEncoder.encode(props.viewerPassword()))
                .roles("VIEWER")
                .build();

        UserDetails admin = User.withUsername(props.adminUser())
                .password(passwordEncoder.encode(props.adminPassword()))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(viewer, admin);
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/actuator/health", "/actuator/info").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/v1/devices/**")
                        .hasAnyRole("VIEWER", "ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/v1/devices/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PUT, "/api/v1/devices/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.DELETE, "/api/v1/devices/**")
                        .hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
