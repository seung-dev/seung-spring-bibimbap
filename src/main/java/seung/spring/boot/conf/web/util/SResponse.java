package seung.spring.boot.conf.web.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import seung.java.kimchi.SDate;
import seung.java.kimchi.exception.SKimchiException;
import seung.java.kimchi.util.SLinkedHashMap;

@ApiModel(value = "Response model", description = "응답 객체")
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Setter
public class SResponse {

    @ApiModelProperty(
            dataType = "String"
            , value = "요청코드"
            , notes = ""
            , example = ""
            )
    @Builder.Default
    private String request_code = "";
    
    @ApiModelProperty(
            dataType = "String"
            , value = "오류코드"
            , notes = "0000: 성공, XXXX: 기타 오류"
            , example = "0000"
            )
    @Builder.Default
    private String error_code = "E999";
    
    @ApiModelProperty(
            dataType = "String"
            , value = "오류메시지"
            , notes = ""
            , example = "Required String parameter 'col01' is not present"
            )
    @Builder.Default
    private String error_message = "";
    
    @ApiModelProperty(
            dataType = "String"
            , value = "요청시간"
            , notes = "yyyy-MM-dd HH:mm:ss.SSSXXX"
            , example = "yyyy-MM-dd HH:mm:ss.SSSXXX"
            )
    @Builder.Default
    private String request_time = SDate.getDateString();
    
    @ApiModelProperty(
            dataType = "String"
            , value = "응답시간"
            , notes = "yyyy-MM-dd HH:mm:ss.SSSXXX"
            , example = "yyyy-MM-dd HH:mm:ss.SSSXXX"
            )
    @Builder.Default
    private String response_time = "";
    
    @ApiModelProperty(
            dataType = "Map"
            , value = "오청내용"
            , notes = "요청내용중 확인이 필요한 필드 반환"
            , example = "{\"request_code\": \"81967e1b-82b6-452e-808c-3bf544c3e10c\"}"
            )
    @Builder.Default
    private SLinkedHashMap data = new SLinkedHashMap();
    
    @ApiModelProperty(
            dataType = "Map"
            , value = "결과 내용"
            , notes = ""
            , example = "{}"
            )
    @Builder.Default
    private SLinkedHashMap result = new SLinkedHashMap();
    
    @SuppressWarnings("unchecked")
    public void putResult(Object key, Object value) {
        this.result.put(key, value);
    }
    
    public String toJsonString(boolean isPretty) throws SKimchiException {
        try {
            return new ObjectMapper()
                    .setSerializationInclusion(Include.ALWAYS)
                    .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                    .configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true)
                    .configure(SerializationFeature.INDENT_OUTPUT, isPretty)
                    .writeValueAsString(this)
                    ;
        } catch (JsonProcessingException e) {
            throw new SKimchiException(e);
        }
    }
    
    public void success() {
        this.error_code = "0000";
    }
    
}
