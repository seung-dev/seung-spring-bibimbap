package seung.spring.boot.conf.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;
import seung.spring.boot.conf.web.util.SRequest;

@Slf4j
@Controller
public class SController {

	@RequestMapping(value = {"/reflect"}, method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public String reflect(
			Model model
			, SRequest sRequest
			) throws Exception {
		
		log.debug("run");
		
		model.addAttribute("no-wrap", sRequest);
		
		return "jsonView";
	}
	
}
