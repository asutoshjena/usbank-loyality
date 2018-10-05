package com.usbank.loyality.core.service.impl;

import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.usbank.loyality.core.service.EhcacheConfig;

/**
 * 
 *         This configuration factory service helps users to add any number of
 *         cache instances through Osgi configurations
 */

@Component(immediate = true, service = EhcacheConfigServiceImpl.class, property = {
        "process.label=US Bank Ehcache Configuration Service Impl",
        Constants.SERVICE_DESCRIPTION + "=This is a Service to read US Bank Ehcache Configuration",
        Constants.SERVICE_VENDOR + "=Epsilon"})
@Designate(ocd = EhcacheConfig.class)
public class EhcacheConfigServiceImpl {

	private static final Logger log = LoggerFactory.getLogger(EhcacheConfigServiceImpl.class);
	private EhcacheConfig configService;

	@Activate
	public void activate(EhcacheConfig config) {
		log.info("Activate method of EhcacheConfigServiceImpl");
		configService = config;
	}

	public String getCacheName() {
		return configService.cache_name();
	}

	public long getTimeToLive() {
		return configService.cache_timetolive();
	}

	public int getMaxElementsInMemory() {
		return configService.cache_maxelementsinmemory();
	}

	public long getTimeToIdle() {
		return configService.cache_timetoidle();
	}

}
