package seung.spring.boot.conf.support;

import java.net.InetAddress;
import java.net.URI;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.util.SRequestMap;

@Slf4j
public class SHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter methodParameter) {
		log.debug("run");
		return methodParameter.getParameterType().equals(SRequestMap.class);
	}// end of supportsParameter
	
	@Override
	public Object resolveArgument(
			MethodParameter         methodParameter
			, ModelAndViewContainer modelAndViewContainer
			, NativeWebRequest      nativeWebRequest
			, WebDataBinderFactory  webDataBinderFactory
			) throws Exception {
		
		log.debug("run");
		
		SRequestMap sRequestMap = new SRequestMap();
		String      key         = "";
		String[]    vals        = null;
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) nativeWebRequest.getNativeRequest();
		
		// network
		key = "appHost";
		sRequestMap.putNetwork(key, httpServletRequest.getHeader("host"));
		log.debug(String.format("({}) network.%s: %s", sRequestMap.getUUID(), key, sRequestMap.getNetwork().getString(key)));
		key = "hostType";
		sRequestMap.putNetwork(key, httpServletRequest.getHeader("host").split("\\.")[0]);
		log.debug(String.format("({}) network.%s: %s", sRequestMap.getUUID(), key, sRequestMap.getNetwork().getString(key)));
		key = "hostName";
		sRequestMap.putNetwork(key, InetAddress.getLocalHost().getHostName());
		log.debug(String.format("({}) network.%s: %s", sRequestMap.getUUID(), key, sRequestMap.getNetwork().getString(key)));
		key = "remoteAddr";
		sRequestMap.putNetwork(key, (httpServletRequest.getHeader("X-FORWARDED-FOR") == null ? httpServletRequest.getRemoteAddr() : httpServletRequest.getHeader("X-FORWARDED-FOR")));
		log.debug(String.format("({}) network.%s: %s", sRequestMap.getUUID(), key, sRequestMap.getNetwork().getString(key)));
		key = "requestURI";
		sRequestMap.putNetwork(key, httpServletRequest.getRequestURI().replace("/WEB-INF/views", "").replace(".jsp", "").replace(httpServletRequest.getContextPath(), ""));
		log.debug(String.format("({}) network.%s: %s", sRequestMap.getUUID(), key, sRequestMap.getNetwork().getString(key)));
		key = "refererURI";
		sRequestMap.putNetwork(key, httpServletRequest.getHeader("referer") == null ? "" : new URI(httpServletRequest.getHeader("referer")).getPath());
		log.debug(String.format("({}) network.%s: %s", sRequestMap.getUUID(), key, sRequestMap.getNetwork().getString(key)));
		
		// parameters
		log.error(methodParameter.getParameterType().getName());
		if(methodParameter.getParameterType().equals(SRequestMap.class)) {
			
			// header
			Enumeration<?> enumerations = httpServletRequest.getHeaderNames();
			while(enumerations.hasMoreElements()) {
				
				key = (String) enumerations.nextElement();
				
				if(key instanceof String) {
					sRequestMap.putHeader(key, httpServletRequest.getHeader(key));
					log.debug(String.format("({}) header.%s: %s", sRequestMap.getUUID(), key, sRequestMap.getHeader().getString(key)));
				}
				
			}// end of header
			
			// data
			enumerations  = httpServletRequest.getParameterNames();
			while(enumerations.hasMoreElements()) {
				
				key = (String) enumerations.nextElement();
				
				if(!key.startsWith("_ss")) {
					vals = httpServletRequest.getParameterValues(key);
					if(vals != null) {
						sRequestMap.putData(key, vals.length > 1 ? vals : vals[0]);
						log.debug(String.format("({}) data.%s: %s", sRequestMap.getUUID(), key, sRequestMap.getData().getString(key)));
					}
				}
				
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
		
		return sRequestMap;
		
	}// end of resolveArgument
	
}
