package seung.spring.boot.conf.support;

import java.io.IOException;
import java.util.UUID;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.swagger.annotations.ApiModelProperty;
import seung.java.kimchi.SStringU;
import seung.java.kimchi.util.SLinkedHashMap;

public class SRequestMapSwagger {

	@ApiModelProperty(
			dataType = "String"
			, value = "요청생성코드"
			, example = "81967e1b-82b6-452e-808c-3bf544c3e10c"
			)
	private UUID uuid = UUID.randomUUID();
	
	@ApiModelProperty(
			dataType = "SLinkedHashMap"
			, value = "네트워크 정보"
			, example = "{\"app_host\":\"127.0.0.1:11131\",\"host_type\":\"127\",\"host_name\":\"DESKTOP-5QCRF8K\",\"remote_addr\":\"127.0.0.1\",\"request_uri\":\"/rest/reflect\",\"referer_uri\":\"\"}"
			, allowEmptyValue = false
			)
	private SLinkedHashMap network = new SLinkedHashMap();
	@ApiModelProperty(
			dataType = "SLinkedHashMap"
			, value = "헤더 정보"
			, example = "{\"app_host\":\"127.0.0.1:11131\",\"host_type\":\"127\",\"host_name\":\"DESKTOP-5QCRF8K\",\"remote_addr\":\"127.0.0.1\",\"request_uri\":\"/rest/reflect\",\"referer_uri\":\"\"}"
			, allowEmptyValue = false
			)
	private SLinkedHashMap header = new SLinkedHashMap();
	@ApiModelProperty(
			dataType = "SLinkedHashMap"
			, value = "세션 정보"
			, example = "{\"host\":\"127.0.0.1:11131\",\"connection\":\"keep-alive\",\"upgrade-insecure-requests\":\"1\",\"user-agent\":\"Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko\",\"accept\":\"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\",\"sec-fetch-site\":\"none\",\"sec-fetch-mode\":\"navigate\",\"sec-fetch-user\":\"?1\",\"sec-fetch-dest\":\"document\",\"accept-encoding\":\"gzip, deflate, br\",\"accept-language\":\"ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7\",\"cookie\":\"JSESSIONID=EBDF3C1D202CB1CDC094E4659CF72455\"}"
			, allowEmptyValue = false
			)
	private SLinkedHashMap session = new SLinkedHashMap();
	@ApiModelProperty(
			dataType = "SLinkedHashMap"
			, value = "데이타 정보"
			, example = "{\"key1\":\"val1\",\"key2\":\"val2\"}"
			, allowEmptyValue = false
			)
	private SLinkedHashMap data = new SLinkedHashMap();
	
	public String getUUID() {
		return uuid.toString();
	}
	
	public SLinkedHashMap getNetwork() {
		return network;
	}
	public void putNetwork(String key, Object value) {
		network.put(key, value);
	}
	
	public SLinkedHashMap getHeader() {
		return header;
	}
	public void putHeader(String key, Object value) {
		header.put(key, value);
	}
	
	public SLinkedHashMap getSession() {
		return session;
	}
	public void putSession(String key, Object value) {
		session.put(key, value);
	}
	
	public SLinkedHashMap getData() {
		return data;
	}
	public void putData(String key, Object value) {
		data.put(key, value);
	}
	public SLinkedHashMap addData(String key, Object value) {
		data.put(key, value);
		return data;
	}
	public SLinkedHashMap addData(SLinkedHashMap sLinkedHashMap) {
		data.putAll(sLinkedHashMap);
		return data;
	}
	public SLinkedHashMap addData(String jsonString) throws JsonParseException, JsonMappingException, IOException {
		data.putJsonString(jsonString);
		return data;
	}
	public SLinkedHashMap putData(Object o) {
		data.putObject(o);
		return data;
	}
	
	/**
	 * @param isPretty
	 * @return
	 */
	public String toString(boolean isPretty) {
		try {
			return SStringU.toJson(this, isPretty);
		} catch (JsonProcessingException e) {
			return ExceptionUtils.getStackTrace(e);
		}
	}
	
}
