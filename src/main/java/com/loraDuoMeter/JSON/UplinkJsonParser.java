package com.loraDuoMeter.JSON;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loraDuoMeter.DTO.MQTT_Listner_Receive_Data_Dto;

public class UplinkJsonParser {

	
	private static final ObjectMapper mapper = new ObjectMapper();

    public static MQTT_Listner_Receive_Data_Dto parse(String json) throws Exception {

        JsonNode root = mapper.readTree(json);
        MQTT_Listner_Receive_Data_Dto data = new MQTT_Listner_Receive_Data_Dto();

        JsonNode endDeviceIds = root.path("end_device_ids");

        data.setDeviceId(endDeviceIds.path("device_id").asText());
        data.setDevEui(endDeviceIds.path("dev_eui").asText());
        data.setJoinEui(endDeviceIds.path("join_eui").asText());
        data.setDevAddr(endDeviceIds.path("dev_addr").asText());

        data.setApplicationId(
            endDeviceIds.path("application_ids")
                        .path("application_id")
                        .asText()
        );

        JsonNode uplink = root.path("uplink_message");

        data.setfPort(uplink.path("f_port").asInt());
        data.setfCnt(uplink.path("f_cnt").asInt());
        data.setFrmPayload(uplink.path("frm_payload").asText());
      //  data.setReceivedAt(uplink.path("received_at").asText());
        
        String receivedAtRaw = uplink.path("received_at").asText();

        if (receivedAtRaw != null && !receivedAtRaw.isEmpty()) {
            Instant instant = Instant.parse(receivedAtRaw);

            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
                                     .withZone(ZoneId.systemDefault()); // or ZoneId.of("Asia/Kolkata")

            data.setReceivedAt(formatter.format(instant));
        }

        
        
        JsonNode decodedPayload = root.path("decoded_payload");
        data.setHexPayload(decodedPayload.path("payload").asText());

        JsonNode rxMeta = uplink.path("rx_metadata").get(0);

        data.setGatewayId(rxMeta.path("gateway_ids").path("gateway_id").asText());
        data.setGatewayEui(rxMeta.path("gateway_ids").path("eui").asText());
        data.setRssi(rxMeta.path("rssi").asInt());
        data.setChannelRssi(rxMeta.path("channel_rssi").asInt());
        data.setSnr(rxMeta.path("snr").asDouble());
        data.setChannelIndex(rxMeta.path("channel_index").asInt());

        JsonNode settings = uplink.path("settings");

        data.setBandwidth(
            settings.path("data_rate").path("lora").path("bandwidth").asInt()
        );

        data.setSpreadingFactor(
            settings.path("data_rate").path("lora").path("spreading_factor").asInt()
        );

        data.setFrequency(settings.path("frequency").asText());

        data.setNetId(
            uplink.path("network_ids").path("net_id").asText()
        );

        // Decode payload
        byte[] decoded = Base64.getDecoder().decode(data.getFrmPayload());
        data.setHexPayload(bytesToHex(decoded));
        
        

        return data;
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes)
            sb.append(String.format("%02X", b));
        return sb.toString();
    }
	
}
