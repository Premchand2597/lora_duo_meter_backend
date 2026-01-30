package com.loraDuoMeter.Decoder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.loraDuoMeter.Downlink_DTO.Meter_Info_Downlink_Dto;
import com.loraDuoMeter.Enum.DeviceType;
import com.loraDuoMeter.Enum.MeterConnection;
import com.loraDuoMeter.Enum.NetworkType;
import com.loraDuoMeter.Enum.ReservedByte;
import com.loraDuoMeter.Enum.ValveStatus;
import com.loraDuoMeter.Repo.IPacketDecoder;

public class Meter_Info_Downlink_Decoder implements IPacketDecoder<Meter_Info_Downlink_Dto> {

	LocalDateTime now = LocalDateTime.now();
	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");	


	@Override
	public Meter_Info_Downlink_Dto decode(String hex) {

	    Meter_Info_Downlink_Dto dto = new Meter_Info_Downlink_Dto();
	    int i = 0;

	    // ---- Fixed fields ----
	    dto.setCommand_id(hex.substring(i, i += 2));
	    dto.setReserved_byte(ReservedByte.fromHex(hex.substring(i, i += 2)));
	    dto.setNetwork_type(NetworkType.fromHex(hex.substring(i, i += 2)));
	    dto.setDevice_type(DeviceType.fromHex(hex.substring(i, i += 2)));
	    dto.setMeter_model(hex.substring(i, i += 2));
	    dto.setFirmware_version(hex.substring(i, i += 4));
	    dto.setMeter_connection(MeterConnection.fromHex(hex.substring(i, i += 2)));
	    dto.setValve_status(ValveStatus.fromHex(hex.substring(i, i += 2)));

	    // ---- Device RTC (hh mm ss dd MM yy) ----
	    int hh = Integer.parseInt(hex.substring(i, i += 2), 16);
	    int mm = Integer.parseInt(hex.substring(i, i += 2), 16);
	    int ss = Integer.parseInt(hex.substring(i, i += 2), 16);
	    int dd = Integer.parseInt(hex.substring(i, i += 2), 16);
	    int MM = Integer.parseInt(hex.substring(i, i += 2), 16);
	    int yy = Integer.parseInt(hex.substring(i, i += 2), 16);

	    String rtc = String.format(
	            "%02d-%02d-20%02d %02d:%02d:%02d",
	            dd, MM, yy, hh, mm, ss
	    );
	    dto.setDevice_rtc(rtc);
	    dto.setLast_update_time(rtc);

	    // ---- Pulse Count (4 Bytes) ----
	    dto.setPulse_count(String.valueOf(
	            Long.parseLong(hex.substring(i, i += 8), 16)
	    ));

	    // ---- Battery Voltage (2 Bytes) ----
	    dto.setBattery_voltage(String.valueOf(
	            Integer.parseInt(hex.substring(i, i += 4), 16) / 1000.0
	    ));

	    // ================== NEW FIELDS ==================

	 // ---- Pending Event (1 Byte) ----
	    dto.setPending_event(
	            String.valueOf(Integer.parseInt(hex.substring(i, i += 2), 16))
	    );

	    // ---- Recharge Status (1 Byte) ----
	    dto.setRecharge_status(
	            String.valueOf(Integer.parseInt(hex.substring(i, i += 2), 16))
	    );

	    // ---- Credit Limits (10 Bytes → split into 2 parts) ----
	    dto.setCredit_limits_01(
	            String.valueOf(Long.parseLong(hex.substring(i, i += 8), 16))
	    );

	    dto.setCredit_limits_02(
	            String.valueOf(Long.parseLong(hex.substring(i, i += 12), 16)) // remaining 6 bytes
	    );

	    // ---- Leak Detector Count (8 Bytes → split into 2 parts) ----
	    dto.setLeak_detector_1(
	            String.valueOf(Long.parseLong(hex.substring(i, i += 8), 16))
	    );

	    dto.setLeak_detector_2(
	            String.valueOf(Long.parseLong(hex.substring(i, i += 8), 16))
	    );

	    // ---- Per Liter (4 Bytes) ----
	    dto.setGas_per_litre(
	            String.valueOf(Long.parseLong(hex.substring(i, i += 8), 16))
	    );

	    // ---- Available Amount (4 Bytes) ----
	    dto.setAvailable_amount(
	            String.valueOf(Long.parseLong(hex.substring(i, i += 8), 16))
	    );

	    // ---- Battery Voltage Cut-off Level (2 Bytes) ----
	    dto.setBattery_voltage_cutt_off(
	            String.valueOf(Integer.parseInt(hex.substring(i, i += 4), 16) / 1000.0)
	    );


	    return dto;
	}
}
