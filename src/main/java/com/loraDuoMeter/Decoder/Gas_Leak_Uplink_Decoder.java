package com.loraDuoMeter.Decoder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.loraDuoMeter.Enum.DeviceType;
import com.loraDuoMeter.Enum.MeterConnection;
import com.loraDuoMeter.Enum.NetworkType;
import com.loraDuoMeter.Enum.ReservedByte;
import com.loraDuoMeter.Enum.ValveStatus;
import com.loraDuoMeter.Repo.IPacketDecoder;
import com.loraDuoMeter.Uplink_DTO.Gas_Leak_Uplink_Dto;

public class Gas_Leak_Uplink_Decoder implements IPacketDecoder<Gas_Leak_Uplink_Dto> {

	LocalDateTime now = LocalDateTime.now();
	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	
	@Override
	public Gas_Leak_Uplink_Dto decode(String hex) {
		
		Gas_Leak_Uplink_Dto leak_dto = new Gas_Leak_Uplink_Dto();
			int i = 0;
		 
		 // ---- Fixed fields ----
	        leak_dto.setCommand_id(hex.substring(i, i += 2));     // 84				- 1byte
	        leak_dto.setReserved_byte(ReservedByte.fromHex(hex.substring(i, i += 2)));  // 01/02-FF 		- 1byte
	        leak_dto.setNetwork_type(NetworkType.fromHex(hex.substring(i, i += 2)));   // 00/01/02/03/04 - 1byte
	        leak_dto.setDevice_type(DeviceType.fromHex(hex.substring(i, i += 2)));    // 00/01			- 1byte
	        leak_dto.setMeter_model(hex.substring(i, i += 2));    // 00/01/02/03/04	- 1byte
	        leak_dto.setFirmware_version(hex.substring(i, i += 4)); // 0000			- 2byte
	        leak_dto.setMeter_connection_type(MeterConnection.fromHex(hex.substring(i, i += 2))); // 00/01	- 1byte
	        leak_dto.setValve_status(ValveStatus.fromHex(hex.substring(i, i += 2)));     // 00/01 - 1byte - 1byte

	     // ---- Device RTC (hh mm ss dd MM yy) ----     
	        int hh = Integer.parseInt(hex.substring(i, i += 2), 16);
	        int mm = Integer.parseInt(hex.substring(i, i += 2), 16);
	        int ss = Integer.parseInt(hex.substring(i, i += 2), 16);
	        int dd = Integer.parseInt(hex.substring(i, i += 2), 16);
	        int MM = Integer.parseInt(hex.substring(i, i += 2), 16);
	        int yy = Integer.parseInt(hex.substring(i, i += 2), 16);

	        // Correct format: dd-MM-yyyy HH:mm:ss
	        leak_dto.setDevice_rtc(String.format("%02d-%02d-20%02d %02d:%02d:%02d", dd, MM, yy, hh, mm, ss)); // 000000000000 - 6bytes


	        // ---- Pulse count (4 bytes, 8 hex chars) ----
	        leak_dto.setPulse_count(String.valueOf(Long.parseLong(hex.substring(i, i += 8), 16))); // 00000000 - 4bytes

	        // ---- Battery voltage (2 bytes, 4 hex chars) ----
	        leak_dto.setBattery_voltage(String.valueOf(Integer.parseInt(hex.substring(i, i += 4), 16) / 1000.0)); //0000 - 2bytes

	        // ---- Next update time (3 bytes, 6 hex chars) ----
	        // Assuming format: HH MM SS (hex)
	        String hhNext = hex.substring(i, i += 2);
	        String mmNext = hex.substring(i, i += 2);
	        String ssNext = hex.substring(i, i += 2);
	        leak_dto.setNext_update_time(String.format("%s:%s:%s", hhNext, mmNext, ssNext)); // 000000 - 3 bytes
	        
	        leak_dto.setFlow_type(hex.substring(i, i += 2));    // 00/01  - 1 byte		
		
		return leak_dto;
	}

}
