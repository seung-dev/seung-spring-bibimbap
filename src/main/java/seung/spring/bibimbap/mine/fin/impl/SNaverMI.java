package seung.spring.bibimbap.mine.fin.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.util.SKimchiException;
import seung.java.kimchi.util.SLinkedHashMap;
import seung.java.kimchi.util.SCode;
import seung.spring.bibimbap.mine.fin.SNaverM;
import seung.spring.bibimbap.mine.util.SMine;
import seung.spring.boot.conf.SProperties;

@Slf4j
@Component("sNaverM")
public class SNaverMI implements SNaverM {

	@Resource(name="sProperties")
	private SProperties sProperties;
	
	@SuppressWarnings("unchecked")
	@Override
	public SMine n0101(
			String requestCode
			) {
		
		log.debug("run");
		
		SMine sMine = SMine.builder()
				.mineCode("n0101")
				.requestCode(requestCode)
				.errorCode(SCode.ERROR)
				.build()
				;
		
		ArrayList<SLinkedHashMap> n0101 = new ArrayList<SLinkedHashMap>();
		String errorMessage = "";
		try {
			
			log.info(
					"{}.{} {}"
					, sMine.getMineCode()
					, sMine.getRequestCode()
					, SCode.START
					);
			
			HttpResponse<byte[]> httpResponse = Unirest
					.post(sProperties.getSeung().getProperty("seung.mine.naver.n0101.url", ""))
					.header("Accept", "text/html, application/xhtml+xml, image/jxr, */*")
					.header("Accept-Language", "ko-KR")
					.header("Host", "finance.naver.com")
					.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko")
					.asBytes()
					;
			
			sMine.putResult("status", httpResponse.getStatus());
			sMine.putResult("statusText", httpResponse.getStatusText());
			if(HttpStatus.OK.value() == httpResponse.getStatus()) {
				
				while(true) {
					
					if(httpResponse.getBody() == null) {
						sMine.setErrorCode("E001");
						errorMessage = "Response body is empty.";
						break;
					}
					
					SLinkedHashMap response = new SLinkedHashMap(
							new String(httpResponse.getBody(), "EUC-KR")
								.substring("window.__jindo2_callback._4100".length() + 1).replaceFirst(".$","")
							);
					
					SLinkedHashMap result = response.getSLinkedHashMap("result");
					
					SLinkedHashMap format = null;
					for(SLinkedHashMap item : result.getListSLinkedHashMap("etfItemList")) {
						format = new SLinkedHashMap();
						format.put("item_code", item.getString("itemcode", ""));
						format.put("item_name", item.getString("itemname", ""));
						format.put("etf_type", item.getString("etfTabCode", ""));
						n0101.add(format);
					}
					
					sMine.success();
					break;
				}// end of body
				
			}// end of status
			
		} catch (UnsupportedEncodingException e) {
			log.error(
					"{}.{}.exception"
					, sMine.getMineCode()
					, sMine.getRequestCode()
					, e
					);
			errorMessage = ExceptionUtils.getStackTrace(e);
			if(errorMessage == null || "".equals(errorMessage)) {
				errorMessage = "" + e;
			}
		} catch (SKimchiException e) {
			log.error(
					"{}.{}.exception"
					, sMine.getMineCode()
					, sMine.getRequestCode()
					, e
					);
			errorMessage = ExceptionUtils.getStackTrace(e);
			if(errorMessage == null || "".equals(errorMessage)) {
				errorMessage = "" + e;
			}
		} finally {
			log.info(
					"{}.{}.error_code={}"
					, sMine.getMineCode()
					, sMine.getRequestCode()
					, sMine.getErrorCode()
					);
			sMine.setErrorMessage(errorMessage);
			sMine.putResult("n0101", n0101);
		}
		
		log.info(
				"{}.{} {}"
				, sMine.getMineCode()
				, sMine.getRequestCode()
				, SCode.END
				);
		return sMine;
	}// end of etfN0101
	
	@SuppressWarnings("unchecked")
	@Override
	public SMine n0102(
			String requestCode
			, String item_code
			) {
		
		log.debug("run");
		
		SMine sMine = SMine.builder()
				.mineCode("n0102")
				.requestCode(requestCode)
				.errorCode(SCode.ERROR)
				.build()
				;
		
		SLinkedHashMap n0102 = new SLinkedHashMap();
		ArrayList<SLinkedHashMap> cu = new ArrayList<SLinkedHashMap>();
		String errorMessage = "";
		try {
			
			log.info(
					"{}.{} {}"
					, sMine.getMineCode()
					, sMine.getRequestCode()
					, SCode.START
					);
			log.info(
					"{}.{}.item_code={}"
					, sMine.getMineCode()
					, sMine.getRequestCode()
					, item_code
					);
			
			HttpResponse<byte[]> httpResponse = Unirest
					.get(sProperties.getSeung().getProperty("seung.mine.naver.n0102.url", ""))
					.queryString("cmp_cd", item_code)
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
					.header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.header("Host", "navercomp.wisereport.co.kr")
					.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko")
					.asBytes()
					;
			
			sMine.putResult("status", httpResponse.getStatus());
			sMine.putResult("statusText", httpResponse.getStatusText());
			if(HttpStatus.OK.value() == httpResponse.getStatus()) {
				
				while(true) {
					
					if(httpResponse.getBody() == null) {
						sMine.setErrorCode("E001");
						errorMessage = "Response body is empty.";
						break;
					}
					
					String response = new String(httpResponse.getBody(), "UTF-8");
					
					if(response.indexOf("status_data =") == -1) {
						sMine.setErrorCode("D001");
						errorMessage = "Failed to find status_data field.";
						break;
					}
					
					SLinkedHashMap miningData = new SLinkedHashMap(response.split("status_data =")[1].split(";")[0]);
					n0102.put("shar_oust", miningData.getString("LIST_STK_CNT", "").replaceAll("[^0-9]", ""));
					
					if(response.indexOf("product_summary_data =") == -1) {
						sMine.setErrorCode("D002");
						errorMessage = "Failed to find status_data field.";
						break;
					}
					
					miningData = new SLinkedHashMap(response.split("product_summary_data =")[1].split(";")[0]);
					n0102.put("indx_name", miningData.getString("BASE_IDX_NM_KOR", ""));
					n0102.put("date_set", miningData.getString("FIRST_SETTLE_DT", "").replaceAll("[^0-9]", ""));
					n0102.put("date_list", miningData.getString("LIST_DT", "").replaceAll("[^0-9]", ""));
					n0102.put("asst_clss", miningData.getString("FUND_TYP", ""));
					n0102.put("expn_rate", miningData.getString("TOT_PAY", ""));
					n0102.put("acct_perd", miningData.getString("FIN_PRD", ""));
					n0102.put("date_dstb", miningData.getString("DIV_BASE_DT", ""));
					n0102.put("issr", miningData.getString("ISSUE_NM_KOR", ""));
					n0102.put("issr_url", miningData.getString("URL", ""));
					
					if(response.indexOf("cmp_comment") == -1) {
						sMine.setErrorCode("D003");
						errorMessage = "Failed to find cmp_comment field.";
						break;
					}
					String cmp_comment = response.split("cmp_comment")[1].split("</div>")[0];
					if(cmp_comment.indexOf("<div style=\"padding: 10px 5px; line-height:20px;\">") == -1) {
						sMine.setErrorCode("D004");
						errorMessage = "Failed to find cmp_comment field.";
						break;
					}
					cmp_comment = cmp_comment.split("<div style=\"padding: 10px 5px; line-height:20px;\">")[1];
//					n0102.put("item_dscr", cmp_comment.replaceAll("\\r|\\n", "").replaceAll("\\s{2,}", " ").trim().replaceAll("(?i)<br */?>", System.lineSeparator()));
					n0102.put("item_dscr", cmp_comment.replaceAll("\\r|\\n", "").replaceAll("\\s{2,}", " ").trim());
					
					if(response.indexOf("CU_data =") == -1) {
						sMine.setErrorCode("D005");
						errorMessage = "Failed to find CU_data field.";
						break;
					}
					
					miningData = new SLinkedHashMap(response.split("CU_data =")[1].split("var")[0].trim().replace("};", "}"));
					
					SLinkedHashMap cu_map = null;
					for(SLinkedHashMap item : miningData.getListSLinkedHashMap("grid_data")) {
						cu_map = new SLinkedHashMap();
						cu_map.put("item_name_kr", item.get("STK_NM_KOR"));
						cu_map.put("asst_wght", item.get("ETF_WEIGHT") == null ? "" : "" + item.get("ETF_WEIGHT"));
						cu.add(cu_map);
					}
					
					sMine.success();
					break;
				}// end of body
				
			}// end of status
			
		} catch (SKimchiException e) {
			log.error(
					"{}.{}.exception"
					, sMine.getMineCode()
					, sMine.getRequestCode()
					, e
					);
			errorMessage = ExceptionUtils.getStackTrace(e);
			if(errorMessage == null || "".equals(errorMessage)) {
				errorMessage = "" + e;
			}
		} catch (UnsupportedEncodingException e) {
			log.error(
					"{}.{}.exception"
					, sMine.getMineCode()
					, sMine.getRequestCode()
					, e
					);
			errorMessage = ExceptionUtils.getStackTrace(e);
			if(errorMessage == null || "".equals(errorMessage)) {
				errorMessage = "" + e;
			}
		} finally {
			log.info(
					"{}.{}.error_code={}"
					, sMine.getMineCode()
					, sMine.getRequestCode()
					, sMine.getErrorCode()
					);
			sMine.setErrorMessage(errorMessage);
			n0102.put("cu", cu);
			sMine.putResult("n0102", n0102);
		}
		
		log.info(
				"{}.{} {}"
				, sMine.getMineCode()
				, sMine.getRequestCode()
				, SCode.END
				);
		return sMine;
	}// end of etfN0102
	
}
