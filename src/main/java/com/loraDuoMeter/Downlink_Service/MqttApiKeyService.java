package com.loraDuoMeter.Downlink_Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

import com.loraDuoMeter.Proj.Mqtt_Api_Key_Down_Proj;
import com.loraDuoMeter.Repo.IMqtt_Api_Key_Repo;

@Service
public class MqttApiKeyService {
	
	 private final IMqtt_Api_Key_Repo repo;

	    private final Map<String, Mqtt_Api_Key_Down_Proj> cache = new ConcurrentHashMap<>();

	    public MqttApiKeyService(IMqtt_Api_Key_Repo repo) {
	        this.repo = repo;
	    }

	    public Mqtt_Api_Key_Down_Proj getByGatewayId(String gatewayId) {
	        return cache.computeIfAbsent(gatewayId, id ->
	            repo.findByGatewayId(id)
	                .orElseThrow(() -> 
	                    new RuntimeException("MQTT config not found for gateway: " + id)
	                )
	        );

}
}
