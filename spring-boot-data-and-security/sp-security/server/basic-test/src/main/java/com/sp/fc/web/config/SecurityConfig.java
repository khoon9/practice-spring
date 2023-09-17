package com.sp.fc.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
// 이제부터 prePostEnabled 로 권한 체크를 실시하겠다는 의미
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig
{
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user01 = User.builder()
                .username("user2")
                .password(passwordEncoder().encode("2222"))
                .roles("USER")
                .build();
        UserDetails user02 = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("3333"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user01, user02);
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults());
        return http.build();
    }

    // 패스워드 설정시 패스워드 인코더가 필수적으로 적용되어야 하기에 메소드를 생성했다.
    // 이걸 수동으로 걸어주면 패스워드 인코딩이 된다.
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}