package seung.spring.bibimbap.eft.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.SDateU;
import seung.java.kimchi.exception.SCastException;
import seung.java.kimchi.http.SHttpU;
import seung.java.kimchi.http.SRequestV;
import seung.java.kimchi.http.SResponseV;
import seung.java.kimchi.util.SLinkedHashMap;
import seung.java.kimchi.util.SRequestMap;

@Slf4j
@Service("sEtfS")
public class SEftSI implements SEtfS {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public SLinkedHashMap etfSL(SRequestMap sRequestMap) {
		
		log.info("sRequestMap: {}", sRequestMap.toString(true));
		
		SLinkedHashMap res = new SLinkedHashMap();
		res.put("requestCode"  , sRequestMap.getData().getString("requestCode", ""));
		res.put("errorCode"    , "");
		res.put("errorMessage" , "");
		res.put("remoteAddress", sRequestMap.getNetwork().getString("remoteAddr", ""));
		res.put("requestTime"  , SDateU.getDateString("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"));
		
		try {
			
			SRequestV sRequestV = new SRequestV();
			sRequestV.setUrl("https://finance.naver.com/api/sise/etfItemList.nhn?etfType=0&targetColumn=market_sum&sortOrder=desc&_callback=window.__jindo2_callback._4100");
			sRequestV.addHeaders("Accept", "text/html, application/xhtml+xml, image/jxr, */*");
//			sRequestV.addHeaders("Accept-Encoding", "gzip, deflate");
			sRequestV.addHeaders("Accept-Language", "ko-KR");
			sRequestV.addHeaders("Host", "finance.naver.com");
			sRequestV.addHeaders("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko");
			
			SResponseV sResponseV = SHttpU.request(sRequestV);
			
			if("1".equals(sRequestMap.getData().getString("isDebug", ""))) {
				res.put("responseCode"    , sResponseV.getResponseCode());
				res.put("responseMessage" , sResponseV.getResponseCode());
				res.put("exceptionMessage", sResponseV.getExceptionMessage());
			}
			
//			ArrayList<SLinkedHashMap> etfSL = new ArrayList<SLinkedHashMap>();
//			if(200 == sResponseV.getResponseCode()) {
//				SLinkedHashMap responseMap = new SLinkedHashMap(new String(sResponseV.getResponseBody(), sResponseV.getResponseCharset()).substring("window.__jindo2_callback._4100".length() + 1).replaceFirst(".$",""));
//				LinkedHashMap result = (LinkedHashMap) responseMap.get("result");
//				ArrayList<LinkedHashMap> etfItemList = (ArrayList<LinkedHashMap>) result.get("etfItemList");
//				SLinkedHashMap item = null;
//				for(LinkedHashMap etfItem : etfItemList) {
//					item = new SLinkedHashMap();
//					item.put("item_code", etfItem.get("itemcode"));
//					item.put("item_name", etfItem.get("itemname"));
//					etfSL.add(item);
//				}
//			}
			ArrayList<SLinkedHashMap> etfSL = new ArrayList<SLinkedHashMap>();
			if(200 == sResponseV.getResponseCode()) {
				SLinkedHashMap responseMap = new SLinkedHashMap(new String(sResponseV.getResponseBody(), sResponseV.getResponseCharset()).substring("window.__jindo2_callback._4100".length() + 1).replaceFirst(".$",""));
				LinkedHashMap result = responseMap.getLinkedHashMap("result");
				ArrayList<LinkedHashMap> etfItemList = (ArrayList<LinkedHashMap>) result.get("etfItemList");
				SLinkedHashMap item = null;
				for(LinkedHashMap etfItem : etfItemList) {
					item = new SLinkedHashMap();
					item.put("item_code", etfItem.get("itemcode"));
					item.put("item_name", etfItem.get("itemname"));
					etfSL.add(item);
				}
			}
			
			res.put("etfSL", etfSL);
			if(sResponseV.getResponseError() != null) {
				res.put("errorMessage", new String(sResponseV.getResponseError(), sResponseV.getResponseCharset()));
			}
			res.put("errorCode", "0000");
			
		} catch (JsonParseException e) {
			res.put("errorCode", "E101");
			res.put("errorMessage", ExceptionUtils.getStackTrace(e));
		} catch (JsonMappingException e) {
			res.put("errorCode", "E102");
			res.put("errorMessage", ExceptionUtils.getStackTrace(e));
		} catch (UnsupportedEncodingException e) {
			res.put("errorCode", "E103");
			res.put("errorMessage", ExceptionUtils.getStackTrace(e));
		} catch (IOException e) {
			res.put("errorCode", "E104");
			res.put("errorMessage", ExceptionUtils.getStackTrace(e));
		} catch (SCastException e) {
			res.put("errorCode", "E105");
			res.put("errorMessage", ExceptionUtils.getStackTrace(e));
		} finally {
			res.put("responseTime", SDateU.getDateString("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"));
		}
		
		return res;
	}

	@Override
	public SLinkedHashMap etfSR(SRequestMap sRequestMap) {
		
		SLinkedHashMap res = new SLinkedHashMap();
		
		return res;
	}

}
