package com.usbank.loyality.core.constants;

/**
 * Contains the global constants.
 *
 * @author ajena
 */
public class GlobalConstants {

    private GlobalConstants() {
        // Restricting Instantiation
    }

    public static final String NT_UNSTRUCTURED = "nt:unstructured";

    public static final String SLING_RESOURCE_TYPE = "sling:resourceType";

    public static final String JCR_PRIMARY_TYPE = "jcr:primaryType";

    public static final String JCR_TITLE = "jcr:title";

    public static final String CQ_PAGE = "cq:Page";

    public static final String CQ_TAGS = "cq:tags";

    public static final String FALSE = "false";

    public static final String TRUE = "true";

    public static final String HASH = "#";

    public static final String FORWARD_SLASH = "/";

    public static final String TARGET_SELF = "_self";

    public static final String TARGET_BLANK = "_blank";

    public static final String DOT_HTML = ".html";

    public static final String HTTP_PROTOCOL = "http://";

    public static final String HTTPS_PROTOCOL = "https://";

    public static final String SLASH_CONTENT = "/content";

    public static final String QUERY_STRING = "?";
    
    //API Constants
    public static final String UTF_8 = "UTF-8";
    
    public static final String CONTENT_TYPE = "application/x-www-form-urlencoded";
    
    public static final String ACCEPT_LANGUAGE = "en-US";
    
    public static final String AUTHORIZATION = "Basic V0VCQVBJX0tFWTpBNjk5WTg5cnI3";
    
    public static final String TOKEN_URL = "/authorization/tokens";
    
    public static final String LOYALITY_USER_URL = "/security/users/";
    
    public static final String TOKEN_CACHE_NAME = "AccessToken";

}
