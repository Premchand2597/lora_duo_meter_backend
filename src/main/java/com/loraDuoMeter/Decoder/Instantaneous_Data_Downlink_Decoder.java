package com.loraDuoMeter.Decoder;

import com.loraDuoMeter.Downlink_DTO.Instantaneous_Data_Downlink_Dto;
import com.loraDuoMeter.Downlink_DTO.Meter_Info_Downlink_Dto;
import com.loraDuoMeter.Enum.DeviceType;
import com.loraDuoMeter.Enum.MeterConnection;
import com.loraDuoMeter.Enum.NetworkType;
import com.loraDuoMeter.Enum.ReservedByte;
import com.loraDuoMeter.Enum.ValveStatus;
import com.loraDuoMeter.Repo.IPacketDecoder;

public class Instantaneous_Data_Downlink_Decoder implements IPacketDecoder<Instantaneous_Data_Downlink_Dto> {


    @Override
    public Instantaneous_Data_Downlink_Dto decode(String hex) {
    	Instantaneous_Data_Downlink_Dto dto = new Instantaneous_Data_Downlink_Dto();
        int i = 0;

        // ---- Fixed fields ----
        dto.setCommand_id(hex.substring(i, i += 2));     // 81
        dto.setReserved_byte(ReservedByte.fromHex(hex.substring(i, i += 2)));  // 00
        dto.setNetwork_type(NetworkType.fromHex(hex.substring(i, i += 2)));   // 00
        dto.setDevice_type(DeviceType.fromHex(hex.substring(i, i += 2)));    // 00
        dto.setMeter_model(hex.substring(i, i += 2));    // 00
        dto.setFirmware_version(hex.substring(i, i += 4)); // 0001
        dto.setMeter_connection(MeterConnection.fromHex(hex.substring(i, i += 2))); // 01
        dto.setValve_status(ValveStatus.fromHex(hex.substring(i, i += 2)));     // 01

     // ---- Device RTC (hh mm ss dd MM yy) ----     
        int hh = Integer.parseInt(hex.substring(i, i += 2), 16);
        int mm = Integer.parseInt(hex.substring(i, i += 2), 16);
        int ss = Integer.parseInt(hex.substring(i, i += 2), 16);
        int dd = Integer.parseInt(hex.substring(i, i += 2), 16);
        int MM = Integer.parseInt(hex.substring(i, i += 2), 16);
        int yy = Integer.parseInt(hex.substring(i, i += 2), 16);

        // Correct format: dd-MM-yyyy HH:mm:ss
        dto.setDevice_rtc(String.format("%02d-%02d-20%02d %02d:%02d:%02d", dd, MM, yy, hh, mm, ss));


        // ---- Pulse count (4 bytes, 8 hex chars) ----
        dto.setPulse_count(String.valueOf(Long.parseLong(hex.substring(i, i += 8), 16)));

        // ---- Battery voltage (2 bytes, 4 hex chars) ----
        dto.setBattery_voltage(String.valueOf(Integer.parseInt(hex.substring(i, i += 4), 16) / 1000.0));

        // ---- Next update time (3 bytes, 6 hex chars) ----
        // Assuming format: HH MM SS (hex)
        String hhNext = hex.substring(i, i += 2);
        String mmNext = hex.substring(i, i += 2);
        String ssNext = hex.substring(i, i += 2);
        dto.setNext_update_time(String.format("%s:%s:%s", hhNext, mmNext, ssNext));


        return dto;
    }
	
}
