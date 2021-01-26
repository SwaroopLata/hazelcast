# hazelcast-spring-boot-issue

## Prerequisite

Java 8 (http://www.oracle.com/technetwork/java/)

## Clone the application
https://github.com/SwaroopLata/hazelcast.git

## How to build and run the application

### Build and run from your favourite IDE
Open the code in the editor  
Put a debugger in below line in HazelcastConfig.configuration() method
MapConfig mapConfig = new MapConfig(MAP_CONFIG_NAME);

Debug the main class SharedModuleApplication.java   

Once the debugger reached in line MapConfig mapConfig = new MapConfig(MAP_CONFIG_NAME)
hover and evaluate  private HazelCastProperties hcProperties . Expand the hcProperties variable 
and for both the maps map1 and map2 , you will find time-to-live-seconds and max-idle-seconds are set properly as passed from application.yaml 
But the EvictionConfig object evictionPolicy is set to NONE and maxSizePolicy set to PER NODE ,
however it should be set as passed from application.yaml i.e. evictionPolicy should be set to LRU and 
maxSizePolicy should be set to USED_HEAP_PERCENTAGE










  







