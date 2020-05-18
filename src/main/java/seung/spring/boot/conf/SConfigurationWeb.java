package seung.spring.boot.conf;

import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.google.common.base.Predicates;

import lombok.extern.slf4j.Slf4j;
import seung.spring.boot.conf.support.SHandlerMethodArgumentResolver;
import seung.spring.boot.conf.support.SMappingJackson2JsonView;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@PropertySources({
	@PropertySource(value="classpath:s-application.properties")
//	, @PropertySource(value="classpath:ift-bridge.properties")
})
@Slf4j
@Configuration
public class SConfigurationWeb extends WebMvcConfigurationSupport {

	@Autowired
	private Properties configProperties;
	
	@Override
	protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		log.info("run");
		super.addArgumentResolvers(argumentResolvers);
		argumentResolvers.add(new SHandlerMethodArgumentResolver());
	}// end of addArgumentResolvers
	
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		log.info("run");
		registry.addResourceHandler("/res/**").addResourceLocations("classpath:/static/res/");
		log.info("addResourceHandler={}", "/res/**");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		log.info("addResourceHandler={}", "/webjars/**");
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		log.info("addResourceHandler={}", "swagger-ui.html");
	}// end of addResourceHandlers
	
	@Bean
	public ViewResolver addBeanNameViewResolver() {
		log.info("run");
		BeanNameViewResolver beanNameViewResolver = new BeanNameViewResolver();
		beanNameViewResolver.setOrder(1);
		return beanNameViewResolver;
	}// end of addBeanNameViewResolver
	
	@Bean(name="jsonView")
	public MappingJackson2JsonView addSMappingJackson2JsonView() {
		log.info("run");
		SMappingJackson2JsonView sMappingJackson2JsonView = new SMappingJackson2JsonView();
		sMappingJackson2JsonView.addNotWrappedModelKeys("no-wrap");
		log.info("addNotWrappedModelKeys={}", "no-wrap");
		return sMappingJackson2JsonView;
	}// end of addSMappingJackson2JsonView
	
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
