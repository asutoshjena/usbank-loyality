package com.usbank.loyality.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.usbank.loyality.core.constants.GlobalConstants;
import com.usbank.loyality.core.service.EhcacheManager;
import com.usbank.loyality.core.service.RESTService;
import com.usbank.loyality.core.utils.CommonUtil;

@Component(
		immediate =  true,
	    service = RESTService.class,
	    property = {
	    	"process.label=Booking Engine REST Service Consumer",
	    	 Constants.SERVICE_DESCRIPTION + "=Booking Engine REST Service Consumer"
	    }
)
public class RESTServiceImpl implements RESTService {

	private static final Logger log = LoggerFactory
			.getLogger(RESTServiceImpl.class);
	
	@Reference
	ApiConfigServiceImpl apiConfig;
	
	@Reference
	EhcacheManager ehcacheManager;

	@Reference
	EhcacheConfigServiceImpl ehCacheConfig;

	@Override
	public String makeGetWSCall(String endPointUrl,
			Map<String, String> requestParams, Map<String, String> headers) {
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			if (null != requestParams && !requestParams.isEmpty()) {
				String params = "";
				int size = requestParams.size();
				int index = 0;
				for (String param : requestParams.keySet()) {
					if(index++ == size - 1)
						params = params + param + "=" + requestParams.get(param);
					else
						params = params + param + "=" + requestParams.get(param) + "&";
				}
				endPointUrl = endPointUrl.contains("?") ? endPointUrl + "&"
						+ params : endPointUrl + "?" + params;
			}
			log.debug("endPointUrl::::::"+endPointUrl);
			HttpGet get = new HttpGet(endPointUrl);

			if (null != headers && !headers.isEmpty()) {
				for (String header : headers.keySet()) {
					get.addHeader(header, headers.get(header));
				}
			}

			CloseableHttpResponse response = client.execute(get);
			int statusCode = response.getStatusLine().getStatusCode();
			log.debug("Status code is ::" + statusCode);
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity, GlobalConstants.UTF_8);
		} catch (Exception e) {
			log.error("Exception", e);
		}
		return "";
	}

	@Override
	public String makeGetWSCall(String endPointUrl,
			Map<String, String> requestParams){
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type",GlobalConstants.CONTENT_TYPE);
		headers.put("Accept-Language", GlobalConstants.ACCEPT_LANGUAGE);
		return makeGetWSCall(endPointUrl, requestParams, headers);
	}
	
	@Override
	public String makePostWSCall(String endPointUrl, String requestBody,
			Map<String, String> requestParams,  Map<String, String> headers){
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			HttpPost post = new HttpPost(endPointUrl);
			if (null != headers && !headers.isEmpty()) {
				for (String header : headers.keySet()) {
					post.addHeader(header, headers.get(header));
				}
			}

			if (StringUtils.isNotEmpty(requestBody)) {
				StringEntity stringEntity = new StringEntity(requestBody,
						ContentType.APPLICATION_JSON);
				post.setEntity(stringEntity);
			} else if (null != requestParams && !requestParams.isEmpty()) {
				List<BasicNameValuePair> parametersBody = getParametersBody(requestParams);
				post.setEntity(new UrlEncodedFormEntity(parametersBody));
			}

			CloseableHttpResponse response = client.execute(post);
			int statusCode = response.getStatusLine().getStatusCode();
			log.info("Post request status code is ::{} ==> {}" , statusCode,response.getStatusLine().getReasonPhrase());
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity, GlobalConstants.UTF_8);
		} catch (Exception e) {
			log.error("Exception post request", e);
		}
		return "";
	}

	private List<BasicNameValuePair> getParametersBody(
			Map<String, String> requestParams) {
		List<BasicNameValuePair> parametersBody = requestParams
				.entrySet()
				.parallelStream()
				.map(entry -> {
					return new BasicNameValuePair(entry.getKey(), entry
							.getValue());
				}).collect(Collectors.toList());
		return parametersBody;
	}

	@Override
	public String makePostWSCall(String endPointUrl, String requestBody,
			Map<String, String> requestParams){
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type",GlobalConstants.CONTENT_TYPE);
		headers.put("Accept-Language", GlobalConstants.ACCEPT_LANGUAGE);
		return makePostWSCall(endPointUrl, requestBody, requestParams, headers);
	}
	
	@Override
	public String makePatchWSCall(String endPointUrl, String requestBody,
			Map<String, String> requestParams,  Map<String, String> headers){
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			HttpPatch patch = new HttpPatch(endPointUrl);
			if (null != headers && !headers.isEmpty()) {
				for (String header : headers.keySet()) {
					patch.addHeader(header, headers.get(header));
				}
			}

			if (StringUtils.isNotEmpty(requestBody)) {
				StringEntity stringEntity = new StringEntity(requestBody,
						ContentType.APPLICATION_JSON);
				patch.setEntity(stringEntity);
			} else if (null != requestParams && !requestParams.isEmpty()) {
				List<BasicNameValuePair> parametersBody = getParametersBody(requestParams);
				patch.setEntity(new UrlEncodedFormEntity(parametersBody));
			}

			CloseableHttpResponse response = client.execute(patch);
			int statusCode = response.getStatusLine().getStatusCode();
			log.info("Patch request status code is ::" + statusCode);
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity, GlobalConstants.UTF_8);
		} catch (Exception e) {
			log.error("Exception patch request", e);
		}
		return "";
	}

	@Override
	public String makePatchWSCall(String endPointUrl, String requestBody,
			Map<String, String> requestParams){
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type",GlobalConstants.CONTENT_TYPE);
		headers.put("Accept-Language", GlobalConstants.ACCEPT_LANGUAGE);
		return makePatchWSCall(endPointUrl, requestBody, requestParams, headers);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String getOauthToken(){
		log.info("Entered getOauthtoken");
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type",GlobalConstants.CONTENT_TYPE);
		headers.put("Accept-Language", GlobalConstants.ACCEPT_LANGUAGE);
		headers.put("Authorization", GlobalConstants.AUTHORIZATION);

		Map<String, String> params = new HashMap<String, String>();
		params.put("grant_type", apiConfig.getGrantType());
		params.put("username", apiConfig.getUsername());
		params.put("password", apiConfig.getPassword());
		params.put("response_type", apiConfig.getResponseType());

		String responseString = makePostWSCall(
				apiConfig.getApiEndPointUrl() + GlobalConstants.TOKEN_URL,
				null, params, headers);
		Map<String,String> tokenJson = (Map<String,String>)CommonUtil.getObjectFromJson(responseString,  new HashMap<String, String>());
		String token = tokenJson.containsKey("AccessToken")?tokenJson.get("AccessToken"):"";
		if(StringUtils.isNotEmpty(token)){
			ehcacheManager.addElementToCache(ehCacheConfig.getCacheName(), GlobalConstants.TOKEN_CACHE_NAME, token);
		}
		return token;	
	}
}
