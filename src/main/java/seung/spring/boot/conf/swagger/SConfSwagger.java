package seung.spring.boot.conf.swagger;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import lombok.extern.slf4j.Slf4j;
import seung.spring.boot.conf.SProperties;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Slf4j
@Configuration
public class SConfSwagger {

    @Bean
    public Docket api(SProperties sProperties) {
        
        Properties properties = sProperties.getSwagger();
        
        String title       = properties.getProperty("config.swagger.title"      , properties.getProperty("spring.application.name", "title"));
        String description = properties.getProperty("config.swagger.description", "description");
        String version     = properties.getProperty("config.swagger.version"    , getClass().getPackage().getImplementationVersion());
        String name        = properties.getProperty("config.swagger.name"       , "name");
        String url         = properties.getProperty("config.swagger.url"        , "");
        String email       = properties.getProperty("config.swagger.email"      , "email");
        log.info("swagger.title={}"      , title);
        log.info("swagger.description={}", description);
        log.info("swagger.version={}"    , version);
        log.info("swagger.name={}"       , name);
        log.info("swagger.url={}"        , url);
        log.info("swagger.email={}"      , email);
        
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title(title)
                        .description(description)
                        .version(version)
//                        .termsOfServiceUrl("alert('none');")
                        .contact(new Contact(name, url, email))
                        .license("Apache 2.0")
                        .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                        .build()
                        )
//                .host("https://x.x.x.x")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.or(PathSelectors.ant("/reflect"), PathSelectors.ant("/rest/**")))
                .build()
                .useDefaultResponseMessages(false)
                ;
        
    }
    
}
