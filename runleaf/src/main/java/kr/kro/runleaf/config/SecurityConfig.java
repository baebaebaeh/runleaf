package kr.kro.runleaf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import kr.kro.runleaf.jwt.CustomAuthenticationFailureHandler;
import kr.kro.runleaf.jwt.JWTFilter;
import kr.kro.runleaf.jwt.JWTUtil;
import kr.kro.runleaf.jwt.LoginFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final CustomAuthenticationFailureHandler failureHandler;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil;

    public SecurityConfig(CustomAuthenticationFailureHandler failureHandler,
			AuthenticationConfiguration authenticationConfiguration, JWTUtil jwtUtil) {
		this.failureHandler = failureHandler;
		this.authenticationConfiguration = authenticationConfiguration;
		this.jwtUtil = jwtUtil;
	}

	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //csrf disable
        http
                .csrf((auth) -> auth.disable());

        //From 로그인 방식 disable
        http
        		.formLogin(form -> form
        				.loginProcessingUrl("/login")
        				.failureHandler(failureHandler)  // 실패 핸들러 등록
        				.permitAll()
            );

        //http basic 인증 방식 disable
        http
                .httpBasic((auth) -> auth.disable());
        

        http
                .authorizeHttpRequests((auth) -> auth

                        .requestMatchers("/login", "/join", "/member/**", "/follow/**", "/running/list", "/uploads/**", "/running/board/**", "/running/coodinate/**", "/running/image/**", "/check", "/uploads/**",
                        		"/comment/**").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().authenticated());

        http
                .addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);
        http
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class);

        //세션 설정
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}

