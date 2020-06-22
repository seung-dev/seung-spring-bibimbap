package seung.spring.boot.conf.web.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.method.annotation.MapMethodProcessor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SRequestMappingHandlerAdapter extends RequestMappingHandlerAdapter {

	@Override
	public void afterPropertiesSet() {
		
		log.debug("run");
		
		super.afterPropertiesSet();
		
		if(getArgumentResolvers() != null) {
			
			List<HandlerMethodArgumentResolver> dafaultHandlerMethodArgumentResolvers = new ArrayList<HandlerMethodArgumentResolver>(getArgumentResolvers());
			List<HandlerMethodArgumentResolver> sHandlerMethodArgumentResolvers = new ArrayList<HandlerMethodArgumentResolver>();
			
			int size = dafaultHandlerMethodArgumentResolvers.size();
			for(int i = 0; i < size; i++) {
				if(dafaultHandlerMethodArgumentResolvers.get(i) instanceof MapMethodProcessor) {
					sHandlerMethodArgumentResolvers.add(new SHandlerMethodArgumentResolver());
					sHandlerMethodArgumentResolvers.add(dafaultHandlerMethodArgumentResolvers.get(i));
				} else {
					sHandlerMethodArgumentResolvers.add(dafaultHandlerMethodArgumentResolvers.get(i));
				}
			}
			
//			if(mapMethodProcessor != null) {
//				for(int i = 1; i < size; i++) {
//					if(handlerMethodArgumentResolvers.get(i) instanceof SHandlerMethodArgumentResolver) {
//						if(i + 1 < size) {
//							handlerMethodArgumentResolvers.add(i + 1, mapMethodProcessor);
//						} else {
//							handlerMethodArgumentResolvers.add(i, mapMethodProcessor);
//						}
//						break;
//					}
//				}
//			}
			
			setArgumentResolvers(sHandlerMethodArgumentResolvers);
			
		}//end of getArgumentResolvers check
		
	}//end of afterPropertiesSet
	
}
