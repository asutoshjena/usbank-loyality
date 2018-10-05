package com.usbank.loyality.core.service;

import java.util.Map;


public interface RESTService {
	
	public String makeGetWSCall(String endPointUrl,
			Map<String, String> requestParams, Map<String, String> headers);

    public String makeGetWSCall(String endPointUrl, Map<String, String> requestParams);
    
    public String makePostWSCall(String endPointUrl, String requestBody, Map<String, String> requestParams,  Map<String, String> headers);
    
    public String makePostWSCall(String endPointUrl, String requestBody, Map<String, String> requestParams);
    
    public String makePatchWSCall(String endPointUrl, String requestBody, Map<String, String> requestParams,  Map<String, String> headers);
    
    public String makePatchWSCall(String endPointUrl, String requestBody, Map<String, String> requestParams);
    
	public String getOauthToken();

}
