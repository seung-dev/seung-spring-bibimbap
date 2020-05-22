package seung.spring.boot.conf;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import lombok.extern.slf4j.Slf4j;
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
public class SConfigurationSwagger {

	@Autowired
	private Properties configProperties;
	
	@Bean
	public Docket api() {
		
		String title       = configProperties.getProperty("config.swagger.title"      , configProperties.getProperty("spring.application.name", "title"));
		String description = configProperties.getProperty("config.swagger.description", "description");
		String version     = configProperties.getProperty("config.swagger.version"    , getClass().getPackage().getImplementationVersion());
		String name        = configProperties.getProperty("config.swagger.name"       , "name");
		String url         = configProperties.getProperty("config.swagger.url"        , "");
		String email       = configProperties.getProperty("config.swagger.email"      , "email");
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
//						.termsOfServiceUrl("alert('none');")
						.contact(new Contact(name, url, email))
						.license("Apache 2.0")
						.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
						.build()
						)
//				.host("https://x.x.x.x")
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(Predicates.or(PathSelectors.ant("/reflect"), PathSelectors.ant("/rest/**")))
				.build()
				.useDefaultResponseMessages(false)
				;
		
	}
	
}
