package com.usbank.loyality.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.usbank.loyality.core.constants.GlobalConstants;

/**
 * @author ajena
 */
public class CommonUtil {
    private CommonUtil() {
        //Restricting Instantiation
    }

    /**
     * Logger configuration for CommonUtil
     */
    private static final Logger log = LoggerFactory.getLogger(CommonUtil.class);

    public static Object getObjectFromJson(String jsonString, Object obj) {
        Gson gson = new Gson();
        Object returnValue = null;
        try {
            returnValue = gson.fromJson(jsonString, obj.getClass());
        } catch (Exception e) {
            log.error("Exception occured in CommonUtil :: getObjectFromJson {}", e);
        }
        return returnValue;
    }
    
    /**
     * Checks if is new window.
     *
     * @param newWindow the new window
     * @return the string
     */
    public static String isNewWindow(String newWindow) {
        log.debug("In isNewWindow method of CommonUtil component.");
        if (StringUtils.isNotBlank(newWindow) && newWindow.equalsIgnoreCase(GlobalConstants.TRUE)) {
            return GlobalConstants.TARGET_BLANK;
        } else {
            return GlobalConstants.TARGET_SELF;
        }
    }

}
