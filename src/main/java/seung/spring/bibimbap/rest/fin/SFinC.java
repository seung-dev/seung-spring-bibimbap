package seung.spring.bibimbap.rest.fin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;
import seung.spring.bibimbap.rest.fin.service.SFinS;
import seung.spring.boot.conf.web.util.SRequest;

@Slf4j
@Controller
public class SFinC {

	@Resource(name="sFinS")
	private SFinS sFinS;
	
	@RequestMapping(value = {"/rest/fin/d0101"}, method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
	public String finD0101(
			Model model
			, SRequest sRequest
			) throws Exception {
		
		log.debug("run");
		
//		model.addAttribute("no-wrap", sFinS.finD0101(sRequest));
		
		return "jsonView";
	}
	
	@RequestMapping(value = {"/rest/fin/n0101"}, method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
	public String finN0101(
			Model model
			, SRequest sRequest
			) throws Exception {
		
		log.debug("run");
		
		model.addAttribute("no-wrap", sFinS.finN0101(sRequest));
		
		return "jsonView";
	}
	
	@RequestMapping(value = {"/rest/fin/n0102"}, method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
	public String finN0102(
			Model model
			, SRequest sRequest
			) throws Exception {
		
		log.debug("run");
		
		model.addAttribute("no-wrap", sFinS.finN0102(sRequest));
		
		return "jsonView";
	}
	
}
