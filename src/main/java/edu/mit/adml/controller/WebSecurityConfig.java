package edu.mit.adml.controller;


import edu.mit.adml.properties.PropertiesConfigurationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired private SecurityProperties securityProperties;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*        http.
        authorizeRequests()
                .antMatchers("/", "/add**","/singleitem**", "/results").hasRole("USER").and().formLogin()
                .loginPage("/login").and().logout().permitAll();*/


        //http.requiresChannel().antMatchers("/").requiresSecure();

        //http.requiresChannel().anyRequest().requiresSecure();
        http.portMapper().http(8080).mapsTo(443);
        http.portMapper().http(80).mapsTo(443);



    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        final String user = PropertiesConfigurationUtil.getCredentials().getUsername_app();
        final String password = PropertiesConfigurationUtil.getCredentials().getPassword_app();
        auth
                .inMemoryAuthentication()
                .withUser(user).password(password).roles("USER");
    }



}
