package seung.spring.boot.conf;

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
public class SSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment environment;
	
	@Override
	public void configure(WebSecurity webSecurity) throws Exception {
		log.debug("run");
		webSecurity.ignoring().antMatchers("/res/**");
		webSecurity.ignoring().antMatchers("/webjars/**");
	}// end of configure
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		log.debug("run");
		
		String       systemIp  = environment.getProperty("config.system.ip", "0:0:0:0:0:0:0:1,127.0.0.1");
		StringBuffer systemIps = new StringBuffer();
		String       serviceIp = environment.getProperty("config.service.ip", "");
		StringBuffer serviceIps = new StringBuffer();
		
		if(!"".equals(systemIp)) {
			for(String ip : systemIp.split(",")) {
				if(systemIps.length() > 0) {
					systemIps.append(" or ");
				}
				log.info("config.system.ip=", ip);
				systemIps.append(String.format("hasIpAddress('%s')", ip.trim()));
			}
		}
		
		if(!"".equals(serviceIp)) {
			for(String ip : serviceIp.split(",")) {
				if(serviceIps.length() > 0) {
					serviceIps.append(" or ");
				}
				log.info("config.service.ip=", ip);
				serviceIps.append(String.format("hasIpAddress('%s')", ip.trim()));
			}
		}
		
//		httpSecurity.cors().disable();
		httpSecurity.csrf().disable();
		
		if(systemIps.length() > 0) {
			httpSecurity.authorizeRequests()
				.antMatchers("/swagger-ui.html").permitAll()
				.antMatchers("/swagger-resources/**").permitAll()
				.antMatchers("/v2/**").permitAll()
				.antMatchers("/reflect").permitAll()
				.antMatchers("/rest/**").permitAll()
				.anyRequest().authenticated()
				;
		} else {
			httpSecurity.authorizeRequests()
				.antMatchers("/swagger-ui.html").permitAll()
				.antMatchers("/swagger-resources/**").permitAll()
				.antMatchers("/v2/**").permitAll()
				.antMatchers("/reflect").permitAll()
				.antMatchers("/rest/**").permitAll()
				.anyRequest().authenticated()
				;
		}
		
	}// end of configure
	
}
