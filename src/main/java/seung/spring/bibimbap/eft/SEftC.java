package seung.spring.bibimbap.eft;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.util.SLinkedHashMap;
import seung.spring.bibimbap.eft.service.SEtfS;
import seung.spring.boot.conf.support.SRequestMapSwagger;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "ETF API", value = "SEftC")
@Slf4j
@Controller
public class SEftC {

	@Resource(name="sEtfS")
	private SEtfS sEtfS;
	
	@ApiOperation(value = "ETF종목 목록조회", notes = "")
	@ApiResponses(value = {
			@ApiResponse(code = 200, response = SLinkedHashMap.class, message = "")
	})
	@RequestMapping(value = {"/rest/etf/etfSL"}, method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
	public String etfSL(
			Model model
			, @ApiIgnore SRequestMapSwagger sRequestMap
			) throws Exception {
		
		log.debug("run");
		
		model.addAttribute("no-wrap", sEtfS.etfSL(sRequestMap));
		
		return "jsonView";
	}
	
	@ApiOperation(value = "ETF종목 단일조회", notes = "")
	@ApiResponses(value = {
			@ApiResponse(code = 200, response = SLinkedHashMap.class, message = "")
	})
	@RequestMapping(value = {"/rest/etf/etfSR/{item_code}"}, method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
	public String etfSR(
			Model model
			, @ApiIgnore SRequestMapSwagger sRequestMap
			, @ApiParam(
					type = "String"
					, allowEmptyValue = false
					, allowMultiple = false
					, example = "069500"
					, value = "종목코드"
					) @PathVariable String item_code
			) throws Exception {
		
		log.debug("run");
		
		model.addAttribute("no-wrap", sEtfS.etfSR(sRequestMap));
		
		return "jsonView";
	}
	
}
