package com.usbank.loyality.core.service;

import java.util.Map;


/**
 * The Interface RESTService.
 */
public interface RESTService {
	
	/**
	 * Make get WS call.
	 *
	 * @param endPointUrl the end point url
	 * @param requestParams the request params
	 * @param headers the headers
	 * @return the string
	 */
	public String makeGetWSCall(String endPointUrl,
			Map<String, String> requestParams, Map<String, String> headers);

    /**
     * Make get WS call.
     *
     * @param endPointUrl the end point url
     * @param requestParams the request params
     * @return the string
     */
    public String makeGetWSCall(String endPointUrl, Map<String, String> requestParams);
    
    /**
     * Make post WS call.
     *
     * @param endPointUrl the end point url
     * @param requestBody the request body
     * @param requestParams the request params
     * @param headers the headers
     * @return the string
     */
    public String makePostWSCall(String endPointUrl, String requestBody, Map<String, String> requestParams,  Map<String, String> headers);
    
    /**
     * Make post WS call.
     *
     * @param endPointUrl the end point url
     * @param requestBody the request body
     * @param requestParams the request params
     * @return the string
     */
    public String makePostWSCall(String endPointUrl, String requestBody, Map<String, String> requestParams);
    
    /**
     * Make patch WS call.
     *
     * @param endPointUrl the end point url
     * @param requestBody the request body
     * @param requestParams the request params
     * @param headers the headers
     * @return the string
     */
    public String makePatchWSCall(String endPointUrl, String requestBody, Map<String, String> requestParams,  Map<String, String> headers);
    
    /**
     * Make patch WS call.
     *
     * @param endPointUrl the end point url
     * @param requestBody the request body
     * @param requestParams the request params
     * @return the string
     */
    public String makePatchWSCall(String endPointUrl, String requestBody, Map<String, String> requestParams);
    
	/**
	 * Gets the oauth token.
	 *
	 * @return the oauth token
	 */
	public String getOauthToken();

}
