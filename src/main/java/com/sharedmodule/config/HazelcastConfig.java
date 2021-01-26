package com.sharedmodule.config;

import com.hazelcast.config.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class HazelcastConfig {

    private static final String MAP_CONFIG_NAME = "default";

    private static final int MAP_CONFIG_TTL = 1800;

    private static final int MAX_IDLE_SECS = 120;

    private static final int SIZE = 10;

    private static final String EVICTION_POLICY = "LRU";


    @Value("${hazelcast.cache.instance-name:hazelcast-instance}")
    private String cacheInstanceName;

    @Autowired
    private HazelCastProperties hcProperties;


    @Bean
    public Config configuration() {
        MapConfig mapConfig = new MapConfig(MAP_CONFIG_NAME);
        mapConfig.setTimeToLiveSeconds(MAP_CONFIG_TTL);
        mapConfig.setMaxIdleSeconds(MAX_IDLE_SECS);
        EvictionConfig evictionConfig = mapConfig.getEvictionConfig();
        evictionConfig.setEvictionPolicy(EvictionPolicy.valueOf(EVICTION_POLICY))
                .setMaxSizePolicy(MaxSizePolicy.USED_HEAP_PERCENTAGE)
                .setSize(SIZE);
        Config config = new Config().setClusterName(cacheInstanceName);
        config.setInstanceName(cacheInstanceName).addMapConfig(mapConfig);
        if (hcProperties != null && hcProperties.getMap() != null) {
            for (Map.Entry<String, MapConfig> entry : hcProperties.getMap().entrySet()) {
                String mapName = entry.getKey();
                MapConfig mapConfigVal = entry.getValue();
                mapConfigVal.setName(mapName);
                //deserializing the Map Eviction Policy from application.yaml is not working
                //so currently setting Eviction Policy to hardcoded values as defined above
                EvictionConfig evConfig = mapConfig.getEvictionConfig();
                evConfig.setEvictionPolicy(EvictionPolicy.valueOf(EVICTION_POLICY))
                        .setMaxSizePolicy(MaxSizePolicy.USED_HEAP_PERCENTAGE)
                        .setSize(SIZE);
                mapConfigVal.setEvictionConfig(evConfig);
                config.addMapConfig(mapConfigVal);

            }
        }

        return config;
    }


}
