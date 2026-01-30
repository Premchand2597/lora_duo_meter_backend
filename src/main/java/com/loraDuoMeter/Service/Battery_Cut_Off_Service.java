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

import com.loraDuoMeter.DTO.Battery_Cut_Off_DTO;
import com.loraDuoMeter.Proj.IBatteryCutOffDisplayView;
import com.loraDuoMeter.Repo.IBattery_Cut_Off_Repo;

@Service
public class Battery_Cut_Off_Service {
	
    private final IBattery_Cut_Off_Repo repo;

    public Battery_Cut_Off_Service(IBattery_Cut_Off_Repo repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public List<Battery_Cut_Off_DTO> getLatestByDevEui() {
        try (Stream<IBatteryCutOffDisplayView> stream = repo.findLatestByDevEuiNative()) {
            return stream.map(p -> new Battery_Cut_Off_DTO(
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
    public Slice<Battery_Cut_Off_DTO> getByDevEui(
            int page, int size, String devEui) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("receivedAt").descending()
        );

        Slice<IBatteryCutOffDisplayView> slice =
                (devEui == null || devEui.isEmpty())
                ? repo.findByDevEui(pageable) : repo.findLatestByDevEuiAndDevEui(devEui, pageable);

        return slice.map(p -> new Battery_Cut_Off_DTO(
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

        Slice<IBatteryCutOffDisplayView> slice;
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
