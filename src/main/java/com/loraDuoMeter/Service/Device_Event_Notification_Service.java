package com.loraDuoMeter.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loraDuoMeter.DTO.ManualDeviceNotificationDisplay_DTO;
import com.loraDuoMeter.Proj.IManualDeviceNotificationDisplayView;
import com.loraDuoMeter.Repo.IDevice_Event_Notification_Repo;

@Service
public class Device_Event_Notification_Service {

    private final IDevice_Event_Notification_Repo repo;

    public Device_Event_Notification_Service(IDevice_Event_Notification_Repo repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public List<ManualDeviceNotificationDisplay_DTO> getLatestByDevEui() {
        try (Stream<IManualDeviceNotificationDisplayView> stream = repo.findLatestByDevEuiNative()) {
            return stream.map(p -> new ManualDeviceNotificationDisplay_DTO(
                p.getSlNo(),
                p.getNetworkType(),
                p.getDeviceType(),
                p.getMeterConnectionType(),
                p.getMeterModel(),
                p.getValveStatus(),
                p.getDeviceRtc(),
                p.getPulseCount(),
                p.getBatteryVoltage(),
                p.getNextUpdateTime(),
                p.getPendingEvent(),
                p.getRechargeStatus(),
                p.getLastUpdateTime(),
                p.getDevEui(),
                p.getGatewayEui(),
                p.getFPort(),
                p.getReceivedAt(),
                p.getBuildingName(),
                p.getResidentName()

            )).collect(Collectors.toList());
        }
    }
    
    @Transactional(readOnly = true)
    public Slice<ManualDeviceNotificationDisplay_DTO> getByDevEui(
            int page, int size, String devEui) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("receivedAt").descending()
        );

        Slice<IManualDeviceNotificationDisplayView> slice =
                (devEui == null || devEui.isEmpty())
                ? repo.findByDevEui(pageable) : repo.findLatestByDevEuiAndDevEui(devEui, pageable);

        return slice.map(p -> new ManualDeviceNotificationDisplay_DTO(
                p.getSlNo(),
                p.getNetworkType(),
                p.getDeviceType(),
                p.getMeterConnectionType(),
                p.getMeterModel(),
                p.getValveStatus(),
                p.getDeviceRtc(),
                p.getPulseCount(),
                p.getBatteryVoltage(),
                p.getNextUpdateTime(),
                p.getPendingEvent(),
                p.getRechargeStatus(),
                p.getLastUpdateTime(),
                p.getDevEui(),
                p.getGatewayEui(),
                p.getFPort(),
                p.getReceivedAt(),
                p.getBuildingName(),
                p.getResidentName()
        ));
    }
    
    @Transactional(readOnly = true)
    public Map<String, Object> getByDevEui(
            int page,
            int size,
            String devEui,
            String search
    ) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("receivedAt").descending()
        );

        Slice<IManualDeviceNotificationDisplayView> slice;
        long total;

        if (search == null || search.trim().isEmpty()) {
            // 🔹 Normal fetch
            slice = repo.findLatestByDevEuiAndDevEui(devEui, pageable);
            total = repo.countByDevEui(devEui);
        } else {
            // 🔹 Search fetch
            slice = repo.searchByDevEui(devEui, search, pageable);
            total = repo.countSearchByDevEui(devEui, search);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("content", slice.getContent());
        map.put("hasNext", slice.hasNext());
        map.put("totalElements", total);

        return map;
    }

    

}

