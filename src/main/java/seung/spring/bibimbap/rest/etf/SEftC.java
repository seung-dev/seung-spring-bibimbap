package seung.spring.bibimbap.rest.etf;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import seung.spring.bibimbap.rest.etf.service.SEtfS;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "ETF API", value = "SEftC")
@Slf4j
@Controller
public class SEftC {

	@Resource(name="sEtfS")
	private SEtfS sEtfS;
	
	@ApiOperation(value = "ETF종목 목록 개요 조회", notes = "")
	@ApiResponses(value = {
			@ApiResponse(
					code = 200
					, response = SLinkedHashMap.class
					, message = ""
					, examples = @Example(value = {
							@ExampleProperty(
									mediaType = "application/json"
									, value = ""
									)})
					)
	})
	@RequestMapping(value = {"/rest/etf/N0100"}, method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
	public String etfN0100(
			Model model
			, @ApiIgnore SRequestMap sRequestMap
			, @ApiParam(
					value = "요청코드"
					, type = "String"
					, allowEmptyValue = false
					, allowMultiple = false
					, example = "4c19b31c-63e6-439e-8012-6e809b19f14c"
					) @RequestParam String request_code
			) throws Exception {
		
		log.debug("run");
		
		model.addAttribute("no-wrap", sEtfS.etfN0100(sRequestMap));
		
		return "jsonView";
	}
	
	@ApiOperation(value = "ETF종목 목록 조회", notes = "")
	@ApiResponses(value = {
			@ApiResponse(
					code = 200
					, response = SLinkedHashMap.class
					, message = "{\"error_code\":\"0000: 정상, EXXX: 오류\",\"error_message\":\"오류메시지\",\"remote_address\":\"요청IP\",\"request_time\":\"서버수신시간\",\"response_code\":\"디버깅자료\",\"response_message\":\"디버깅자료\",\"exception_message\":\"디버깅자료\",\"etf0000\":[{\"item_code\":\"종목코드\",\"item_name\":\"종목명\"}],\"response_time\":\"서버응답시간\"}"
					, examples = @Example(value = {
							@ExampleProperty(
									mediaType = "application/json"
									, value = "{\"error_code\":\"0000: 정상, EXXX: 오류\",\"error_message\":\"오류메시지\",\"remote_address\":\"요청IP\",\"request_time\":\"서버수신시간\",\"response_code\":\"디버깅자료\",\"response_message\":\"디버깅자료\",\"exception_message\":\"디버깅자료\",\"etf0000\":[{\"item_code\":\"종목코드\",\"item_name\":\"종목명\"}],\"response_time\":\"서버응답시간\"}"
									)})
					)
	})
	@RequestMapping(value = {"/rest/etf/N0101"}, method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
	public String etfN0101(
			Model model
			, @ApiIgnore SRequestMap sRequestMap
			, @ApiParam(
					value = "요청코드"
					, type = "String"
					, allowEmptyValue = false
					, allowMultiple = false
					, example = "c0abaab0-85fe-4440-aa9e-728276a06232"
					) @RequestParam String request_code
			) throws Exception {
		
		log.debug("run");
		
		model.addAttribute("no-wrap", sEtfS.etfN0101(sRequestMap));
		
		return "jsonView";
	}
	
	@ApiOperation(value = "ETF종목 개요 조회", notes = "")
	@ApiResponses(value = {
			@ApiResponse(code = 200, response = SLinkedHashMap.class, message = "")
	})
	@RequestMapping(value = {"/rest/etf/N0102"}, method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
	public String etfN0102(
			Model model
			, @ApiIgnore SRequestMap sRequestMap
			, @ApiParam(
					value = "요청코드"
					, type = "String"
					, allowEmptyValue = false
					, allowMultiple = false
					, example = "ac44927e-173f-47a3-b1e2-5528485f8238"
					) @RequestParam String request_code
			, @ApiParam(
					value = "종목코드"
					, type = "String"
					, allowEmptyValue = false
					, allowMultiple = false
					, example = "069500"
					) @RequestParam String item_code
			) throws Exception {
		
		log.debug("run");
		
		model.addAttribute("no-wrap", sEtfS.etfN0102(sRequestMap));
		
		return "jsonView";
	}
	
}
