package com.usbank.loyality.core.models;

import com.usbank.loyality.core.utils.CommonUtil;
import com.usbank.loyality.core.utils.LinksUtil;
import com.usbank.loyality.core.vo.ReedemCarouselVO;
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
public class ReedemCarouselModel {

    private static final Logger log = LoggerFactory.getLogger(ReedemCarouselModel.class);

    @SlingObject
    private ResourceResolver resourceResolver;

    @ValueMapValue
    private String[] reedemTilesList;

    @ValueMapValue
    private String[] thumbnailItemList;

    private List<ReedemCarouselVO> reedemCarouselList;

    private List<ReedemCarouselVO> thumbnailList;

    @PostConstruct
    protected void init() {
        log.info("ReedemCarouselModel :: init :: Start");
        if (ArrayUtils.isNotEmpty(reedemTilesList)) {
            reedemCarouselList = new ArrayList<>();
            for (String reedemTile : reedemTilesList) {
                ReedemCarouselVO reedemCarouselVO = (ReedemCarouselVO) CommonUtil.getObjectFromJson(reedemTile, new ReedemCarouselVO());
                reedemCarouselVO.setCtaUrl(LinksUtil.checkInternalURLByPath(reedemCarouselVO.getCtaUrl(), resourceResolver));
                reedemCarouselVO.setNewWindow(CommonUtil.isNewWindow(reedemCarouselVO.getNewWindow()));
                reedemCarouselList.add(reedemCarouselVO);
            }
        } else {
            log.error("No Carousel Items Configured");
        }
        if (ArrayUtils.isNotEmpty(thumbnailItemList)) {
            thumbnailList = new ArrayList<>();
            for (String thumbnail : thumbnailItemList) {
                ReedemCarouselVO carouselVO = (ReedemCarouselVO) CommonUtil.getObjectFromJson(thumbnail, new ReedemCarouselVO());
                thumbnailList.add(carouselVO);
            }
        } else {
            log.error("No Thumbnails Configugred");
        }
        log.info("ReedemCarouselModel :: init :: End");
    }

    public List<ReedemCarouselVO> getReedemCarouselList() {
        return reedemCarouselList;
    }

    public List<ReedemCarouselVO> getThumbnailList() {
        return thumbnailList;
    }

}
