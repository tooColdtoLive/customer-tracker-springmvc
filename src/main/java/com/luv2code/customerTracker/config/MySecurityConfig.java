package com.luv2code.customerTracker.config;

import com.luv2code.customerTracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {

    @Autowired
    private DataSource securityDataSource;

    @Autowired
    private UserService userService;

    @Bean
    public JdbcUserDetailsManager userDetailsService() {
        /*UserDetails theEmployee = User.withUsername("employee")
                .password("{noop}employee").roles("EMPLOYEE").build();
        UserDetails theManager = User.withUsername("manager")
                .password("{noop}manager").roles("EMPLOYEE", "MANAGER").build();
        UserDetails theAdmin = User.withUsername("admin")
                .password("{noop}admin").roles("EMPLOYEE", "ADMIN").build();
        UserDetails tester = User.withUsername("tester")
                .password("{noop}tester").roles("EMPLOYEE", "MANAGER", "ADMIN").build();
        UserDetails none = User.withUsername("none")
                .password("{noop}none").roles().build();
        return new InMemoryUserDetailsManager(theAdmin,theManager,theEmployee, tester, none);*/

        return new JdbcUserDetailsManager(securityDataSource);
    }

    @Bean
    @Order(1)
    public SecurityFilterChain mvcFilterChain(HttpSecurity http) throws Exception {    // HttpSecurity used to config spring security
        // spring security default enables CSRF protection by filtering
        // to enable the protection, use POST instead of GET, and include CSRF token, which form:form tag does that automatically
        // CSRF protection should always be enabled unless for non-browser client after thorough consideration, .csrf().disable()
        // CSRF = pronounced as C-SURF, attack tricking user to perform unwanted action, e.g. by using the user's logged cookie/token
        // XSS = attack by injecting malicious script, action totally unlimited, unlike CSRF

        http.cors().and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
                .authorizeHttpRequests((authorize) -> // authorizeHttpRequests to restrict access based on HttpServletRequest
                        authorize
                                .antMatchers("/resources/**")
                                .permitAll()
                                .antMatchers("/TestDbServlet")
                                .permitAll()
                                .antMatchers("/user/registration/**")
                                .permitAll()
                                .anyRequest().authenticated()  // for any request, must be authenticated
                )
//                .httpBasic(withDefaults()); // for showing the browser default login form (as pop-up)
                .formLogin(form -> form.loginPage("/login")     // actual url with form for user input
                        .loginProcessingUrl("/login/authenticate")   // process the data from the form above, must POST
                                                                    // no controller request mapping needed, also no coding
                        .permitAll()      // allow everyone to see login page
                )
                .logout().permitAll()  // expose the default /logout url , handled by spring security, no coding needed
                                        // invalidate HTTP session, remove session cookies, redirect to login page with logout as param
                .and()
                .exceptionHandling()
                .accessDeniedPage("/accessDenied"); // the url will not be displayed but only the content, e.g. /admin with /accessDenied content

        return http.build();
    }

    // TODO configure API security usage, alternative way: to build a separate API app
    @Bean
    @Order(2)
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {    // HttpSecurity used to config spring security
        // spring security default enables CSRF protection by filtering
        // to enable the protection, use POST instead of GET, and include CSRF token, which form:form tag does that automatically
        // CSRF protection should always be enabled unless for non-browser client after thorough consideration, .csrf().disable()
        // CSRF = pronounced as C-SURF, attack tricking user to perform unwanted action, e.g. by using the user's logged cookie/token
        // XSS = attack by injecting malicious script, action totally unlimited, unlike CSRF

        http.authorizeHttpRequests((authorize) -> // authorizeHttpRequests to restrict access based on HttpServletRequest
                        authorize
                                .antMatchers(HttpMethod.GET, "/api/customers").hasRole("EMPLOYEE")
                                .antMatchers(HttpMethod.GET, "/api/customers/**").hasRole("EMPLOYEE")
                                .antMatchers(HttpMethod.POST, "/api/customers").hasAnyRole("MANAGER", "ADMIN")
                                .antMatchers(HttpMethod.POST, "/api/customers/**").hasAnyRole("MANAGER", "ADMIN")
                                .antMatchers(HttpMethod.PUT, "/api/customers").hasAnyRole("MANAGER", "ADMIN")
                                .antMatchers(HttpMethod.PUT, "/api/customers/**").hasAnyRole("MANAGER", "ADMIN")
                                .antMatchers(HttpMethod.DELETE, "/api/customers/**").hasRole("ADMIN")
                )
                .httpBasic()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/ignore1", "/ignore2");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
}
