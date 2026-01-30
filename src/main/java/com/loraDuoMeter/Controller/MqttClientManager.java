package com.loraDuoMeter.Controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.stereotype.Component;

import com.loraDuoMeter.Proj.Mqtt_Api_Key_Down_Proj;

@Component
public class MqttClientManager {

    private final Map<String, IMqttAsyncClient> clients = new ConcurrentHashMap<>();

    // Original method
    public IMqttAsyncClient getClient(
            String gatewayId,
            String brokerUrl,
            String username,
            String apiKey
    ) {

        return clients.computeIfAbsent(gatewayId, g -> {
            try {
                IMqttAsyncClient client =
                        new MqttAsyncClient(brokerUrl, "gw-" + gatewayId);

                MqttConnectOptions opt = new MqttConnectOptions();
                opt.setUserName(username);
                opt.setPassword(apiKey.toCharArray());
                opt.setAutomaticReconnect(true);
                opt.setCleanSession(false);

                client.connect(opt).waitForCompletion();
                return client;

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    // New overload to accept your projection
    public IMqttAsyncClient getClient(Mqtt_Api_Key_Down_Proj cfg) {
        return getClient(cfg.getGatewayId(), "tcp://" + cfg.getBrokerIpAddress() + ":" + cfg.getPortNumber(), cfg.getUserName(),
            cfg.getApiKey()
        );
    }
}
