package com.mia.springboot.config.auth;

import com.mia.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정들을 활성화시켜줌
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() // h2-console 화면을 사용하기 위해 해당 옵션들을 disable
                .and()
                    .authorizeRequests() // URL별 권한관리를 설정하는 옵션의 시작점으로, 얘가 선언되어야만 antMatchers 옵션 사용 가능
                    // antMatchers : 권한 관리 대상 지정하는 옵션. URL, HTTP 메소드별 관리가 가능
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                    // "/" 등 지정된 URL들은 permitAll() 옵션을 통해 전체 열람 권한을 줌
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    // "/api/v1/**" 주소를 가진 API는 USER 권한을 가진 사람만 가능
                    .anyRequest().authenticated()
                    // 설정된 값들 이외 나머지 URL을 뜻하고 authenticated()를 추가하며 나머지 URL들은 모두 인증된 사용자들에게만 허용(로그인한 사용자들)
                .and()
                    // 로그아웃 기능에 대한 여러 설정의 진입점으로 로그아웃 성공시 / 주소로 이동한다.
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login() // OAuth2 로그인 기능에 대한 여러 설정의 진입점
                        .userInfoEndpoint() // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당
                            .userService(customOAuth2UserService);
    }
}
