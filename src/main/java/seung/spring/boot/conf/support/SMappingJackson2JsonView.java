package seung.spring.boot.conf.support;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SMappingJackson2JsonView extends MappingJackson2JsonView {

	private ArrayList<String> notWrappedModelKeys = new ArrayList<String>();
	
	public void addNotWrappedModelKeys(String key) {
		log.debug("run");
		if(!notWrappedModelKeys.contains(key)) {
			notWrappedModelKeys.add(key);
		}
	}// end of addNotWrappedModelKeys
	
	public void removeNotWrappedModelKeys(String key) {
		log.debug("run");
		notWrappedModelKeys.remove(key);
	}// end of removeNotWrappedModelKeys
	
	@Override
	protected Object filterModel(Map<String, Object> model) {
		log.debug("run");
		for(String key : notWrappedModelKeys) {
			if(model.containsKey(key)) {
				return model.get(key);
			}
		}
		return super.filterModel(model);
	}// end of filterModel
	
}
