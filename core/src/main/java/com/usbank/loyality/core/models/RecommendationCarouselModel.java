package com.usbank.loyality.core.models;

import com.usbank.loyality.core.utils.CommonUtil;
import com.usbank.loyality.core.utils.LinksUtil;
import com.usbank.loyality.core.vo.LinkVO;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class RecommendationCarouselModel {
    private static final Logger log = LoggerFactory.getLogger(RecommendationCarouselModel.class);

    @SlingObject
    private ResourceResolver resourceResolver;

    @ValueMapValue
    private String[] recommendationList;

    private List<LinkVO> carouselList;

    @PostConstruct
    protected void init() {
        log.info("RecommendationCarouselModel :: init :: Start");
        if (ArrayUtils.isNotEmpty(recommendationList)) {
            carouselList = new ArrayList<>();
            for (String carousel : recommendationList) {
                LinkVO linkVO = (LinkVO) CommonUtil.getObjectFromJson(carousel, new LinkVO());
                linkVO.setLinkUrl(LinksUtil.checkInternalURLByPath(linkVO.getLinkUrl(), resourceResolver));
                linkVO.setLinkNewWindow(LinksUtil.isNewWindow(linkVO.getLinkNewWindow()));
                linkVO.setLinkName(linkVO.getLinkName());
                linkVO.setPath(linkVO.getPath());
                carouselList.add(linkVO);
            }
        }
        log.info("RecommendationCarouselModel :: init :: End");
    }

    public List<LinkVO> getCarouselList() {
        return carouselList;
    }
}
