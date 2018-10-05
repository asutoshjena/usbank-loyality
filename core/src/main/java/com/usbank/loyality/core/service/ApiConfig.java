package com.usbank.loyality.core.service;


import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Agility Loyality Configuration Service", description = "Configuration Service for Agility Loyality")
	public @interface ApiConfig {

	    @AttributeDefinition(name = "API Endpoint URL", description = "API Endpoint URL", type = AttributeType.STRING)
	    String api_endpoint_url() default "https://d1ebrt-pveapi.epsilonagilityloyalty.com/api/v1";

	    @AttributeDefinition(name = "Grant type", description = "Grant type", type = AttributeType.STRING)
	    String grant_type() default "password";

	    @AttributeDefinition(name = "username", description = "username", type = AttributeType.STRING)
	    String username() default "RAMESHKUMAR";

	    @AttributeDefinition(name = "Password", description = "Password", type = AttributeType.STRING)
	    String password() default "Ramesh@123456";

	    @AttributeDefinition(name = "Response Type", description = "Response Type", type = AttributeType.STRING)
	    String response_type() default "token";
}