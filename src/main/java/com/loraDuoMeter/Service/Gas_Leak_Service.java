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

import com.loraDuoMeter.DTO.GasLeakDisplay_DTO;
import com.loraDuoMeter.Proj.IGasLeakDisplayView;
import com.loraDuoMeter.Proj.IPowerUpDisplayView;
import com.loraDuoMeter.Repo.IGas_Leak_Repo;
import com.loraDuoMeter.Repo.IPower_Up_Display_Repo;

@Service
public class Gas_Leak_Service {    
	
	
	private final IGas_Leak_Repo repo;

	public Gas_Leak_Service(IGas_Leak_Repo repo) {
    this.repo = repo;
	}

@Transactional(readOnly = true)
public List<GasLeakDisplay_DTO> getLatestByDevEui() {
    try (Stream<IGasLeakDisplayView> stream = repo.findLatestByDevEuiNative()) {
        return stream.map(p -> new GasLeakDisplay_DTO(
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
            p.getFlowType(),
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
public Slice<GasLeakDisplay_DTO> getByDevEui(
        int page, int size, String devEui) {

    Pageable pageable = PageRequest.of(
            page,
            size,
            Sort.by("receivedAt").descending()
    );

    Slice<IGasLeakDisplayView> slice =
            (devEui == null || devEui.isEmpty())
            ? repo.findByDevEui(pageable) : repo.findLatestByDevEuiAndDevEui(devEui, pageable);

    return slice.map(p -> new GasLeakDisplay_DTO(
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
            p.getFlowType(),
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

    Slice<IGasLeakDisplayView> slice;
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