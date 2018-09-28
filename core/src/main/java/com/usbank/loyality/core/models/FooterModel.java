package com.usbank.loyality.core.models;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.inherit.HierarchyNodeInheritanceValueMap;
import com.day.cq.commons.inherit.InheritanceValueMap;
import com.usbank.loyality.core.utils.CommonUtil;
import com.usbank.loyality.core.utils.LinksUtil;
import com.usbank.loyality.core.vo.LinkVO;

/**
 * This class will read the footer configuration, prepares a list and sends it
 * to HTL.
 *
 * @author ajena
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FooterModel {
    /**
     * Logger configuration for FooterModel
     */
    private static final Logger log = LoggerFactory.getLogger(FooterModel.class);

    /*
     * The ResourceResolver Object
     */
    @SlingObject
    private ResourceResolver resourceResolver;

    /*
     * The Resource Object
     */
    @SlingObject
    private Resource resource;

    private List<LinkVO> footerList;

    /**
     * This method reads the footer multi field values
     * and prepares the list.
     */
    @PostConstruct
    protected void init() {
        log.info("FooterModel :: init :: Start");
        InheritanceValueMap iValueMap = new HierarchyNodeInheritanceValueMap(resource);
        String[] footerLinkList = iValueMap.getInherited("footerLinkList", String[].class);
        if (ArrayUtils.isNotEmpty(footerLinkList)) {
            footerList = new ArrayList<>();
            for (String footer : footerLinkList) {
                LinkVO linkVO = (LinkVO) CommonUtil.getObjectFromJson(footer, new LinkVO());
                linkVO.setLinkUrl(LinksUtil.checkInternalURLByPath(linkVO.getLinkUrl(), resourceResolver));
                linkVO.setLinkNewWindow(LinksUtil.isNewWindow(linkVO.getLinkNewWindow()));
                footerList.add(linkVO);
            }
        } else {
            log.error("FooterModel :: Empty List");
        }
        log.info("FooterModel :: init :: End");
    }

    public List<LinkVO> getFooterList() {
        return footerList;
    }
}
