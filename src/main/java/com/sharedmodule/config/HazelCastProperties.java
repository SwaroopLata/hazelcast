package com.sharedmodule.config;


import com.hazelcast.config.MapConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@EnableConfigurationProperties
@ConfigurationProperties("hazelcast")
class HazelCastProperties {

    private Map<String, MapConfig> map;
}




