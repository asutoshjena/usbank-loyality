package com.usbank.loyality.core.service;

import net.sf.ehcache.Cache;

public interface EhcacheManager {

	public Cache getCache(String cacheName);
	
	public boolean addElementToCache(String cacheName, Object key, Object value);
	
	public Object getElementFromCache(String cacheName, Object key);

	public void removeCache(String cacheName);

	boolean removeElementFromCache(String cacheName, Object key);
	
	public void removeAllCaches();

}
