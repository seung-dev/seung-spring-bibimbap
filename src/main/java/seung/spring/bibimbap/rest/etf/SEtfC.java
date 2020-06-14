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
import seung.spring.bibimbap.rest.etf.service.SEtfS;
import seung.spring.boot.conf.web.util.SRequest;
import seung.spring.boot.conf.web.util.SResponse;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "ETF API", value = "SEftC")
@Slf4j
@Controller
public class SEtfC {

    @Resource(name="sEtfS")
    private SEtfS sEtfS;
    
    @ApiOperation(value = "상장기업 고유번호 조회", notes = "")
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200
                    , response = SResponse.class
                    , message = ""
                    , examples = @Example(value = {
                            @ExampleProperty(
                                    mediaType = "application/json"
                                    , value = ""
                                    )})
                    )
    })
    @RequestMapping(value = {"/rest/etf/d0101"}, method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String etfD0101(
            Model model
            , @ApiIgnore SRequest sRequest
            , @ApiParam(
                    value = "요청코드"
                    , type = "String"
                    , allowEmptyValue = false
                    , allowMultiple = false
                    , example = "81967e1b-82b6-452e-808c-3bf544c3e10c"
                    ) @RequestParam String request_code
            ) throws Exception {
        
        log.debug("run");
        
        model.addAttribute("no-wrap", sEtfS.etfD0101(sRequest));
        
        return "jsonView";
    }
    
    @ApiOperation(value = "ETF종목 목록 조회", notes = "")
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200
                    , response = SResponse.class
                    , message = ""
                    , examples = @Example(value = {
                            @ExampleProperty(
                                    mediaType = "application/json"
                                    , value = ""
                                    )})
                    )
    })
    @RequestMapping(value = {"/rest/etf/n0101"}, method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String etfN0101(
            Model model
            , @ApiIgnore SRequest sRequest
            , @ApiParam(
                    value = "요청코드"
                    , type = "String"
                    , allowEmptyValue = false
                    , allowMultiple = false
                    , example = "c0abaab0-85fe-4440-aa9e-728276a06232"
                    ) @RequestParam String request_code
            ) throws Exception {
        
        log.debug("run");
        
        model.addAttribute("no-wrap", sEtfS.etfN0101(sRequest));
        
        return "jsonView";
    }
    
    @ApiOperation(value = "ETF종목 개요 조회", notes = "")
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200
                    , response = SResponse.class
                    , message = ""
                    , examples = @Example(value = {
                            @ExampleProperty(
                                    mediaType = "application/json"
                                    , value = ""
                                    )})
                    )
    })
    @RequestMapping(value = {"/rest/etf/n0102"}, method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String etfN0102(
            Model model
            , @ApiIgnore SRequest sRequest
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
        
        model.addAttribute("no-wrap", sEtfS.etfN0102(sRequest));
        
        return "jsonView";
    }
    
}
