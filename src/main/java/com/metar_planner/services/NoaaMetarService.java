package com.metar_planner.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.metar_planner.models.RawMetarResponse;


@Service
public class NoaaMetarService implements MetarService {
	
	private String noaaUrl;
	private RestTemplate restTemplate;
	private String urlDataSource;
	private String urlRequestType;
	private String urlFormat;
	private String urlHoursBeforeNow;
	
	public NoaaMetarService(
			RestTemplate theRestTemplate,
			@Value("${noaa.url}") String url,
			@Value("${noaa.url.datasource}") String datasource,
			@Value("${noaa.url.requesttype}") String requesttype,
			@Value("${noaa.url.format}") String format,
			@Value("${noaa.url.hoursBeforeNow}") String hoursBeforeNow
	) {
		
		this.restTemplate = theRestTemplate;
		this.noaaUrl = url;
		this.urlDataSource = datasource;
		this.urlRequestType = requesttype;
		this.urlFormat = format;
		this.urlHoursBeforeNow = hoursBeforeNow;
		
	}
	

	public RawMetarResponse getMetar(String stationId) {
		// Sample URL: "https://www.aviationweather.gov/adds/dataserver_current/httpparam?datasource=metars&requesttype=retrieve&format=xml&hoursBeforeNow=1.25&stationString="

		Map<String, String> uriVars = this.getUriVars(stationId);
		
		
		//TODO: Needs exception handling.
		ResponseEntity<RawMetarResponse> responseEntity = 
				restTemplate.exchange(
						this.noaaUrl, 
						HttpMethod.GET, 
						null, 
						new ParameterizedTypeReference<RawMetarResponse>() {},
						uriVars
				);
		
		
		RawMetarResponse rawMetarResponse = responseEntity.getBody();
		
	    return rawMetarResponse;
	}
	
	private Map<String, String> getUriVars(String stationId){
		Map<String, String> uriVars = new HashMap<String, String>();
		uriVars.put("datasource",this.urlDataSource);
		uriVars.put("requesttype",this.urlRequestType);
		uriVars.put("format",this.urlFormat);
		uriVars.put("hoursBeforeNow",this.urlHoursBeforeNow);
		uriVars.put("stationString", stationId);
		
		return  uriVars;
	}

}
