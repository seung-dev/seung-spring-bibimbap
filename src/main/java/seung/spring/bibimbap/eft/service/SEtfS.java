package seung.spring.bibimbap.eft.service;

import seung.java.kimchi.util.SLinkedHashMap;
import seung.spring.boot.conf.support.SRequestMapSwagger;

public interface SEtfS {

	SLinkedHashMap etfSL(SRequestMapSwagger sRequestMap);
	
	SLinkedHashMap etfSR(SRequestMapSwagger sRequestMap);
	
}
