package com.metar_planner.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metar_planner.models.RawMetarResponse;
import com.metar_planner.services.MetarService;
import com.metar_planner.services.NoaaMetarService;



@RestController
@RequestMapping("metar")
public class MetarController {
	
	private MetarService metarService;

	MetarController(NoaaMetarService theMetarService){
		this.metarService = theMetarService;
	}

	@GetMapping("/{icao}")
	RawMetarResponse getMetar(@PathVariable String icao) {
		return this.metarService.getMetar(icao);
	}
	
	
}
