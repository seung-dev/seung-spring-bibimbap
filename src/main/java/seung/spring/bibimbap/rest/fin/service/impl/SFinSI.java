package seung.spring.bibimbap.rest.fin.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.SDate;
import seung.java.kimchi.util.SCode;
import seung.java.kimchi.util.SKimchiException;
import seung.spring.bibimbap.mine.fin.impl.SDartMI;
import seung.spring.bibimbap.mine.fin.impl.SNaverMI;
import seung.spring.bibimbap.mine.util.SMine;
import seung.spring.bibimbap.rest.fin.service.SFinS;
import seung.spring.boot.conf.web.util.SRequest;
import seung.spring.boot.conf.web.util.SResponse;

@Slf4j
@Service("sFinS")
public class SFinSI implements SFinS {

	@Resource(name="sDartM")
	private SDartMI sDartM;
	
	@Resource(name="sNaverM")
	private SNaverMI sNaverM;
	
//	@Override
//	public SResponse finD0101(SRequest sRequest) {
//		
//		log.debug("run");
//		
//		String requestCode = sRequest.getData().getString("request_code", "");
//		
//		SResponse sResponse = SResponse.builder()
//				.request_code(requestCode)
//				.data(sRequest.getData())
//				.build()
//				;
//		
//		try {
//			
//			log.debug("{}.sRequest: {}", requestCode, sRequest.toJsonString(true));
//			
//			SMine sMining = sDartM.d0101(requestCode);
//			
//			sResponse.setError_code(sMining.getErrorCode());
//			if("0000".equals(sMining.getErrorCode())) {
//				sResponse.setResult(sMining.getResult());
//				sResponse.setError_code("0000");
//			} else {
//				sResponse.setError_code(sMining.getErrorCode());
//				sResponse.setError_message(sMining.getErrorMessage());
//			}
//			
//		} catch (SKimchiException e) {
//			log.error("(({})) Failed to call n0101.", requestCode, e);
//			sResponse.setError_message(ExceptionUtils.getStackTrace(e));
//		}
//		
//		sResponse.setResponse_time(SDate.getDateString());
//		return sResponse;
//	}
	
	@Override
	public SResponse finN0101(SRequest sRequest) {
		
		String requestCode = sRequest.getData().getString("request_code", "");
		log.info(
				"{} {}"
				, requestCode
				, SCode.START
				);
		
		SResponse sResponse = SResponse.builder()
				.request_code(requestCode)
				.data(sRequest.getData())
				.build()
				;
		
		
		SMine sMine = sNaverM.n0101(requestCode);
		
		sResponse.setError_code(sMine.getErrorCode());
		if(SCode.SUCCESS.equals(sMine.getErrorCode())) {
			sResponse.setResult(sMine.getResult());
			sResponse.success();
		} else {
			sResponse.setError_code(sMine.getErrorCode());
			sResponse.setError_message(sMine.getErrorMessage());
		}
		
		sResponse.setResponse_time(SDate.getDateString());
		
		log.info(
				"{}.error_code={}"
				, requestCode
				, sResponse.getError_code()
				);
		log.info(
				"{} {}"
				, requestCode
				, SCode.ERROR
				);
		return sResponse;
	}
	
	@Override
	public SResponse finN0102(SRequest sRequest) {
		
		String requestCode = sRequest.getData().getString("request_code", "");
		log.info(
				"{} {}"
				, requestCode
				, SCode.START
				);
		log.info(
				"{}.item_code={}"
				, requestCode
				, sRequest.getData().getString("item_code", "")
				);
		
		SResponse sResponse = SResponse.builder()
				.request_code(requestCode)
				.data(sRequest.getData())
				.build()
				;
		
		
		SMine sMine = sNaverM.n0102(
				requestCode
				, sRequest.getData().getString("item_code", "")
				);
		
		sResponse.setError_code(sMine.getErrorCode());
		if(SCode.SUCCESS.equals(sMine.getErrorCode())) {
			sResponse.setResult(sMine.getResult());
			sResponse.success();
		} else {
			sResponse.setError_code(sMine.getErrorCode());
			sResponse.setError_message(sMine.getErrorMessage());
		}
		
		sResponse.setResponse_time(SDate.getDateString());
		
		log.info(
				"{}.error_code={}"
				, requestCode
				, sResponse.getError_code()
				);
		log.info(
				"{} {}"
				, requestCode
				, SCode.ERROR
				);
		return sResponse;
	}
	
}
