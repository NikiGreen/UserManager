package com.system.usermanager.config;

import com.system.usermanager.model.parametr.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
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
                /*.and()
                    .httpBasic().
                and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);*/
    }

    @Override
    public void configure (WebSecurity web)  {
        web.ignoring(). antMatchers ("/css/** ", "/js/ **","/styles/**","/background/**");
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                /*.passwordEncoder(NoOpPasswordEncoder.getInstance())*/
                .passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery("select username, password, active from usr where username=?")
                .authoritiesByUsernameQuery("select u.username, ur.roles from usr u inner join user_role ur on u.id = ur.user_id where u.username=?");
    }
}
