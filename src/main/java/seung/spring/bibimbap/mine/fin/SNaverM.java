package seung.spring.bibimbap.mine.fin;

import seung.spring.bibimbap.mine.util.SMine;

public interface SNaverM {

	SMine n0101(String requestCode);
	
	SMine n0102(String requestCode, String item_code);
	
	SMine n0104(String requestCode, String item_code);
	
}
