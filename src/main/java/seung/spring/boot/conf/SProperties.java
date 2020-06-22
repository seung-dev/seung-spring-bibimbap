package seung.spring.boot.conf;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import lombok.Builder;
import lombok.Getter;

@Builder(builderMethodName = "hiddenBuilder")
@Getter
//@Setter
public class SProperties {

	private Properties environment;
	
	private Properties swagger;
	
	private List<Properties> datasource;
	
	private Properties jpa;
	
	private Properties jpaVendor;
	
	private Properties quartz;
	
	private Properties seung;
	
	public static SPropertiesBuilder builder() {
		return hiddenBuilder()
				.environment(new Properties())
				.swagger(new Properties())
				.datasource(new ArrayList<>())
				.jpa(new Properties())
				.jpaVendor(new Properties())
				.quartz(new Properties())
				.seung(new Properties())
				;
	}
	
}
