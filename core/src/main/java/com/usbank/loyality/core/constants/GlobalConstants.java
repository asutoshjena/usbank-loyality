package com.usbank.loyality.core.constants;

/**
 * Contains the global constants.
 *
 * @author ajena
 */
public class GlobalConstants {

    /**
     * Instantiates a new global constants.
     */
    private GlobalConstants() {
        // Restricting Instantiation
    }

    /** The Constant NT_UNSTRUCTURED. */
    public static final String NT_UNSTRUCTURED = "nt:unstructured";

    /** The Constant SLING_RESOURCE_TYPE. */
    public static final String SLING_RESOURCE_TYPE = "sling:resourceType";

    /** The Constant JCR_PRIMARY_TYPE. */
    public static final String JCR_PRIMARY_TYPE = "jcr:primaryType";

    /** The Constant JCR_TITLE. */
    public static final String JCR_TITLE = "jcr:title";

    /** The Constant CQ_PAGE. */
    public static final String CQ_PAGE = "cq:Page";

    /** The Constant CQ_TAGS. */
    public static final String CQ_TAGS = "cq:tags";

    /** The Constant FALSE. */
    public static final String FALSE = "false";

    /** The Constant TRUE. */
    public static final String TRUE = "true";

    /** The Constant HASH. */
    public static final String HASH = "#";

    /** The Constant FORWARD_SLASH. */
    public static final String FORWARD_SLASH = "/";

    /** The Constant TARGET_SELF. */
    public static final String TARGET_SELF = "_self";

    /** The Constant TARGET_BLANK. */
    public static final String TARGET_BLANK = "_blank";

    /** The Constant DOT_HTML. */
    public static final String DOT_HTML = ".html";

    /** The Constant HTTP_PROTOCOL. */
    public static final String HTTP_PROTOCOL = "http://";

    /** The Constant HTTPS_PROTOCOL. */
    public static final String HTTPS_PROTOCOL = "https://";

    /** The Constant SLASH_CONTENT. */
    public static final String SLASH_CONTENT = "/content";

    /** The Constant QUERY_STRING. */
    public static final String QUERY_STRING = "?";
    
    /** The Constant UTF_8. */
    //API Constants
    public static final String UTF_8 = "UTF-8";
    
    /** The Constant CONTENT_TYPE. */
    public static final String CONTENT_TYPE = "application/x-www-form-urlencoded";
    
    /** The Constant ACCEPT_LANGUAGE. */
    public static final String ACCEPT_LANGUAGE = "en-US";
    
    /** The Constant AUTHORIZATION. */
    public static final String AUTHORIZATION = "Basic V0VCQVBJX0tFWTpBNjk5WTg5cnI3";
    
    /** The Constant TOKEN_URL. */
    public static final String TOKEN_URL = "/authorization/tokens";
    
    /** The Constant LOYALITY_USER_URL. */
    public static final String LOYALITY_USER_URL = "/security/users/";
    
    /** The Constant TOKEN_CACHE_NAME. */
    public static final String TOKEN_CACHE_NAME = "AccessToken";

}
