package com.example.springsocialfacebooksecondarylogin.config;


import com.example.springsocialfacebooksecondarylogin.security.FacebookConnectionSignup;
import com.example.springsocialfacebooksecondarylogin.security.FacebookSignInAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInController;


@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = { "com.example.springsocialfacebooksecondarylogin.security" })
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    @Qualifier("myUserDetailsService")
    @Autowired
    private  UserDetailsService userDetailsService;
    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;
    @Autowired
    private  UsersConnectionRepository usersConnectionRepository;
    @Autowired
    private  FacebookConnectionSignup facebookConnectionSignup;



    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // @formatter: off
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login*","/signin/**","/signup/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout();
    }//@formatter:on

//    @Primary
    @Bean
    public ProviderSignInController providerSignInController() {
        usersConnectionRepository.setConnectionSignUp(facebookConnectionSignup);
        return new ProviderSignInController(connectionFactoryLocator, usersConnectionRepository, new FacebookSignInAdapter());
    }

}
