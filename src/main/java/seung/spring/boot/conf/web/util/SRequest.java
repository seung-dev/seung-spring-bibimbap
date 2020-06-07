package seung.spring.boot.conf.web.util;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import seung.java.kimchi.exception.SKimchiException;
import seung.java.kimchi.util.SLinkedHashMap;

@ApiModel(value = "Request model", description = "요청 객체")
@Builder
@Getter
public class SRequest {

    @ApiModelProperty(
            dataType = "String"
            , value = "요청생성코드"
            , example = "81967e1b-82b6-452e-808c-3bf544c3e10c"
            )
    private String uuid;
    
    @ApiModelProperty(
            dataType = "Map"
            , value = "네트워크 정보"
            , example = "{\"remote_addr\": \"요청지 주소\",\"request_uri\": \"요청 경로\",\"referer\": \"요청지 경로\"}"
            , allowEmptyValue = false
            )
    private SLinkedHashMap network;
    
    @ApiModelProperty(
            dataType = "Map"
            , value = "헤더 정보"
            , example = "{\"host\": \"127.0.0.1:11130\",\"connection\": \"keep-alive\",\"accept\": \"application/json;charset=UTF-8\",\"user-agent\": \"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36\",\"sec-fetch-site\": \"same-origin\",\"sec-fetch-mode\": \"cors\",\"sec-fetch-dest\": \"empty\",\"referer\": \"http://127.0.0.1:11130/swagger/swagger-ui.html\",\"accept-encoding\": \"gzip, deflate, br\",\"accept-language\": \"ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7\"}"
            , allowEmptyValue = false
            )
    private SLinkedHashMap header;
    
    @ApiModelProperty(
            dataType = "Map"
            , value = "세션 정보"
            , example = "{}"
            , allowEmptyValue = false
            )
    private SLinkedHashMap session;
    
    @ApiModelProperty(
            dataType = "Map"
            , value = "요청 내용"
            , example = "{\"request_code\": \"1df9ee2f-fe4b-4b1e-8e8f-d52c234b6202\"}"
            , allowEmptyValue = false
            )
    private SLinkedHashMap data;
    
    public static class SRequestBuilder {
        private String uuid = UUID.randomUUID().toString();
        private SLinkedHashMap network = new SLinkedHashMap();
        private SLinkedHashMap header = new SLinkedHashMap();
        private SLinkedHashMap session = new SLinkedHashMap();
        private SLinkedHashMap data = new SLinkedHashMap();
        @SuppressWarnings("unchecked")
        public void putNetwork(Object key, Object value) {
            this.network.put(key, value);
        }
        @SuppressWarnings("unchecked")
        public void putHeader(Object key, Object value) {
            this.header.put(key, value);
        }
        @SuppressWarnings("unchecked")
        public void putSession(Object key, Object value) {
            this.session.put(key, value);
        }
        @SuppressWarnings("unchecked")
        public void putData(Object key, Object value) {
            this.data.put(key, value);
        }
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
    
}
