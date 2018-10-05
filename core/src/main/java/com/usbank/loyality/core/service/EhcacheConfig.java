package com.usbank.loyality.core.service;


import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "US Bank EhCache Configuration Service", description = "Configuration Service for US Bank EhCache")
	public @interface EhcacheConfig {

	@AttributeDefinition(name = "Cache Name", description = "Cache Name", type = AttributeType.STRING)
	String cache_name() default "basicCache";

	@AttributeDefinition(name = "Time to live in Seconds", description = "Time to Live", type = AttributeType.LONG)
	long cache_timetolive() default 3600;

	@AttributeDefinition(name = "Max elements in Memory", description = "Maximum Number of Elements", type = AttributeType.LONG)
	int cache_maxelementsinmemory() default 100000;

	@AttributeDefinition(name = "Time to idle in Seconds", description = "Time to Idle", type = AttributeType.LONG)
	long cache_timetoidle() default 360;
}