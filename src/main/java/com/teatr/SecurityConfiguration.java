package com.teatr;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    /**
     * Konfiguracja użytkowników w pamięci (in-memory).
     */
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password("user")
                .roles("SPECTATOR")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password("admin")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    /**
     * Konfiguracja SecurityFilterChain (zastępuje WebSecurityConfigurerAdapter).
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/index", "/resources/static/**", "/webjars/**", "/css/**", "/js/**", "/images/**", "/icons/**").permitAll() // Publiczne zasoby
                        .requestMatchers("/main").authenticated() // Autoryzacja wymagana
                        .requestMatchers("/main_admin").hasRole("ADMIN") // Dostęp tylko dla administratorów
                        .requestMatchers("/main_spectator").hasRole("SPECTATOR") // Dostęp tylko dla użytkowników
                        .anyRequest().authenticated() // Wszystkie inne ścieżki wymagają uwierzytelnienia
                )
                .formLogin(form -> form
                        .loginPage("/login") // Niestandardowa strona logowania
                        .defaultSuccessUrl("/main") // Domyślna strona po zalogowaniu
                        .permitAll() // Dostęp dla wszystkich do strony logowania
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // Ścieżka do wylogowania
                        .logoutSuccessUrl("/index") // Strona po wylogowaniu
                        .permitAll() // Dostęp dla wszystkich do wylogowania
                );

        return http.build();
    }

    /**
     * Prosty enkoder hasła (niezalecany w produkcji).
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}