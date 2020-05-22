package seung.spring.bibimbap.mining.etf;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.exception.SCastException;
import seung.java.kimchi.http.SHttpU;
import seung.java.kimchi.http.SRequestV;
import seung.java.kimchi.http.SResponseV;
import seung.java.kimchi.util.SLinkedHashMap;
import seung.spring.bibimbap.mining.SMiningException;

@Slf4j
@Component("sEtfMN")
public class SEtfMN {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SLinkedHashMap etfN0101() {
		
		SLinkedHashMap etfN0101 = new SLinkedHashMap();
		
		// default fields
		etfN0101.put("_error_code"   , "E999");
		etfN0101.put("_error_message", "");
		
		ArrayList<SLinkedHashMap> item_list = new ArrayList<SLinkedHashMap>();
		try {
			
			SRequestV sRequestV = new SRequestV();
			sRequestV.setUrl("https://finance.naver.com/api/sise/etfItemList.nhn?etfType=0&targetColumn=market_sum&sortOrder=desc&_callback=window.__jindo2_callback._4100");
			sRequestV.addHeaders("Accept", "text/html, application/xhtml+xml, image/jxr, */*");
//			sRequestV.addHeaders("Accept-Encoding", "gzip, deflate");
			sRequestV.addHeaders("Accept-Language", "ko-KR");
			sRequestV.addHeaders("Host", "finance.naver.com");
			sRequestV.addHeaders("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko");
			
			SResponseV sResponseV = SHttpU.request(sRequestV);
			
			etfN0101.put("response_code"    , sResponseV.getResponseCode());
			etfN0101.put("response_message" , sResponseV.getResponseCode());
			etfN0101.put("exception_message", sResponseV.getExceptionMessage());
			
			if(200 == sResponseV.getResponseCode()) {
				SLinkedHashMap responseMap = new SLinkedHashMap(new String(sResponseV.getResponseBody(), sResponseV.getResponseCharset()).substring("window.__jindo2_callback._4100".length() + 1).replaceFirst(".$",""));
				LinkedHashMap result = responseMap.getLinkedHashMap("result");
				ArrayList<LinkedHashMap> etfItemList = (ArrayList<LinkedHashMap>) result.get("etfItemList");
				SLinkedHashMap item = null;
				for(LinkedHashMap etfItem : etfItemList) {
					item = new SLinkedHashMap();
					item.put("item_code", etfItem.get("itemcode"));
					item.put("item_name", etfItem.get("itemname"));
					item_list.add(item);
				}
			}// end of mining
			
			if(sResponseV.getResponseError() != null) {
				etfN0101.put("error_message", new String(sResponseV.getResponseError(), sResponseV.getResponseCharset()));
			}
			
			etfN0101.put("_error_code", "0000");
			
		} catch (JsonParseException e) {
			etfN0101.put("error_code", "E901");
			etfN0101.put("error_message", ExceptionUtils.getStackTrace(e));
			log.error(etfN0101.getString("error_code", ""), e);
		} catch (JsonMappingException e) {
			etfN0101.put("error_code", "E902");
			etfN0101.put("error_message", ExceptionUtils.getStackTrace(e));
			log.error(etfN0101.getString("error_code", ""), e);
		} catch (UnsupportedEncodingException e) {
			etfN0101.put("error_code", "E903");
			etfN0101.put("error_message", ExceptionUtils.getStackTrace(e));
			log.error(etfN0101.getString("error_code", ""), e);
		} catch (IOException e) {
			etfN0101.put("error_code", "E904");
			etfN0101.put("error_message", ExceptionUtils.getStackTrace(e));
			log.error(etfN0101.getString("error_code", ""), e);
		} catch (SCastException e) {
			etfN0101.put("error_code", "E905");
			etfN0101.put("error_message", ExceptionUtils.getStackTrace(e));
			log.error(etfN0101.getString("error_code", ""), e);
		} finally {
			etfN0101.put("item_list", item_list);
		}
		
		return etfN0101;
		
	}// end of etfN0101
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SLinkedHashMap etfN0102(String item_code) {
		
		SLinkedHashMap etfN0102 = new SLinkedHashMap();
		
		// default fields
		etfN0102.put("_error_code"   , "E999");
		etfN0102.put("_error_message", "");
		
		ArrayList<SLinkedHashMap> cu_list = new ArrayList<SLinkedHashMap>();
		SLinkedHashMap            cu_map  = null;
		try {
			
			SRequestV sRequestV = new SRequestV();
			sRequestV.setUrl(String.format("%s%s", "https://navercomp.wisereport.co.kr/v2/ETF/index.aspx?cmp_cd=", item_code));
			sRequestV.addHeaders("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
//			sRequestV.addHeaders("Accept-Encoding", "gzip, deflate");
			sRequestV.addHeaders("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");
			sRequestV.addHeaders("Host", "navercomp.wisereport.co.kr");
			sRequestV.addHeaders("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko");
			
			SResponseV sResponseV = SHttpU.request(sRequestV);
			
			etfN0102.put("response_code"    , sResponseV.getResponseCode());
			etfN0102.put("response_message" , sResponseV.getResponseCode());
			etfN0102.put("exception_message", sResponseV.getExceptionMessage());
			
			if(200 == sResponseV.getResponseCode()) {
				
				String responseBody = new String(sResponseV.getResponseBody(), sResponseV.getResponseCharset());
				
				SLinkedHashMap miningData = null;
				
				if(responseBody.indexOf("status_data =") == -1) {
					etfN0102.put("error_code", "E001");
					throw new SMiningException("Failed to find status_data field.");
				}
				
				miningData = new SLinkedHashMap(responseBody.split("status_data =")[1].split(";")[0]);
				etfN0102.put("상장주식수", miningData.getString("LIST_STK_CNT", "").replaceAll("[^0-9]", ""));
				
				if(responseBody.indexOf("product_summary_data =") == -1) {
					etfN0102.put("error_code", "E002");
					throw new SMiningException("Failed to find product_summary_data field.");
				}
				
				miningData = new SLinkedHashMap(responseBody.split("product_summary_data =")[1].split(";")[0]);
				etfN0102.put("기초지수명", miningData.getString("BASE_IDX_NM_KOR", ""));
				etfN0102.put("최초설정일", miningData.getString("FIRST_SETTLE_DT", "").replaceAll("[^0-9]", ""));
				etfN0102.put("상장일", miningData.getString("LIST_DT", "").replaceAll("[^0-9]", ""));
				etfN0102.put("펀드형태", miningData.getString("FUND_TYP", ""));
				etfN0102.put("총보수", miningData.getString("TOT_PAY", ""));
				etfN0102.put("회계기간", miningData.getString("FIN_PRD", ""));
				etfN0102.put("분배금기준일", miningData.getString("DIV_BASE_DT", ""));
				etfN0102.put("자산운용사", miningData.getString("ISSUE_NM_KOR", ""));
				etfN0102.put("자산운용사홈페이지", miningData.getString("URL", ""));
				
				if(responseBody.indexOf("cmp_comment") == -1) {
					etfN0102.put("error_code", "E003");
					throw new SMiningException("Failed to find cmp_comment field.");
				}
				
				String cmp_comment = responseBody.split("cmp_comment")[1].split("</div>")[0];
				
				if(cmp_comment.indexOf("<div style=\"padding: 10px 5px; line-height:20px;\">") == -1) {
					etfN0102.put("error_code", "E004");
					throw new SMiningException("Failed to find cmp_comment field.");
				}
				
				cmp_comment = cmp_comment.split("<div style=\"padding: 10px 5px; line-height:20px;\">")[1];
				etfN0102.put("상품설명", cmp_comment.replaceAll("\\r|\\n", "").replaceAll("\\s{2,}", " ").trim().replaceAll("(?i)<br */?>", System.lineSeparator()));
				
				if(responseBody.indexOf("CU_data =") == -1) {
					etfN0102.put("error_code", "E005");
					throw new SMiningException("Failed to find CU_data field.");
				}
				
				miningData = new SLinkedHashMap(responseBody.split("CU_data =")[1].split(";")[0]);
				for(LinkedHashMap cu : miningData.getListLinkedHashMap("grid_data")) {
					cu_map = new SLinkedHashMap();
					cu_map.put("구성종목명", cu.get("STK_NM_KOR"));
					cu_map.put("주식수", cu.get("AGMT_STK_CNT"));
					cu_map.put("주식수", cu_map.getString("주식수", "").replaceAll("[^0-9]", ""));
					cu_map.put("구성비중", cu.get("ETF_WEIGHT") == null ? "" : "" + cu.get("ETF_WEIGHT"));
					cu_list.add(cu_map);
				}
				
				if(responseBody.indexOf("volume_chart_data =") == -1) {
					etfN0102.put("error_code", "E005");
					throw new SMiningException("Failed to find CU_data field.");
				}
				
			}// end of mining
			
			if(sResponseV.getResponseError() != null) {
				etfN0102.put("error_message", new String(sResponseV.getResponseError(), sResponseV.getResponseCharset()));
			}
			
			etfN0102.put("_error_code", "0000");
			
//		} catch (JsonParseException e) {
//			etfN0102.put("error_code", "E901");
//			etfN0102.put("error_message", ExceptionUtils.getStackTrace(e));
//			log.error(etfN0101.getString("error_code", ""), e);
//		} catch (JsonMappingException e) {
//			etfN0102.put("error_code", "E902");
//			etfN0102.put("error_message", ExceptionUtils.getStackTrace(e));
//			log.error(etfN0101.getString("error_code", ""), e);
		} catch (UnsupportedEncodingException e) {
			etfN0102.put("error_code", "E903");
			etfN0102.put("error_message", ExceptionUtils.getStackTrace(e));
			log.error(etfN0102.getString("error_code", ""), e);
		} catch (IOException e) {
			etfN0102.put("error_code", "E904");
			etfN0102.put("error_message", ExceptionUtils.getStackTrace(e));
			log.error(etfN0102.getString("error_code", ""), e);
		} catch (SCastException e) {
			etfN0102.put("error_code", "E905");
			etfN0102.put("error_message", ExceptionUtils.getStackTrace(e));
			log.error(etfN0102.getString("error_code", ""), e);
		} catch (SMiningException e) {
			etfN0102.put("error_message", ExceptionUtils.getStackTrace(e));
			log.error(etfN0102.getString("error_code", ""), e);
		} finally {
			etfN0102.put("구성종목", cu_list);
		}
		
		return etfN0102;
		
	}// end of etfN0102
	
}
