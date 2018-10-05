package com.usbank.loyality.core.service.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.osgi.framework.Constants;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.usbank.loyality.core.service.EhcacheManager;


/**
 *         Service class to create/update/delete Ehcache dynamically with the
 *         help of OSGi Configurations, that can provided with the
 *         <class>EhcacheConfig</class>
 */

@Component(immediate = true, service = EhcacheManager.class, property = {
		"process.label=US Bank Ehcache Service Consumer",
		Constants.SERVICE_DESCRIPTION + "=US Bank Ehcache Service Consumer" })
public class EhcacheManagerImpl implements EhcacheManager {

	private static final Logger log = LoggerFactory.getLogger(EhcacheManagerImpl.class);

	private Map<String, EhcacheConfigServiceImpl> ehcacheConfigs = new ConcurrentHashMap<String, EhcacheConfigServiceImpl>();

	private CacheManager cacheManager;

	@Reference(service = EhcacheConfigServiceImpl.class, bind = "bindEhcacheConfig", cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC, name = "Cache configurations", unbind = "unbindEhcacheConfig")
	protected final void bindEhcacheConfig(EhcacheConfigServiceImpl config) {
		log.info(" Binding cache config {}", config.getCacheName());
		ehcacheConfigs.put(config.getCacheName(), config);
		log.info("No of Configs Available {}", ehcacheConfigs.size());
		addOrUpdateCache(config);
	}

	protected final void unbindEhcacheConfig(EhcacheConfigServiceImpl config) {
		ehcacheConfigs.remove(config.getCacheName());
		removeCache(config);
	}
	
	@Activate
	public void activate(ComponentContext componentContext) {
		log.info(" Creating a cache manager instance on activate");
		if (cacheManager == null) {
			cacheManager = CacheManager.create();
		}
	}

	@Deactivate
	public void deactivate(ComponentContext componentContext) {
		removeAllCaches();
	}

	private void addOrUpdateCache(EhcacheConfigServiceImpl config) {
		log.debug(" Adding / Updating Cache {}", config.getCacheName());
		if (cacheManager == null) {
			log.debug(" New Cache manager", config.getCacheName());
			cacheManager = CacheManager.create();
		} else {
			log.debug(" Old Cache manager");
		}
		Cache cache = new Cache(config.getCacheName(), config.getMaxElementsInMemory(), false, false,
				config.getTimeToLive(), config.getTimeToIdle());
		cacheManager.addCache(cache);
	}

	private void removeCache(final EhcacheConfigServiceImpl config) {
		log.info("Removing Cache {}", config.getCacheName());
		removeCache(config.getCacheName());
	}

	public Cache getCache(String cacheName) {
		return this.cacheManager.getCache(cacheName);
	}

	@Override
	public boolean addElementToCache(String cacheName, Object key, Object value) {
		Cache cache = getCache(cacheName);
		if (cache == null) {
			log.warn("No cache found with the name {}", cacheName);
			return false;
		}
		cache.put(new Element(key, value));
		return true;
	}
	@Override
	public boolean removeElementFromCache(String cacheName, Object key) {
		Cache cache = getCache(cacheName);
		if (cache == null) {
			log.warn("No cache found with the name {}", cacheName);
			return false;
		}
		cache.remove(key);
		return true;
	}

	@Override
	public Object getElementFromCache(String cacheName, Object key) {
		Cache cache = getCache(cacheName);
		if (cache != null) {
			Element element = cache.get(key);
			return element != null ? element.getObjectValue() : null;
		}
		return null;
	}
	
	@Override
	public void removeAllCaches() {
		if (this.cacheManager != null) {
			this.cacheManager.clearAll();
		}
	}

	@Override
	public void removeCache(String cacheName) {
		if (this.cacheManager != null) {
			this.cacheManager.removeCache(cacheName);
		}
	}

}
