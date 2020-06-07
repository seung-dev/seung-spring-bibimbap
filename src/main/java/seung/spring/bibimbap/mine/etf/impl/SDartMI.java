package seung.spring.bibimbap.mine.etf.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.SFile;
import seung.java.kimchi.exception.SKimchiException;
import seung.java.kimchi.http.SHttp;
import seung.java.kimchi.http.SHttpRequest;
import seung.java.kimchi.http.SHttpResponse;
import seung.java.kimchi.util.SLinkedHashMap;
import seung.java.kimchi.xml.SSaxParser;
import seung.java.kimchi.xml.SXml;
import seung.spring.bibimbap.mine.etf.SDartM;
import seung.spring.bibimbap.mine.util.SMine;
import seung.spring.bibimbap.mine.util.SMiningException;
import seung.spring.boot.conf.SProperties;

@Slf4j
@Component("sDartM")
public class SDartMI implements SDartM {

    @Resource(name="sProperties")
    private SProperties sProperties;
    
    @Override
    public SMine d0101(
            String requestCode
            ) {
        
        log.debug("run");
        
        SMine sMine = SMine.builder()
                .requestCode(requestCode)
                .build()
                ;
        
        List<SLinkedHashMap> d0101 = null;
        try {
            
            SHttpRequest sHttpRequest = SHttpRequest.builder()
                    .url("https://opendart.fss.or.kr/api/corpCode.xml")
                    .data(Pair.of("crtfc_key", sProperties.getSeung().getProperty("seung.mine.dart.api.key", "")))
                    .build()
                    ;
            
            SHttpResponse sHttpResponse = SHttp.request(sHttpRequest);
            
            log.debug("{}.getResponseCode={}", requestCode, sHttpResponse.getResponseCode());
            sMine.putResult("responseCode", sHttpResponse.getResponseCode());
            sMine.putResult("responseMessage", sHttpResponse.getResponseMessage());
            if(200 == sHttpResponse.getResponseCode()) {
                
                byte[] xml = SFile.unzipSingleTextFile(sHttpResponse.getResponseBody());
                SXml sXml = SSaxParser.parse(xml, false, false, true, "result.list", -1, 10);
                d0101 = sXml.getItem();
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
            sMine.putResult("d0101", d0101);
        }
        
        return sMine;
    }// end of d0101
    
}
