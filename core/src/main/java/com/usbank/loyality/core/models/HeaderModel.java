package com.usbank.loyality.core.models;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.inherit.HierarchyNodeInheritanceValueMap;
import com.day.cq.commons.inherit.InheritanceValueMap;
import com.usbank.loyality.core.constants.GlobalConstants;
import com.usbank.loyality.core.utils.CommonUtil;
import com.usbank.loyality.core.utils.LinksUtil;
import com.usbank.loyality.core.vo.LinkVO;

/**
 * The Class HeaderModel.
 *
 * @author ajena
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HeaderModel {

	private static final Logger log = LoggerFactory
			.getLogger(HeaderModel.class);

	private String logoImage;
	private String logoAltText;
	private String logoUrl;
	private String logoNewWindow;
	
	private String accountLabel;
	private String accountLink;

	private List<LinkVO> listOfNavigations;

	@SlingObject
	private ResourceResolver resourceResolver;

	@SlingObject
	private Resource resource;

	@PostConstruct
	protected void init() {
		log.info("HeaderModel :: init :: Start");
		InheritanceValueMap iValueMap = new HierarchyNodeInheritanceValueMap(
				resource);

		getLogo(iValueMap);
		getPrimaryNav(iValueMap);

		log.info("HeaderModel :: init :: End");
	}

	private void getLogo(InheritanceValueMap iValueMap) {
		if (null != iValueMap) {
			logoImage = iValueMap.getInherited("logoImage", String.class);
			logoAltText = iValueMap.getInherited("logoAltText", String.class);
			logoUrl = LinksUtil.checkInternalURLByPath(
					iValueMap.getInherited("logoUrl", String.class),
					resourceResolver);
			logoNewWindow = CommonUtil.isNewWindow(iValueMap.getInherited(
					"logoNewWindow", GlobalConstants.FALSE));
			accountLabel = iValueMap.getInherited("accountLabel", String.class);
			accountLink = LinksUtil.checkInternalURLByPath(
					iValueMap.getInherited("accountLink", String.class),
					resourceResolver);
		}
	}

	private void getPrimaryNav(InheritanceValueMap iValueMap) {

		// Primary Navigation links reading logic start

		this.listOfNavigations = new ArrayList<>();
		String[] navigationfields = iValueMap.getInherited("primarynavfields",
				String[].class);
		try {
			if (navigationfields != null && navigationfields.length > 0) {
				for (String navfield : navigationfields) {
					LinkVO linkVO = (LinkVO) CommonUtil.getObjectFromJson(
							navfield, new LinkVO());
					linkVO.setLinkUrl(LinksUtil.checkInternalURLByPath(
							linkVO.getLinkUrl(), resourceResolver));
					linkVO.setLinkNewWindow(LinksUtil.isNewWindow(linkVO
							.getLinkNewWindow()));
					this.listOfNavigations.add(linkVO);
				}
			}
		} catch (Exception e) {
			log.error("HeaderModel :: getPrimaryNav :: JSONException :: {}", e);
		}
	}

	public String getLogoImage() {
		return logoImage;
	}

	public String getLogoAltText() {
		return logoAltText;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public String getLogoNewWindow() {
		return logoNewWindow;
	}

	public String getAccountLabel() {
		return accountLabel;
	}

	public String getAccountLink() {
		return accountLink;
	}
	
	public List<LinkVO> getListOfNavigations() {
		return listOfNavigations;
	}
}