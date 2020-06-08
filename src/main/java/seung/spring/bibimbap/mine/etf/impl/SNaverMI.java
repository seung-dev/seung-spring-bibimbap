package seung.spring.bibimbap.mine.etf.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.exception.SKimchiException;
import seung.java.kimchi.http.SHttp;
import seung.java.kimchi.http.SHttpRequest;
import seung.java.kimchi.http.SHttpResponse;
import seung.java.kimchi.util.SLinkedHashMap;
import seung.spring.bibimbap.mine.etf.SNaverM;
import seung.spring.bibimbap.mine.util.SMine;
import seung.spring.bibimbap.mine.util.SMiningException;
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
                .requestCode(requestCode)
                .build()
                ;
        
        ArrayList<SLinkedHashMap> n0101 = new ArrayList<SLinkedHashMap>();
        try {
            
            SHttpRequest sHttpRequest = SHttpRequest.builder()
                    .url(sProperties.getSeung().getProperty("seung.mine.naver.n0101.url", ""))
                    .build()
                    ;
            
            sHttpRequest.addHeader("Accept", "text/html, application/xhtml+xml, image/jxr, */*");
//            sHttpRequest.addHeader("Accept-Encoding", "gzip, deflate");
            sHttpRequest.addHeader("Accept-Language", "ko-KR");
            sHttpRequest.addHeader("Host", "finance.naver.com");
            sHttpRequest.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko");
            
            SHttpResponse sHttpResponse = SHttp.request(sHttpRequest);
            
            log.debug("{}.getResponseCode={}", requestCode, sHttpResponse.getResponseCode());
            sMine.putResult("responseCode", sHttpResponse.getResponseCode());
            sMine.putResult("responseMessage", sHttpResponse.getResponseMessage());
            if(200 == sHttpResponse.getResponseCode()) {
                
                SLinkedHashMap responseMap = new SLinkedHashMap(
                        new String(
                                sHttpResponse.getResponseBody(), sHttpResponse.getResponseCharset()
                                ).substring("window.__jindo2_callback._4100".length() + 1).replaceFirst(".$","")
                        );
                SLinkedHashMap result = responseMap.getSLinkedHashMap("result");
                
                SLinkedHashMap format = null;
                for(SLinkedHashMap item : result.getListSLinkedHashMap("etfItemList")) {
                    format = new SLinkedHashMap();
                    format.put("item_code", item.getString("itemcode", ""));
                    format.put("item_name", item.getString("itemname", ""));
                    n0101.add(format);
                }
                sMine.setErrorCode("0000");
                
            } else {
                
                sMine.setErrorCode("E001");
                if(sHttpResponse.getResponseError() != null) {
                    sMine.setErrorMessage(new String(sHttpResponse.getResponseError(), sHttpResponse.getResponseCharset()));
                } else {
                    throw new SMiningException(
                            String.format(
                                    "responseCode=%s, responseMessage=%s"
                                    , sHttpResponse.getResponseCode()
                                    , sHttpResponse.getResponseMessage()
                                    )
                            );
                }
                
            }
            
        } catch (SKimchiException e) {
            sMine.setErrorMessage(ExceptionUtils.getStackTrace(e));
            log.error("{}.error", requestCode, e);
        } catch (UnsupportedEncodingException e) {
            sMine.setErrorMessage(ExceptionUtils.getStackTrace(e));
            log.error("{}.error", requestCode, e);
        } catch (SMiningException e) {
            sMine.setErrorMessage(ExceptionUtils.getStackTrace(e));
            log.error("{}.error", requestCode, e);
        } finally {
            sMine.putResult("n0101", n0101);
        }
        
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
                .requestCode(requestCode)
                .data(new SLinkedHashMap().add("item_code", item_code))
                .build()
                ;
        
        SLinkedHashMap n0102 = new SLinkedHashMap();
        ArrayList<SLinkedHashMap> cu = new ArrayList<SLinkedHashMap>();
        try {
            
            SHttpRequest sHttpRequest = SHttpRequest.builder()
                    .url(String.format("%s%s", sProperties.getSeung().getProperty("seung.mine.naver.n0102.url", ""), item_code))
                    .build()
                    ;
            
            sHttpRequest.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
//          sHttpRequest.addHeader("Accept-Encoding", "gzip, deflate");
            sHttpRequest.addHeader("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");
            sHttpRequest.addHeader("Host", "navercomp.wisereport.co.kr");
            sHttpRequest.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko");
            
            SHttpResponse sHttpResponse = SHttp.request(sHttpRequest);
            
            log.debug("{}.getResponseCode={}", requestCode, sHttpResponse.getResponseCode());
            sMine.putResult("responseCode", sHttpResponse.getResponseCode());
            sMine.putResult("responseMessage", sHttpResponse.getResponseMessage());
            if(200 == sHttpResponse.getResponseCode()) {
                
                String responseBody = new String(sHttpResponse.getResponseBody(), sHttpResponse.getResponseCharset());
                
                SLinkedHashMap miningData = null;
                
                if(responseBody.indexOf("status_data =") == -1) {
                    sMine.setErrorCode("D001");
                    throw new SMiningException("Failed to find status_data field.");
                }
                
                miningData = new SLinkedHashMap(responseBody.split("status_data =")[1].split(";")[0]);
                n0102.put("shar_oust", miningData.getString("LIST_STK_CNT", "").replaceAll("[^0-9]", ""));
                
                if(responseBody.indexOf("product_summary_data =") == -1) {
                    sMine.setErrorCode("D002");
                    throw new SMiningException("Failed to find status_data field.");
                }
                
                miningData = new SLinkedHashMap(responseBody.split("product_summary_data =")[1].split(";")[0]);
                n0102.put("item_name_kr", miningData.getString("BASE_IDX_NM_KOR", ""));
                n0102.put("date_set", miningData.getString("FIRST_SETTLE_DT", "").replaceAll("[^0-9]", ""));
                n0102.put("date_list", miningData.getString("LIST_DT", "").replaceAll("[^0-9]", ""));
                n0102.put("asst_clss", miningData.getString("FUND_TYP", ""));
                n0102.put("comm_fee", miningData.getString("TOT_PAY", ""));
                n0102.put("acct_perd", miningData.getString("FIN_PRD", ""));
                n0102.put("date_dstb", miningData.getString("DIV_BASE_DT", ""));
                n0102.put("issr", miningData.getString("ISSUE_NM_KOR", ""));
                n0102.put("issr_url", miningData.getString("URL", ""));
                
                if(responseBody.indexOf("cmp_comment") == -1) {
                    sMine.setErrorCode("D003");
                    throw new SMiningException("Failed to find cmp_comment field.");
                }
                
                String cmp_comment = responseBody.split("cmp_comment")[1].split("</div>")[0];
                
                if(cmp_comment.indexOf("<div style=\"padding: 10px 5px; line-height:20px;\">") == -1) {
                    sMine.setErrorCode("D004");
                    throw new SMiningException("Failed to find cmp_comment field.");
                }
                
                cmp_comment = cmp_comment.split("<div style=\"padding: 10px 5px; line-height:20px;\">")[1];
//                n0102.put("item_dscr", cmp_comment.replaceAll("\\r|\\n", "").replaceAll("\\s{2,}", " ").trim().replaceAll("(?i)<br */?>", System.lineSeparator()));
                n0102.put("item_dscr", cmp_comment.replaceAll("\\r|\\n", "").replaceAll("\\s{2,}", " ").trim());
                
                if(responseBody.indexOf("CU_data =") == -1) {
                    sMine.setErrorCode("D005");
                    throw new SMiningException("Failed to find CU_data field.");
                }
                
                miningData = new SLinkedHashMap(responseBody.split("CU_data =")[1].split(";")[0]);
                
                SLinkedHashMap cu_map = null;
                for(SLinkedHashMap item : miningData.getListSLinkedHashMap("grid_data")) {
                    cu_map = new SLinkedHashMap();
                    cu_map.put("item_name_kr", item.get("STK_NM_KOR"));
                    cu_map.put("asst_wght", item.get("ETF_WEIGHT") == null ? "" : "" + item.get("ETF_WEIGHT"));
                    cu.add(cu_map);
                }
                
                sMine.setErrorCode("0000");
                
            } else {
                
                sMine.setErrorCode("E001");
                if(sHttpResponse.getResponseError() != null) {
                    sMine.setErrorMessage(new String(sHttpResponse.getResponseError(), sHttpResponse.getResponseCharset()));
                } else {
                    throw new SMiningException(
                            String.format(
                                    "responseCode=%s, responseMessage=%s"
                                    , sHttpResponse.getResponseCode()
                                    , sHttpResponse.getResponseMessage()
                                    )
                            );
                }
                
            }
            
        } catch (SKimchiException e) {
            sMine.setErrorMessage(ExceptionUtils.getStackTrace(e));
            log.error("{}.error", requestCode, e);
        } catch (UnsupportedEncodingException e) {
            sMine.setErrorMessage(ExceptionUtils.getStackTrace(e));
            log.error("{}.error", requestCode, e);
        } catch (SMiningException e) {
            sMine.setErrorMessage(ExceptionUtils.getStackTrace(e));
            log.error("{}.error", requestCode, e);
        } finally {
            n0102.put("cu", cu);
            sMine.putResult("n0102", n0102);
        }
        
        return sMine;
    }// end of etfN0102
    
}
