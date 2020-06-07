package seung.spring.bibimbap.rest.etf.service;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.SDate;
import seung.java.kimchi.exception.SKimchiException;
import seung.spring.bibimbap.mine.etf.impl.SNaverMI;
import seung.spring.bibimbap.mine.util.SMine;
import seung.spring.boot.conf.web.util.SRequest;
import seung.spring.boot.conf.web.util.SResponse;

@Slf4j
@Service("sEtfS")
public class SEftSI implements SEtfS {

    @Resource(name="sNaverM")
    private SNaverMI sNaverM;
    
    @Override
    public SResponse etfN0101(SRequest sRequest) {
        
        log.debug("run");
        
        String requestCode = sRequest.getData().getString("request_code", "");
        
        SResponse sResponse = SResponse.builder()
                .request_code(requestCode)
                .data(sRequest.getData())
                .build()
                ;
        
        try {
            
            log.debug("{}.sRequest: {}", requestCode, sRequest.toJsonString(true));
            
            SMine sMining = sNaverM.n0101(requestCode);
            
            sResponse.setError_code(sMining.getErrorCode());
            if("0000".equals(sMining.getErrorCode())) {
                sResponse.setResult(sMining.getResult());
                sResponse.setError_code("0000");
            } else {
                sResponse.setError_code(sMining.getErrorCode());
                sResponse.setError_message(sMining.getErrorMessage());
            }
            
        } catch (SKimchiException e) {
            log.error("(({})) Failed to call n0101.", requestCode, e);
            sResponse.setError_message(ExceptionUtils.getStackTrace(e));
        }
        
        sResponse.setResponse_time(SDate.getDateString());
        return sResponse;
    }

    @Override
    public SResponse etfN0102(SRequest sRequest) {
        
        log.info("run");
        
        SResponse sResponse = SResponse.builder().data(sRequest.getData()).build();
        
        String requestCode = sRequest.getData().getString("request_code", "");
        try {
            
            log.debug("{}.sRequest: {}", requestCode, sRequest.toJsonString(true));
            
            SMine sMining = sNaverM.n0102(
                    requestCode
                    , sRequest.getData().getString("item_code", "")
                    );
            
            if("0000".equals(sMining.getErrorCode())) {
                sResponse.setResult(sMining.getResult());
                sResponse.setError_code("0000");
            } else {
                sResponse.setError_code(sMining.getErrorCode());
                sResponse.setError_message(sMining.getErrorMessage());
            }
            
        } catch (SKimchiException e) {
            log.error("(({})) Failed to call n0102.", requestCode, e);
            sResponse.setError_message(ExceptionUtils.getStackTrace(e));
        }
        
        sResponse.setResponse_time(SDate.getDateString());
        return sResponse;
    }
    
}
