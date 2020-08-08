package seung.spring.bibimbap.mine.fin.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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
	
	@SuppressWarnings("unchecked")
	@Override
	public SMine n0104(
			String requestCode
			, String item_code
			) {
		
		log.debug("run");
		
		SMine sMine = SMine.builder()
				.mineCode("n0104")
				.requestCode(requestCode)
				.errorCode(SCode.ERROR)
				.build()
				;
		
		List<SLinkedHashMap> n0104 = new ArrayList<>();
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
					.get(String.format("https://finance.naver.com/item/coinfo.nhn?code=%s", item_code))
					.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko")
					.asBytes()
					;
			httpResponse = Unirest
					.get(String.format(sProperties.getSeung().getProperty("seung.mine.naver.n0104.url", ""), item_code))
					.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko")
					.asBytes()
					;
			
			while(true) {
				
				if(HttpStatus.OK.value() != httpResponse.getStatus()) {
					sMine.setErrorCode("D001");
					errorMessage = "Failed to get coinfo page.";
					break;
				}
				
				String response = new String(httpResponse.getBody(), "UTF-8");
				
				if(response.indexOf("\"ajax/cF1001.aspx\"") == -1) {
					sMine.setErrorCode("D002");
					errorMessage = "Failed to find url field.";
					break;
				}
				
				if(response.indexOf("encparam:") == -1) {
					sMine.setErrorCode("D003");
					errorMessage = "Failed to find encparam field.";
					break;
				}
				
				if(response.indexOf("id:") == -1) {
					sMine.setErrorCode("D004");
					errorMessage = "Failed to find id field.";
					break;
				}
				
				response = response.split("\"ajax/cF1001.aspx\"")[1].split("\\{")[1].split("\\}")[0];
				String encparam = response.split("encparam:")[1].split("'")[1].split("'")[0];
				String id = response.split("id:")[1].split("'")[1].split("'")[0];
				
//				log.info(String.format(sProperties.getSeung().getProperty("seung.mine.naver.n0105.url", ""), item_code, encparam, id));
				httpResponse = Unirest
						.get(String.format(sProperties.getSeung().getProperty("seung.mine.naver.n0105.url", ""), item_code, encparam, id))
						.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko")
						.header("Referer", String.format("https://navercomp.wisereport.co.kr/v2/company/c1010001.aspx?cmp_cd=%s", item_code))
						.asBytes()
						;
				
				
				if(HttpStatus.OK.value() != httpResponse.getStatus()) {
					sMine.setErrorCode("D005");
					errorMessage = "Failed to get cF1001 page.";
					break;
				}
				
				response = new String(httpResponse.getBody(), "UTF-8");
				
//				log.info(response);
				
				if(response.indexOf("연간") == -1) {
					sMine.setErrorCode("D006");
					errorMessage = "Failed to find item_sd field.";
					break;
				}
				
				response = response.split("연간")[1];
				
				String[] item_sds = response.split("</thead>")[0].split("<th");
				String item_sd = "";
				for(int i = 1; i < item_sds.length; i++) {
					item_sd = item_sds[i].split(">")[1].split("<")[0];
					n0104.add(new SLinkedHashMap()
							.add("item_sd", item_sd.replaceAll("[^0-9-]", ""))
							.add("is_est", item_sd.contains("E") ? 1 : 0)
							);
				}
				
				String[] item_trs = response.split("매출액")[1].split("<td");
				String item_tr = "";
				for(int i = 1; i <= n0104.size(); i++) {
					item_tr = item_trs[i];
					if(item_tr.contains("<span")) {
						item_tr = item_tr.split("<span")[1].split(">")[1].split("<")[0];
					} else {
						item_tr = "";
					}
					n0104.get(i - 1).put("item_tr", item_tr.replaceAll("[^0-9-]", ""));
				}
				
				String[] item_ois = response.split("영업이익")[1].split("<td");
				String item_oi = "";
				for(int i = 1; i <= n0104.size(); i++) {
					item_oi = item_ois[i];
					if(item_oi.contains("<span")) {
						item_oi = item_oi.split("<span")[1].split(">")[1].split("<")[0];
					} else {
						item_oi = "";
					}
					n0104.get(i - 1).put("item_oi", item_oi.replaceAll("[^0-9-]", ""));
				}
				
				String[] item_tas = response.split("자산총계")[1].split("<td");
				String item_ta = "";
				for(int i = 1; i <= n0104.size(); i++) {
					item_ta = item_tas[i];
					if(item_ta.contains("<span")) {
						item_ta = item_ta.split("<span")[1].split(">")[1].split("<")[0];
					} else {
						item_ta = "";
					}
					n0104.get(i - 1).put("item_ta", item_ta.replaceAll("[^0-9-]", ""));
				}
				
				String[] item_tls = response.split("부채총계")[1].split("<td");
				String item_tl = "";
				for(int i = 1; i <= n0104.size(); i++) {
					item_tl = item_tls[i];
					if(item_tl.contains("<span")) {
						item_tl = item_tl.split("<span")[1].split(">")[1].split("<")[0];
					} else {
						item_tl = "";
					}
					n0104.get(i - 1).put("item_tl", item_tl.replaceAll("[^0-9-]", ""));
				}
				
				String[] item_tes = response.split("자본총계")[1].split("<td");
				String item_te = "";
				for(int i = 1; i <= n0104.size(); i++) {
					item_te = item_tes[i];
					if(item_te.contains("<span")) {
						item_te = item_te.split("<span")[1].split(">")[1].split("<")[0];
					} else {
						item_te = "";
					}
					n0104.get(i - 1).put("item_te", item_te.replaceAll("[^0-9-]", ""));
				}
				
				String[] item_cfos = response.split("영업활동현금흐름")[1].split("<td");
				String item_cfo = "";
				for(int i = 1; i <= n0104.size(); i++) {
					item_cfo = item_cfos[i];
					if(item_cfo.contains("<span")) {
						item_cfo = item_cfo.split("<span")[1].split(">")[1].split("<")[0];
					} else {
						item_cfo = "";
					}
					n0104.get(i - 1).put("item_cfo", item_cfo.replaceAll("[^0-9-]", ""));
				}
				
				String[] item_des = response.split("부채비율")[1].split("<td");
				String item_de = "";
				for(int i = 1; i <= n0104.size(); i++) {
					item_de = item_des[i];
					if(item_de.contains("<span")) {
						item_de = item_de.split("<span")[1].split(">")[1].split("<")[0];
					} else {
						item_de = "";
					}
					n0104.get(i - 1).put("item_de", item_de.replaceAll("[^0-9-]", ""));
				}
				
				sMine.success();
				break;
			}
			
			
//		} catch (SKimchiException e) {
//			log.error(
//					"{}.{}.exception"
//					, sMine.getMineCode()
//					, sMine.getRequestCode()
//					, e
//					);
//			errorMessage = ExceptionUtils.getStackTrace(e);
//			if(errorMessage == null || "".equals(errorMessage)) {
//				errorMessage = "" + e;
//			}
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
			sMine.putResult("n0104", n0104);
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
