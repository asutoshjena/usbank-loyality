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
public class NewItemModel {

    private static final Logger log = LoggerFactory.getLogger(NewItemModel.class);

    @SlingObject
    private ResourceResolver resourceResolver;

    @ValueMapValue
    private String[] itemTiles;

    @ValueMapValue
    private String ctaUrl;

    @ValueMapValue
    private String newWindow;

    private List<LinkVO> itemList;

    @PostConstruct
    protected void init() {
        log.info("NewItemModel :: init :: Start");
        if (ArrayUtils.isNotEmpty(itemTiles)) {
            itemList = new ArrayList<>();
            for (String item : itemTiles) {
                LinkVO linkVO = (LinkVO) CommonUtil.getObjectFromJson(item, new LinkVO());
                linkVO.setLinkUrl(LinksUtil.checkInternalURLByPath(linkVO.getLinkUrl(), resourceResolver));
                linkVO.setLinkNewWindow(LinksUtil.isNewWindow(linkVO.getLinkNewWindow()));
                linkVO.setLinkName(linkVO.getLinkName());
                linkVO.setPath(linkVO.getPath());
                linkVO.setTitle(linkVO.getTitle());
                itemList.add(linkVO);
            }
        }
        log.info("NewItemModel :: init :: End");
    }

    public String getCtaUrl() {
        return LinksUtil.checkInternalURLByPath(ctaUrl, resourceResolver);
    }

    public String getNewWindow() {
        return CommonUtil.isNewWindow(newWindow);
    }

    public List<LinkVO> getItemList() {
        return itemList;
    }
}
