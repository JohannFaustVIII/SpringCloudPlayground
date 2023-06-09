package joh.faust;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
        httpSecurity.csrf().disable().authorizeExchange()
                .pathMatchers("/discovery/**", "/eureka/**").permitAll()
                .anyExchange()
                .authenticated()
                .and()
                .oauth2Login();

        return httpSecurity.build();
    }

}
