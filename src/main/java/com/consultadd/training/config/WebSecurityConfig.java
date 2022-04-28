package com.consultadd.training.config;
import com.consultadd.training.model.Employee;
import com.consultadd.training.util.JwtRequestFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.consultadd.training.service.EmployeeDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{

//    @Autowired
//    EmployeeDetailsService employeeDetailsService;
@Autowired
private JwtRequestFilter jwtRequestFilter;
    @Autowired
    private EmployeeDetailsService myUserDetailsService;
    @Autowired
    UserDetailsService userDetailsService;
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
//        auth.inMemoryAuthentication()
//                .withUser("user1")
//                .password("pass");
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    protected void configure(HttpSecurity http) throws Exception
    {
//        http.csrf().disable().cors();
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();
        http.authorizeRequests()
                .antMatchers("/home").hasAuthority("USER")
                .antMatchers("/create").hasAuthority("ADMIN")
                .antMatchers("/delete/**").hasAuthority("ADMIN")
                .antMatchers("/get").hasAuthority("ADMIN")
                .antMatchers("/update").hasAuthority("ADMIN")
                .and().csrf().disable()
                .formLogin();

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
