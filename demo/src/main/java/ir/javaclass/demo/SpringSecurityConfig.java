package ir.javaclass.demo;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserRepository userRepository;

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       User admin  = userRepository.findByUsername("0912xxxxxxx");
       User user  = userRepository.findByUsername("0919yyyyyyy");

       auth.inMemoryAuthentication()
               .withUser(user.getUsername()).password("{noop}" + user.getPassword()).roles("USER")
               .and()
               .withUser(admin.getUsername()).password("{noop}" + admin.getPassword()).roles("USER", "ADMIN");

   }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/user/list").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/user/search").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/user/add").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/user/edit").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/user/delete").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/product/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/purchase/list").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/purchase/buy").hasRole("ADMIN")
                .and().csrf().disable()
                .formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password").permitAll().and().logout().permitAll();
    }
}