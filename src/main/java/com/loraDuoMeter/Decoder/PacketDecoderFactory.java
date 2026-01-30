package com.loraDuoMeter.Decoder;

import java.util.EnumMap;
import java.util.Map;
import com.loraDuoMeter.Enum.PacketType;
import com.loraDuoMeter.Repo.IPacketDecoder;

public class PacketDecoderFactory {
	
	 private static final Map<PacketType, IPacketDecoder<?>> MAP =
	            new EnumMap<>(PacketType.class);

	    static {
			/* Uplink */
	        MAP.put(PacketType.POWER_UP, new Power_Up_Uplink_Decoder());
	        MAP.put(PacketType.DAILY_UPDATE, new Daily_Update_Uplink_Decoder());
	        MAP.put(PacketType.TAMPER, new Tamper_Event_Uplink_Decoder());
	        MAP.put(PacketType.GAS_LEAK, new Gas_Leak_Uplink_Decoder());
	        MAP.put(PacketType.RECHARGE_OVER, new Recharge_Over_Uplink_Decoder());
	        MAP.put(PacketType.MANUAL_DEVICE_EVENT_NOTIFICATION, new Device_Event_Notification_Uplink_Decoder());
	        MAP.put(PacketType.BATTERY_CUTTOFF, new Battery_Cut_Off_Uplink_Decoder());
	        
			/* Downlink */	        
	        MAP.put(PacketType.READ_METER_INFO, new Meter_Info_Downlink_Decoder());
	        MAP.put(PacketType.READ_INSTANTANEOUS_DATA, new Instantaneous_Data_Downlink_Decoder());
	        
	        
	    }

	    @SuppressWarnings("unchecked")
	    public static <T> T decode(String hex) {
	        PacketType type = PacketType.resolve(hex);

	        IPacketDecoder<?> decoder = MAP.get(type);
	        if (decoder == null) {
	            throw new IllegalArgumentException("No decoder for packet type: " + type);
	        }

	        return (T) decoder.decode(hex);
	    }
}
