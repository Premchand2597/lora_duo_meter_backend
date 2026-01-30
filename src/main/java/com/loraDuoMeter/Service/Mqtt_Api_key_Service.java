package com.loraDuoMeter.Service;

import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loraDuoMeter.Proj.Mqtt_Api_Key_Down_Proj;
import com.loraDuoMeter.Proj.Mqtt_Api_Key_Proj;
import com.loraDuoMeter.Repo.IMqtt_Api_Key_Repo;

@Service
public class Mqtt_Api_key_Service {

	@Autowired
	private IMqtt_Api_Key_Repo repo;
	
	private final Map<String, Mqtt_Api_Key_Proj> cache = new ConcurrentHashMap<>();
	
	 @Transactional(readOnly = true)
	public Slice<Mqtt_Api_Key_Proj> get_apis(int page, int size){
		Pageable pageable= PageRequest.of(page,size);
		return repo.find_all_api_keys(pageable);
		
	}

	
}
