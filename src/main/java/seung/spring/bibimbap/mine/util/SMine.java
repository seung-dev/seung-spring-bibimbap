package seung.spring.bibimbap.mine.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import seung.java.kimchi.exception.SKimchiException;
import seung.java.kimchi.util.SLinkedHashMap;

@Builder
@Getter
@Setter
public class SMine {

    @Builder.Default
    private String requestCode = "";
    
    @Builder.Default
    private String errorCode = "E999";
    
    @Builder.Default
    private String errorMessage = "";
    
    @Builder.Default
    private SLinkedHashMap data = new SLinkedHashMap();
    
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
    
}
