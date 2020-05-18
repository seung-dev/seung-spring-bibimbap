package seung.spring.boot.conf.support;

import java.util.Collection;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;

public class SApiInfo extends ApiInfo {

	@SuppressWarnings("rawtypes")
	public SApiInfo(
			String title
			, String description
			, String version
			, String termsOfServiceUrl
			, Contact contact
			, String license
			, String licenseUrl
			, Collection<VendorExtension> vendorExtensions
			) {
		super(title, description, version, termsOfServiceUrl, contact, license, licenseUrl, vendorExtensions);
		// TODO Auto-generated constructor stub
	}

}
