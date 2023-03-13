package com.metar_planner.services;

import com.metar_planner.models.RawMetarResponse;

public interface MetarService {
	
	public RawMetarResponse getMetar(String id);

}