package MetarPlanner.api.models;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import MetarPlanner.MetarController;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@XmlRootElement(name="response")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RawMetarResponse{
	private static final Logger log = LoggerFactory.getLogger(MetarController.class);

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
