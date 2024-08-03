package dev.rajat.User.service.Security;

import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurity {
@Bean
@Order(1)

    public SecurityFilterChain filteringCriteria(HttpSecurity http) throws Exception
    {
         // http.cors().disable();
        //http.csrf().disable();
       // http.csrf(AbstractHttpConfigurer::disable)
              //  .cors(AbstractHttpConfigurer::disable);
        /*http.authorizeHttpRequests((requests) -> {
                            try {
                                requests
                                        .anyRequest().permitAll()
                                        .and().cors().disable()
                                        .csrf().disable();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
        return http.build();*/
        http.cors().disable();
        http.csrf().disable();
//        http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/auth/*").permitAll());
//        http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/order/*").authenticated());
        http.authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll());
        return http.build();

    }


    @Bean
    BCryptPasswordEncoder bycryptPasswordEncorder()
    {
        return new BCryptPasswordEncoder();
    }
}
