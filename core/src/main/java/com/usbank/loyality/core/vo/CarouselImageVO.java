package com.usbank.loyality.core.vo;

public class CarouselImageVO {
    private String image;

	private String imageMobile;

	private String label;

	private String alt;
    
    private String linkUrl;
    
    public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getImageMobile() {
		return imageMobile;
	}
	public void setImageMobile(String imageMobile) {
		this.imageMobile = imageMobile;
	}
	public String getAlt() {
		return alt;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public void setAlt(String alt) {
		this.alt = alt;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
}
