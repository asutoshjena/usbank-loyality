package com.usbank.loyality.core.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.usbank.loyality.core.constants.GlobalConstants;
import com.usbank.loyality.core.service.EhcacheManager;
import com.usbank.loyality.core.service.RESTService;
import com.usbank.loyality.core.service.impl.ApiConfigServiceImpl;
import com.usbank.loyality.core.service.impl.EhcacheConfigServiceImpl;

@Component(service = Servlet.class, property = {
		"sling.servlet.extensions=json", "sling.servlet.paths=/usbank/token",
		"sling.servlet.methods={'GET','POST'}" })
public class TokenServlet extends SlingAllMethodsServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8576734287774353771L;

	private static final Logger log = LoggerFactory
			.getLogger(TokenServlet.class);

	@Reference
	ApiConfigServiceImpl apiConfig;

	@Reference
	RESTService restService;
	
	@Reference
	EhcacheManager ehcacheManager;

	@Reference
	EhcacheConfigServiceImpl ehCacheConfig;

	@Override
	protected void doGet(final SlingHttpServletRequest request,
			final SlingHttpServletResponse response) throws ServletException,
			IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(final SlingHttpServletRequest request,
			final SlingHttpServletResponse response) throws ServletException,
			IOException {

		try {
			log.debug("Entered doPost");
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type",GlobalConstants.CONTENT_TYPE);
			headers.put("Accept-Language", GlobalConstants.ACCEPT_LANGUAGE);
			headers.put("Authorization", GlobalConstants.AUTHORIZATION);

			Map<String, String> params = new HashMap<String, String>();
			params.put("grant_type", apiConfig.getGrantType());
			params.put("username", apiConfig.getUsername());
			params.put("password", apiConfig.getPassword());
			params.put("response_type", apiConfig.getResponseType());

			String responseString = restService.makePostWSCall(
					apiConfig.getApiEndPointUrl() + GlobalConstants.TOKEN_URL,
					null, params, headers);
			response.getWriter().print(responseString);
		} catch (Exception e) {
			log.error("Exception", e);
		}

	}
}
