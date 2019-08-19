package com.system.usermanager.config;

import com.system.usermanager.model.param.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String SELECT_USER_BY_USERNAME = "SELECT username, password, active FROM usr WHERE username=?";
    private static final String SELECT_AUTHORITIES_BY_USERNAME = "SELECT u.username, ur.roles FROM usr u INNER JOIN user_role ur on u.id = ur.user_id WHERE u.username=?";

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/user/new").hasAuthority(String.valueOf(Role.ADMIN))
                .antMatchers("/user/{id}/edit").hasAuthority(String.valueOf(Role.ADMIN))
                .antMatchers("/delete").hasAuthority(String.valueOf(Role.ADMIN))
                .antMatchers("/", "/registration").permitAll()
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/** ", "/js/ **", "/styles/**", "/background/**");
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery(SELECT_USER_BY_USERNAME)
                .authoritiesByUsernameQuery(SELECT_AUTHORITIES_BY_USERNAME);
    }
}
