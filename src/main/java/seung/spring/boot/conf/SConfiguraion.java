package seung.spring.boot.conf;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

import lombok.extern.slf4j.Slf4j;

@PropertySources({
	@PropertySource(value="classpath:s-application.properties")
})
@ComponentScan({"seung"})
@Slf4j
@Configuration
public class SConfiguraion {

	@Autowired
	private Environment environment;
	
	@SuppressWarnings("unchecked")
	@Bean(name = "configProperties")
	public Properties addConfigProperties() {
		
		log.debug("run");
		
		Properties properties = null;
		try {
			
			properties = Binder.get(environment).bind("", Bindable.of(Properties.class)).get();
			properties.setProperty("config.hostname", InetAddress.getLocalHost().getHostName());
			
			Enumeration<String> propertyNames = (Enumeration<String>) properties.propertyNames();
			String              propertyName  = "";
			while(propertyNames.hasMoreElements()) {
				propertyName = propertyNames.nextElement();
				if("".equals(properties.getProperty(propertyName, ""))) {
					properties.remove(propertyName);
				}
			}
		} catch (UnknownHostException e) {
			log.error("Failed to add configProperties.", e);
		}
		
		return properties;
	}// end of addConfigProperties
	
}
