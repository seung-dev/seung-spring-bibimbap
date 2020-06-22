package seung.spring.boot.conf.web.util;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.Builder;
import lombok.Getter;
import seung.java.kimchi.util.SKimchiException;
import seung.java.kimchi.util.SLinkedHashMap;

@Builder
@Getter
public class SRequest {

	private String uuid;
	
	private SLinkedHashMap network;
	
	private SLinkedHashMap header;
	
	private SLinkedHashMap session;
	
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
