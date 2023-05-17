package ua.odessa.moneyApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ua.odessa.moneyApp.services.PersonDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  private final PersonDetailsService personDetailsService;

  public SecurityConfig(PersonDetailsService personDetailsService) {
    this.personDetailsService = personDetailsService;
  }

  @Bean
  public SecurityFilterChain configure(HttpSecurity http) throws Exception {
    AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
    authenticationManagerBuilder.userDetailsService(personDetailsService);
    AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

    http
            .csrf()
            .disable()
            .authenticationManager(authenticationManager)
            .authorizeHttpRequests()
            .requestMatchers("/styles/mainstyle.css", "/auth/login", "auth/registration", "/main", "/about", "/", "/error")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
            .loginPage("/auth/login")
            .loginProcessingUrl("/process_login")
            .defaultSuccessUrl("/transaction", true)
            .failureUrl("/auth/login?error")
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/auth/login");
    return http.build();
  }

  void registerProvider(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(personDetailsService);
  }

  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

//  private final AuthProviderImpl authProvider;
//
//  public SecurityConfig(AuthProviderImpl authProvider) {
//    this.authProvider = authProvider;
//  }
//
//  void registerProvider(AuthenticationManagerBuilder auth) {
//    auth.authenticationProvider(authProvider);
//  }
}
