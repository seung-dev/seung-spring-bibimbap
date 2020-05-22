package seung.spring.boot;

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
import seung.java.kimchi.util.SRequestMap;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "공통 API", value = "SCommC")
@Slf4j
@Controller
public class SApplicationC {

	@ApiOperation(response = SRequestMap.class, value = "접속 테스트", notes = "요청한 네트워크, 헤더, 세션, 쿼리 정보를 응답.")
	@ApiResponses(value = {
			@ApiResponse(
					code = 200
					, message = "{\"uuid\":\"1df9ee2f-fe4b-4b1e-8e8f-d52c234b6202\",\"network\":{\"app_host\":\"127.0.0.1:11131\",\"host_type\":\"127\",\"host_name\":\"DESKTOP-5QCRF8K\",\"remote_addr\":\"127.0.0.1\",\"request_uri\":\"/reflect\",\"referer_uri\":\"/swagger-ui.html\"},\"header\":{\"accept\":\"application/json;charset=UTF-8\",\"referer\":\"http://127.0.0.1:11131/swagger-ui.html\",\"accept-language\":\"ko-KR\",\"accept-encoding\":\"gzip, deflate\",\"user-agent\":\"Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko\",\"host\":\"127.0.0.1:11131\",\"connection\":\"Keep-Alive\",\"cookie\":\"JSESSIONID=F3C212E797A36E28BFB72005BFF535FB\"},\"session\":{},\"data\":{\"request_code\":\"1df9ee2f-fe4b-4b1e-8e8f-d52c234b6202\"}}"
					, examples = @Example(value = {
							@ExampleProperty(
									mediaType = "application/json"
									, value = "{\"uuid\":\"1df9ee2f-fe4b-4b1e-8e8f-d52c234b6202\",\"network\":{\"app_host\":\"127.0.0.1:11131\",\"host_type\":\"127\",\"host_name\":\"DESKTOP-5QCRF8K\",\"remote_addr\":\"127.0.0.1\",\"request_uri\":\"/reflect\",\"referer_uri\":\"/swagger-ui.html\"},\"header\":{\"accept\":\"application/json;charset=UTF-8\",\"referer\":\"http://127.0.0.1:11131/swagger-ui.html\",\"accept-language\":\"ko-KR\",\"accept-encoding\":\"gzip, deflate\",\"user-agent\":\"Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko\",\"host\":\"127.0.0.1:11131\",\"connection\":\"Keep-Alive\",\"cookie\":\"JSESSIONID=F3C212E797A36E28BFB72005BFF535FB\"},\"session\":{},\"data\":{\"request_code\":\"1df9ee2f-fe4b-4b1e-8e8f-d52c234b6202\"}}"
									)})
					)
	})
	@RequestMapping(value = {"/reflect"}, method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public String reflect(
			Model model
			, @ApiIgnore SRequestMap sRequestMap
			, @ApiParam(
					value = "요청코드"
					, type = "String"
					, allowEmptyValue = true
					, allowMultiple = false
					, example = "1df9ee2f-fe4b-4b1e-8e8f-d52c234b6202"
					) @RequestParam String request_code
			) throws Exception {
		
		log.debug("run");
		
		model.addAttribute("no-wrap", sRequestMap);
		
		return "jsonView";
	}
	
}
