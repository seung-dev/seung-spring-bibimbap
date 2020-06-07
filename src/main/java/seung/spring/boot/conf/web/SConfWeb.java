package seung.spring.boot.conf.web;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import lombok.extern.slf4j.Slf4j;
import seung.spring.boot.conf.web.support.SHandlerMethodArgumentResolver;
import seung.spring.boot.conf.web.support.SMappingJackson2JsonView;

@Slf4j
@Configuration
public class SConfWeb extends WebMvcConfigurationSupport {

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        log.info("run");
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(new SHandlerMethodArgumentResolver());
    }// end of addArgumentResolvers
    
    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        
        log.info("run");
        
        registry.addRedirectViewController("/swagger/v2/api-docs", "/v2/api-docs");
        registry.addRedirectViewController("/swagger/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui");
        registry.addRedirectViewController("/swagger/swagger-resources/configuration/security", "/swagger-resources/configuration/security");
        registry.addRedirectViewController("/swagger/swagger-resources", "/swagger-resources");
        registry.addRedirectViewController("/swagger/", "/swagger/swagger-ui.html");
        registry.addRedirectViewController("/swagger/csrf", "/csrf");
        registry.addRedirectViewController("/swagger", "/swagger/swagger-ui.html");
        
    }
    
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        
        log.info("run");
        
        registry.addResourceHandler("/res/**").addResourceLocations("classpath:/static/res/");
        log.info("addResourceHandler={}", "/res/**");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        log.info("addResourceHandler={}", "/webjars/**");
        registry.addResourceHandler("/swagger-resources/**").addResourceLocations("classpath:/META-INF/resources/");
        log.info("addResourceHandler={}", "/swagger-resources/**");
        registry.addResourceHandler("/swagger/**").addResourceLocations("classpath:/META-INF/resources/");
        log.info("addResourceHandler={}", "/swagger/swagger-ui.html");
        
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
    
}
