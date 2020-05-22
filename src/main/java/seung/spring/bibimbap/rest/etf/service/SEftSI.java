package seung.spring.bibimbap.rest.etf.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.SDateU;
import seung.java.kimchi.exception.SCastException;
import seung.java.kimchi.util.SLinkedHashMap;
import seung.java.kimchi.util.SRequestMap;
import seung.spring.bibimbap.mining.etf.SEtfMN;

@Slf4j
@Service("sEtfS")
public class SEftSI implements SEtfS {

	@Resource(name="sEtfMN")
	private SEtfMN sEtfMN;
	
	@SuppressWarnings("unchecked")
	@Override
	public SLinkedHashMap etfN0100(SRequestMap sRequestMap) {
		
		log.info("sRequestMap: {}", sRequestMap.toString(true));
		
		SLinkedHashMap res = new SLinkedHashMap();
		
		res.put("_request_code"  , sRequestMap.getData().getString("request_code", ""));
		res.put("_error_code"    , "E999");
		res.put("_error_message" , "");
		res.put("_request_time"  , SDateU.getDateString());
		res.put("_response_time" , "");
		res.put("_remote_address", sRequestMap.getNetwork().getString("remote_address", ""));
		try {
			
			SLinkedHashMap etfN0101 = sEtfMN.etfN0101();
			
			if("0000".equals(etfN0101.getString("_error_code", ""))) {
				
				int maxTimes = 10;
				int tryTimes = 0;
				for(SLinkedHashMap item : etfN0101.getListSLinkedHashMap("item_list")) {
					tryTimes++;
					item.putAll(sEtfMN.etfN0102(item.getString("item_code", "")));
					item.remove("_error_code");
					item.remove("_error_message");
					item.remove("response_code");
					item.remove("response_message");
					item.remove("exception_message");
					if(tryTimes == maxTimes) {
						break;
					}
				}
				
			}// end of item list loop
			
			res.putAll(etfN0101);
			res.put("_error_code"    , "");
			
		} catch (SCastException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		res.put("_response_time", SDateU.getDateString());
		
		return res;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SLinkedHashMap etfN0101(SRequestMap sRequestMap) {
		
		log.info("sRequestMap: {}", sRequestMap.toString(true));
		
		SLinkedHashMap res = new SLinkedHashMap();
		
		res.put("_request_code"  , sRequestMap.getData().getString("request_code", ""));
		res.put("_error_code"    , "E999");
		res.put("_error_message" , "");
		res.put("_request_time"  , SDateU.getDateString());
		res.put("_response_time" , "");
		res.put("_remote_address", sRequestMap.getNetwork().getString("remote_address", ""));
		
		res.putAll(sEtfMN.etfN0101());
		
		res.put("_response_time", SDateU.getDateString());
		
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public SLinkedHashMap etfN0102(SRequestMap sRequestMap) {
		
		SLinkedHashMap res = new SLinkedHashMap();
		
		res.put("_request_code" , sRequestMap.getData().getString("request_code", ""));
		res.put("_error_code"   , "E999");
		res.put("_error_message", "");
		res.put("_request_time" , SDateU.getDateString());
		res.put("_response_time", "");
		res.put("_remote_address", sRequestMap.getNetwork().getString("remote_address", ""));
		
		res.putAll(sEtfMN.etfN0102(sRequestMap.getData().getString("item_code", "")));
		
		res.put("_response_time", SDateU.getDateString());
		
		return res;
	}

}
