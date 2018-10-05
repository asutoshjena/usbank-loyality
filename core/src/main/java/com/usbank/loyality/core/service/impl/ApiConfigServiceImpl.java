package com.usbank.loyality.core.service.impl;

import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

import com.usbank.loyality.core.service.ApiConfig;

/**
 * @author pannem
 *         The implementation class for  Agility Loyality config, which will read all the
 *         configuration
 */
@Component(immediate = true, service = ApiConfigServiceImpl.class, property = {
        "process.label=Agility Loyality Configuration Service Impl",
        Constants.SERVICE_DESCRIPTION + "=This is a Service to read Venetian Booking Configuration",
        Constants.SERVICE_VENDOR + "=Epsilon"})
@Designate(ocd = ApiConfig.class)
public class ApiConfigServiceImpl {

    private ApiConfig configService;

    @Activate
    protected void activate(ApiConfig config) {
        configService = config;
    }
    
	public String getApiEndPointUrl() {
		return configService.api_endpoint_url();
	}

	public String getGrantType() {
		return configService.grant_type();
	}

	public String getUsername() {
		return configService.username();
	}

	public String getPassword() {
		return configService.password();
	}

	public String getResponseType() {
		return configService.response_type();
	}
}
