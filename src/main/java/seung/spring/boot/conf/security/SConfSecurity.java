package seung.spring.boot.conf.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
public class SConfSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment environment;
    
    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        log.debug("run");
        webSecurity.ignoring().antMatchers("/res/**");
        webSecurity.ignoring().antMatchers("/webjars/**");
        webSecurity.ignoring().antMatchers("/swagger-resources/**");
        webSecurity.ignoring().antMatchers("/swagger/swagger-resources/**");
        webSecurity.ignoring().antMatchers("/swagger/swagger-ui.html");
    }// end of configure
    
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        
        log.debug("run");
        
        String       systemIp  = environment.getProperty("seung.security.allow.ip", "127.0.0.1");
        StringBuffer systemIps = new StringBuffer();
        
        if(!"".equals(systemIp)) {
            for(String ip : systemIp.split(",")) {
                if(systemIps.length() > 0) {
                    systemIps.append(" or ");
                }
                log.info("config.system.ip=", ip);
                systemIps.append(String.format("hasIpAddress('%s')", ip.trim()));
            }
        }
        
//        httpSecurity.cors().disable();
        httpSecurity.csrf().disable();
//        httpSecurity.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        
        if(systemIps.length() > 0) {
            httpSecurity.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/reflect").permitAll()
                .antMatchers("/rest/**").access(systemIps.toString())
                .antMatchers("/v2/**").access(systemIps.toString())
                .antMatchers("/swagger/**").access(systemIps.toString())
                .antMatchers("/rest/**").access(systemIps.toString())
                .anyRequest().authenticated()
                ;
        } else {
            httpSecurity.authorizeRequests()
                .anyRequest().permitAll()
                ;
        }
        
    }// end of configure
    
}
