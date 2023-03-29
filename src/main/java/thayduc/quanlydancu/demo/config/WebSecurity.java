package thayduc.quanlydancu.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import thayduc.quanlydancu.demo.service.serviceIpm.UserSecurityService;
import thayduc.quanlydancu.demo.utility.SecurityUtility;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserSecurityService userSecurityService;

    private BCryptPasswordEncoder passwordEncoder() {
        return SecurityUtility.passwordEncoder();
    }

    private static final String[] PUBLIC_MATCHERS = {
            "/css/**",
            "/js/**",
            "/image/**",
            "/",
            "/assets/**",
            "/swagger-ui/**",
            "/javainuse-openapi/**",
            "/new-user",
            "/login",
            "/test/**",
            "/api/tim-kiem-dich-vu",
            "/api/tim-kiem-dan-cu",
            "/api/thong-ke/dich-vu-hoa-don"




    };
    private static final String[] PRIVATE_ADMIN={
            "/cudan/**",
            "/dichvu/**",
            "/quanly/**",



    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(PUBLIC_MATCHERS).permitAll()
                .antMatchers(PRIVATE_ADMIN).hasRole("ADMIN")
                .anyRequest().hasRole("USER");
        http
                .csrf().disable().cors().disable()
                .formLogin().failureUrl("/login?error")
                .loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/?logout");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
    }
}
