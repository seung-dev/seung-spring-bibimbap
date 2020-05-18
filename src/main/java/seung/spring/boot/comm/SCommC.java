package seung.spring.boot.comm;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
public class SCommC {

	@ApiOperation(response = SRequestMap.class, value = "접속 테스트", notes = "요청한 네트워크, 헤더, 세션, 쿼리 정보를 응답.")
	@ApiResponses(value = {
			@ApiResponse(
					code = 200
					, message = "{\"uuid\":\"96631cd1-0742-4014-a588-d6f4973ebd8a\",\"network\":{\"appHost\":\"127.0.0.1:11131\",\"hostType\":\"127\",\"hostName\":\"DESKTOP-5QCRF8K\",\"remoteAddr\":\"127.0.0.1\",\"requestURI\":\"/rest/reflect\",\"refererURI\":\"\"},\"header\":{\"host\":\"127.0.0.1:11131\",\"connection\":\"keep-alive\",\"upgrade-insecure-requests\":\"1\",\"user-agent\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36\",\"accept\":\"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\",\"sec-fetch-site\":\"none\",\"sec-fetch-mode\":\"navigate\",\"sec-fetch-user\":\"?1\",\"sec-fetch-dest\":\"document\",\"accept-encoding\":\"gzip, deflate, br\",\"accept-language\":\"ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7\",\"cookie\":\"JSESSIONID=EBDF3C1D202CB1CDC094E4659CF72455\"},\"session\":{},\"data\":{\"key1\":\"val1\",\"key2\":\"val2\"}}"
					, examples = @Example(value = {
							@ExampleProperty(
									mediaType = "application/json"
									, value = "{\"uuid\":\"1196631cd1-0742-4014-a588-d6f4973ebd8a\",\"network\":{\"appHost\":\"127.0.0.1:11131\",\"hostType\":\"127\",\"hostName\":\"DESKTOP-5QCRF8K\",\"remoteAddr\":\"127.0.0.1\",\"requestURI\":\"/rest/reflect\",\"refererURI\":\"\"},\"header\":{\"host\":\"127.0.0.1:11131\",\"connection\":\"keep-alive\",\"upgrade-insecure-requests\":\"1\",\"user-agent\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36\",\"accept\":\"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\",\"sec-fetch-site\":\"none\",\"sec-fetch-mode\":\"navigate\",\"sec-fetch-user\":\"?1\",\"sec-fetch-dest\":\"document\",\"accept-encoding\":\"gzip, deflate, br\",\"accept-language\":\"ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7\",\"cookie\":\"JSESSIONID=EBDF3C1D202CB1CDC094E4659CF72455\"},\"session\":{},\"data\":{\"key1\":\"val1\",\"key2\":\"val2\"}}"
									)})
					)
	})
	@RequestMapping(value = {"/reflect"}, method = {RequestMethod.GET}, produces = "application/json;charset=UTF-8")
	public String reflect(
			Model model
			, @ApiIgnore SRequestMap sRequestMap
			) throws Exception {
		
		log.debug("run");
		
		model.addAttribute("no-wrap", sRequestMap);
		
		return "jsonView";
	}
	
}
