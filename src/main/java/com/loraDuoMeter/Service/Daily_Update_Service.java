package com.loraDuoMeter.Service;

import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loraDuoMeter.DTO.DailyUpdateDisplay_DTO;
import com.loraDuoMeter.Proj.DailyUpdateDisplayView;
import com.loraDuoMeter.Repo.IDaily_Update_Display_Repo;

@Service
public class Daily_Update_Service {

    private final IDaily_Update_Display_Repo repo;

    public Daily_Update_Service(IDaily_Update_Display_Repo repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public List<DailyUpdateDisplay_DTO> getLatestByDevEui() {
        try (Stream<DailyUpdateDisplayView> stream = repo.findLatestByDevEuiNative()) {
            return stream.map(p -> new DailyUpdateDisplay_DTO(
                p.getSlNo(),
                p.getNetworkType(),
                p.getDeviceType(),
                p.getMeterConnection(),
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
                p.getResidentName(),
                p.getMeterModel()
            )).collect(Collectors.toList());
        }
    }
    
    @Transactional(readOnly = true)
    public Slice<DailyUpdateDisplay_DTO> getByDevEui(
            int page, int size, String devEui) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("receivedAt").descending()
        );

        Slice<DailyUpdateDisplayView> slice =
                (devEui == null || devEui.isEmpty())
                ? repo.findByDevEui(pageable) : repo.findLatestByDevEuiAndDevEui(devEui, pageable);

        return slice.map(p -> new DailyUpdateDisplay_DTO(
                p.getSlNo(),
                p.getNetworkType(),
                p.getDeviceType(),
                p.getMeterConnection(),
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
                p.getResidentName(),
                p.getMeterModel()
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

        Slice<DailyUpdateDisplayView> slice;
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
