package com.usbank.loyality.core.vo;

public class LinkVO {
    private String linkName;
    private String linkUrl;
    private String linkNewWindow;
	private String path;

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getLinkNewWindow() {
        return linkNewWindow;
    }

    public void setLinkNewWindow(String linkNewWindow) {
        this.linkNewWindow = linkNewWindow;
    }
    public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
