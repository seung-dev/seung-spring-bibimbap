package seung.spring.bibimbap.eft;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;
import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.util.SLinkedHashMap;
import seung.java.kimchi.util.SRequestMap;
import seung.spring.bibimbap.eft.service.SEtfS;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "ETF API", value = "SEftC")
@Slf4j
@Controller
public class SEftC {

	@Resource(name="sEtfS")
	private SEtfS sEtfS;
	
	@ApiOperation(value = "ETF종목 목록조회", notes = "")
	@ApiResponses(value = {
			@ApiResponse(
					code = 200
					, response = SLinkedHashMap.class
					, message = "{\"errorCode\":\"0000: 정상, EXXX: 오류\",\"errorMessage\":\"오류메시지\",\"remoteAddress\":\"요청IP\",\"requestTime\":\"서버수신시간\",\"responseCode\":\"디버깅자료\",\"responseMessage\":\"디버깅자료\",\"exceptionMessage\":\"디버깅자료\",\"etfSL\":[{\"item_code\":\"종목코드\",\"item_name\":\"종목명\"}],\"responseTime\":\"서버응답시간\"}"
					, examples = @Example(value = {
							@ExampleProperty(
									mediaType = "application/json"
									, value = "{\"errorCode\":\"0000: 정상, EXXX: 오류\",\"errorMessage\":\"오류메시지\",\"remoteAddress\":\"요청IP\",\"requestTime\":\"서버수신시간\",\"responseCode\":\"디버깅자료\",\"responseMessage\":\"디버깅자료\",\"exceptionMessage\":\"디버깅자료\",\"etfSL\":[{\"item_code\":\"종목코드\",\"item_name\":\"종목명\"}],\"responseTime\":\"서버응답시간\"}"
									)})
					)
	})
	@RequestMapping(value = {"/rest/etf/etfSL"}, method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
	public String etfSL(
			Model model
			, @ApiIgnore SRequestMap sRequestMap
			, @ApiParam(
					type = "String"
					, allowEmptyValue = false
					, allowMultiple = false
					, example = "1df9ee2f-fe4b-4b1e-8e8f-d52c234b6202"
					, value = "요청코드"
					) @RequestParam String requestCode
			, @ApiParam(
					type = "String"
					, allowEmptyValue = true
					, allowMultiple = false
					, defaultValue = "0"
					, example = "1"
					, value = "디버깅여부"
					) @RequestParam String isDebug
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
			, @ApiIgnore SRequestMap sRequestMap
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
