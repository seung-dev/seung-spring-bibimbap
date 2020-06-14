package seung.spring.boot.conf;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.commons.text.CaseUtils;
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
    @PropertySource(value = "classpath:s-swagger.properties")
    , @PropertySource(value = "classpath:s-mine.properties")
})
@ComponentScan(value = {"seung.spring"})
@Slf4j
@Configuration
public class SConfBoot {

    @SuppressWarnings("unchecked")
    @Bean(name = "sProperties")
    public SProperties sProperties(Environment environment) {
        
        log.debug("run");
        
        SProperties sProperties = SProperties.builder().build();
        try {
            
            Properties properties = Binder.get(environment).bind("", Bindable.of(Properties.class)).get();
            properties.put("host.name", InetAddress.getLocalHost().getHostName());
            
            Enumeration<String> propertyNames = (Enumeration<String>) properties.propertyNames();
            String              propertyName  = "";
            String              propertyValue = "";
            int                 propertyIndex = -1;
            while(propertyNames.hasMoreElements()) {
                propertyName  = propertyNames.nextElement();
                propertyValue = properties.getProperty(propertyName, "");
                if(propertyName.startsWith("seung.swagger")) {
                    sProperties.getSwagger().put(propertyName, propertyValue);
                } else if(propertyName.startsWith("seung.datasource")) {
                    propertyIndex = Integer.parseInt(propertyName.split("\\.")[2]);
                    if(sProperties.getDatasource().size() < propertyIndex + 1) {
                        sProperties.getDatasource().add(new Properties());
                    }
                    sProperties.getDatasource().get(propertyIndex).put(
                            CaseUtils.toCamelCase(propertyName.substring(propertyName.lastIndexOf(".") + 1), false, '-')
                            , propertyValue
                            );
                } else if(propertyName.startsWith("spring.jpa.properties")) {
                    sProperties.getJpa().put(propertyName.replace("spring.jpa.properties.", ""), propertyValue);
                } else if(propertyName.startsWith("spring.jpa")) {
                    sProperties.getJpaVendor().put(propertyName, propertyValue);
                } else if(propertyName.startsWith("spring.quartz.properties")) {
                    sProperties.getQuartz().put(propertyName.replace("spring.quartz.properties.", ""), propertyValue);
                } else if(propertyName.startsWith("seung.mine")) {
                    sProperties.getSeung().put(propertyName, propertyValue);
                } else {
                    sProperties.getEnvironment().put(propertyName, propertyValue);
                }
            }
            
        } catch (UnknownHostException e) {
            log.error("Failed to add configProperties.", e);
        }
        
        return sProperties;
        
    }// end of addConfigProperties
    
}
