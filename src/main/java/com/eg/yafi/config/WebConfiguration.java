package com.eg.yafi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private WebApplicationContext applicationContext;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsService userDetailsService;
//    @Autowired
//    private AuthenticationProvider authenticationProvider;

//    @Autowired
//    public WebConfiguration(DataSource dataSource) {
//      super();
//      this.dataSource = dataSource;
//
//    }

//    @PostConstruct
//    public void completeSetup() {
//        userDetailsService = applicationContext.getBean(CustomUserDetailsService.class);
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/topic", "/thread", "/login").hasAnyRole("USER", "ADMIN")
            .anyRequest().permitAll()
            .and()
            .httpBasic()
            .and()
            .csrf().disable(); //TODO: FIXME


//        http
//            .authorizeRequests()
//            .anyRequest().authenticated()
//            .and()
//            .httpBasic();
    }

//    @Autowired
//    public void initialize(AuthenticationManagerBuilder builder, DataSource dataSource) throws Exception {
//        builder.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("select username, password, enabled from app_user where username=?")
//                .authoritiesByUsernameQuery("select username, role FROM app_user where username=?")
//                //.authoritiesByUsernameQuery("select (select username, role_id FROM app_user where username=?) AS TEMP")
//                .passwordEncoder(new BCryptPasswordEncoder());
//    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
                //.authenticationProvider(authenticationProvider())
                .userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
//                .and()
//                .jdbcAuthentication()
//                //.and()
//                //.()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("select username, password, enabled from app_user where username=?")
//                .authoritiesByUsernameQuery("select username, role FROM app_user where username=?")
//                //.authoritiesByUsernameQuery("select (select username, role_id FROM app_user where username=?) AS TEMP")
//                .passwordEncoder(new BCryptPasswordEncoder());
    }

//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception{
////        auth
////                .jdbcAuthentication()//
////                .dataSource(dataSource)
////                .withDefaultSchema()
////                .withUser(users.username("user").password("user").roles("USER"))
////                .withUser(users.username("admin").password("password").roles("USER","ADMIN"));
//
//        auth
//            //.authenticationProvider(authenticationProvider())
//            .userDetailsService(userDetailsService)
//            .passwordEncoder(new BCryptPasswordEncoder())
//                .and()
//                .jdbcAuthentication()
//            //.and()
//            //.()
//            .dataSource(dataSource)
//            .usersByUsernameQuery("select username, password, enabled from app_user where username=?")
//            .authoritiesByUsernameQuery("select username, role FROM app_user where username=?")
//            .authoritiesByUsernameQuery("select (select username, role_id FROM app_user where username=?) AS TEMP")
//            .passwordEncoder(new BCryptPasswordEncoder());
//    }

//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService);
//        authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
//        return authProvider;
//    }


    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
      /*  config.*/
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

}
