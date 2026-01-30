package com.loraDuoMeter.Downlink_Service;

import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.loraDuoMeter.Controller.MqttClientManager;
import com.loraDuoMeter.Proj.Mqtt_Api_Key_Down_Proj;

@Service
public class DownlinkService {
	
	
	private final MqttApiKeyService apiKeyService;
    private final MqttClientManager clientManager;

    public DownlinkService(MqttApiKeyService apiKeyService, MqttClientManager clientManager) {
        this.apiKeyService = apiKeyService;
        this.clientManager = clientManager;
    }

	/*
	 * @Async public void sendDownlink(String gatewayId, String devEui, String
	 * payloadBase64) throws Exception {
	 * 
	 * // 1️⃣ Fetch credentials dynamically Mqtt_Api_Key_Down_Proj cfg =
	 * apiKeyService.getByGatewayId(gatewayId);
	 * 
	 * // 2️⃣ Build topic dynamically String topic = "v3/" + cfg.getUserName() +
	 * "/devices/" + devEui + "/down/push";
	 * 
	 * // 3️⃣ Get MQTT client (pooled per gateway) IMqttAsyncClient client =
	 * clientManager.getClient(cfg);
	 * 
	 * // 4️⃣ Publish payload String message = """ { "downlinks":[ { "f_port":2,
	 * "frm_payload":"%s", "priority":"NORMAL" } ] } """.formatted(payloadBase64);
	 * 
	 * client.publish(topic, message.getBytes(), 1, false); }
	 */
    
    public void sendDownlink(String gatewayId, String devEui, String payloadBase64) throws Exception {

        Mqtt_Api_Key_Down_Proj cfg = apiKeyService.getByGatewayId(gatewayId);

        String topic = "v3/" + cfg.getUserName()
                     + "/devices/" + devEui
                     + "/down/push";

        String message = """
        {
          "downlinks":[
            {
              "f_port":32,
              "frm_payload":"%s",
              "priority":"NORMAL"
            }
          ]
        }
        """.formatted(payloadBase64);

        IMqttAsyncClient client = clientManager.getClient(cfg);

        client.publish(topic, message.getBytes(), 1, false).waitForCompletion();

        System.out.println("✔ Sent to " + devEui + " payload=" + payloadBase64);
    }


}
