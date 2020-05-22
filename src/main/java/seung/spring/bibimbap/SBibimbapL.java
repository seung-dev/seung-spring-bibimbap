package seung.spring.bibimbap;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.SStringU;

/**
 * life cycle
 * @author stoas
 */
@Slf4j
@Component
public class SBibimbapL {

	@Resource(name="configProperties")
	private Properties configProperties;
	
	@PostConstruct
	public void postRun() {
		
		log.debug("run");
		
		try {
			log.info(SStringU.toJson(configProperties, true));
		} catch (JsonProcessingException e) {
			log.error("Failed to check configProperties.", true);
		}
		
	}
	
	@PreDestroy
	public void preStop() {
		log.debug("run");
	}
	
	
}
