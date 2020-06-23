package seung.spring.boot.conf.web.support;

import java.net.URI;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import lombok.extern.slf4j.Slf4j;
import seung.spring.boot.conf.web.util.SRequest;

@Slf4j
public class SHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter methodParameter) {
		log.debug("run");
		return methodParameter.getParameterType().equals(SRequest.class);
	}// end of supportsParameter
	
	@Override
	public Object resolveArgument(
			MethodParameter		 methodParameter
			, ModelAndViewContainer modelAndViewContainer
			, NativeWebRequest	  nativeWebRequest
			, WebDataBinderFactory  webDataBinderFactory
			) throws Exception {
		
		log.debug("run");
		
		SRequest.SRequestBuilder sRequestBuilder = SRequest.builder();
		
		Object element = null;
		String key = null;
		String[] value = null;
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) nativeWebRequest.getNativeRequest();
		
		// network
		key = "remote_addr";
		String remote_addr = httpServletRequest.getHeader("X-Forwarded-For");
		if (remote_addr ==null || remote_addr.length() == 0 || "unknown".equalsIgnoreCase(remote_addr)) {
			remote_addr = httpServletRequest.getHeader("Proxy-Client-IP");
		}
		if (remote_addr ==null || remote_addr.length() == 0 || "unknown".equalsIgnoreCase(remote_addr)) {
			remote_addr = httpServletRequest.getHeader("WL-Proxy-Client-IP");
		}
		if (remote_addr ==null || remote_addr.length() == 0 || "unknown".equalsIgnoreCase(remote_addr)) {
			remote_addr = httpServletRequest.getHeader("HTTP_CLIENT_IP");
		}
		if (remote_addr ==null || remote_addr.length() == 0 || "unknown".equalsIgnoreCase(remote_addr)) {
			remote_addr = httpServletRequest.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (remote_addr ==null || remote_addr.length() == 0 || "unknown".equalsIgnoreCase(remote_addr)) {
			remote_addr = httpServletRequest.getRemoteAddr();
		}
		sRequestBuilder.putNetwork(key, remote_addr);
		key = "request_uri";
		sRequestBuilder.putNetwork(key, httpServletRequest.getRequestURI());
		key = "referer";
		sRequestBuilder.putNetwork(key, httpServletRequest.getHeader("referer") == null ? "" : new URI(httpServletRequest.getHeader("referer")).getPath());
		
		// parameters
		if(methodParameter.getParameterType().equals(SRequest.class)) {
			
			// header
			Enumeration<?> enumerations = httpServletRequest.getHeaderNames();
			while(enumerations.hasMoreElements()) {
				
				element = enumerations.nextElement();
				key = element == null ? "" : (String) element;
				
				sRequestBuilder.putHeader(key, httpServletRequest.getHeader(key));
				
			}// end of header
			
			// data
			enumerations  = httpServletRequest.getParameterNames();
			while(enumerations.hasMoreElements()) {
				
				element = enumerations.nextElement();
				key = element == null ? "" : (String) element;
				value = httpServletRequest.getParameterValues(key);
				
				sRequestBuilder.putData(key, 1 == value.length ? value[0] : value);
				
			}// end of data
			
//			// fowarding attributes
//			enumerations = httpServletRequest.getAttributeNames();
//			while(enumerations.hasMoreElements()) {
//				
//				key = (String) enumerations.nextElement();
//				
//				if(key.startsWith("_fw")) {
//					sRequestMap.putData(key, vals.length > 1 ? vals : vals[0]);
//					sRequestMap.putQuery(key.replaceAll("forward_", ""), httpServletRequest.getAttribute(key));
//					log.debug(String.format("[ATTRIBUTE] %s: %s", key, sRequestMap.getQuery().getString(key)));
//				}
//				
//			}
			
		}
		
//		// parameters - json
//		if("XMLHttpRequest".equals(httpServletRequest.getHeader("X-Requested-With"))) {
//			String json = IOUtils.toString(httpServletRequest.getInputStream(), "UTF-8");
//			logger.debug(String.format("[JSON] %s", json));
//			if(json != null && json.length() > 0) {
//				try {
//					sRequestMap.putQuery(new ObjectMapper().readValue(json, Map.class));
//				} catch (Exception e) {
//					sRequestMap.putQuery("error", "" + e);
//				}
//			}
//		}
		
		return sRequestBuilder.build();
		
	}// end of resolveArgument
	
}
