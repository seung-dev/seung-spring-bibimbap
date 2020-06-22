package seung.spring.boot.conf.web.util;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import seung.java.kimchi.SDate;
import seung.java.kimchi.util.SKimchiException;
import seung.java.kimchi.util.SLinkedHashMap;
import seung.java.kimchi.util.SCode;

@Builder
@Getter
@Setter
public class SResponse {

	@NotNull
	private String request_code;
	
	@NotNull
	private String error_code;
	
	@Builder.Default
	private String error_message = "";
	
	@Builder.Default
	private String request_time = SDate.getDateString();
	
	@Builder.Default
	private String response_time = "";
	
	@Builder.Default
	private SLinkedHashMap data = new SLinkedHashMap();
	
	@Builder.Default
	private SLinkedHashMap result = new SLinkedHashMap();
	
	@SuppressWarnings("unchecked")
	public void putResult(Object key, Object value) {
		this.result.put(key, value);
	}
	
	public void success() {
		this.error_code = SCode.SUCCESS;
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
			return String.format("{\"errorMessage\":\"%s\"}", ExceptionUtils.getStackTrace(e));
		}
	}
	
}
