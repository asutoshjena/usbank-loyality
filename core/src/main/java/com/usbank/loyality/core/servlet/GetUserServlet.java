package com.usbank.loyality.core.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drew.lang.annotations.NotNull;
import com.usbank.loyality.core.constants.GlobalConstants;
import com.usbank.loyality.core.service.EhcacheManager;
import com.usbank.loyality.core.service.RESTService;
import com.usbank.loyality.core.service.impl.ApiConfigServiceImpl;
import com.usbank.loyality.core.service.impl.EhcacheConfigServiceImpl;

/**
 * The Class GetUserServlet.
 */
@Component(service = Servlet.class, property = {
		"sling.servlet.extensions=json",
		"sling.servlet.paths=/usbank/loyalityUser",
		"sling.servlet.methods={'GET','POST'}" })
public class GetUserServlet extends SlingAllMethodsServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8576734287774353771L;

	/** The Constant log. */
	private static final Logger log = LoggerFactory
			.getLogger(GetUserServlet.class);

	/** The api config. */
	@Reference
	ApiConfigServiceImpl apiConfig;

	/** The rest service. */
	@Reference
	RESTService restService;

	/** The ehcache manager. */
	@Reference
	EhcacheManager ehcacheManager;

	/** The eh cache config. */
	@Reference
	EhcacheConfigServiceImpl ehCacheConfig;

	/* (non-Javadoc)
	 * @see org.apache.sling.api.servlets.SlingSafeMethodsServlet#doGet(org.apache.sling.api.SlingHttpServletRequest, org.apache.sling.api.SlingHttpServletResponse)
	 */
	@Override
	protected void doGet(@NotNull final SlingHttpServletRequest request,
			@NotNull final SlingHttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/* (non-Javadoc)
	 * @see org.apache.sling.api.servlets.SlingAllMethodsServlet#doPost(org.apache.sling.api.SlingHttpServletRequest, org.apache.sling.api.SlingHttpServletResponse)
	 */
	@Override
	protected void doPost(@NotNull final SlingHttpServletRequest request,
			@NotNull final SlingHttpServletResponse response)
			throws ServletException, IOException {

		try {
			log.info("Entered GetUser");
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", GlobalConstants.CONTENT_TYPE);
			headers.put("Accept-Language", GlobalConstants.ACCEPT_LANGUAGE);
			String cacheName = ehCacheConfig.getCacheName();
			String token = (String) ehcacheManager.getElementFromCache(
					cacheName, GlobalConstants.TOKEN_CACHE_NAME);
			if (StringUtils.isEmpty(token)) {
				token = restService.getOauthToken();
				log.info("Token fetched from API not available in cache");
			}
			headers.put("Authorization", "OAuth " + token);

			// headers.put("Authorization",
			// "OAuth e19a73c4-ea65-48a4-8edb-3fc8d337201f");

			String username = request.getParameter("username");
			Map<String, String> params = new HashMap<String, String>();
			params.put("username", username);

			String responseString = restService.makeGetWSCall(
					apiConfig.getApiEndPointUrl()
							+ GlobalConstants.LOYALITY_USER_URL + username,
					params, headers);
			response.getWriter().print(responseString);
		} catch (Exception e) {
			log.error("Exception", e);
		}

	}
}
