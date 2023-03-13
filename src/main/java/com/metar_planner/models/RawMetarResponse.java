package com.metar_planner.models;


import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@XmlRootElement(name="response")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RawMetarResponse{
	public String request_index;
	public String data_source;
	public String request;
	public String errors;
	public String warnings;
	public String time_taken_ms;
	public RawMetarData data;
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class RawMetarData {
		public List<RawMetar> METAR;
		
		@Data
		@NoArgsConstructor
		@AllArgsConstructor
		public static class RawMetar{
			public String raw_text;
			public String station_id;
			
		}
	}
	
	@Override
	public String toString() {
		return this.data.METAR.get(0).raw_text;
	}
}
