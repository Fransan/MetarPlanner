package MetarPlanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.codec.xml.Jaxb2XmlDecoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import MetarPlanner.api.models.Metar;
import MetarPlanner.api.models.RawMetarResponse;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("metar")
public class MetarController {
	private static final Logger log = LoggerFactory.getLogger(MetarController.class);
	MetarController(){}

	@GetMapping("/{icao}")
	Metar getMetar(@PathVariable String icao) {
		// "https://www.aviationweather.gov/adds/dataserver_current/httpparam?datasource=metars&requesttype=retrieve&format=xml&hoursBeforeNow=1.25&stationString="
		//final String url = "https://www.aviationweather.gov/adds/dataserver_current/httpparam";
		final String url = "https://www.aviationweather.gov/adds/dataserver_current/httpparam?datasource=metars&requesttype=retrieve&format=xml&hoursBeforeNow=1.25&stationString="+icao;

		Map<String, String> uriVars = new HashMap<String, String>();
		uriVars.put("datasource","metars");
		uriVars.put("requesttype","retrieve");
		uriVars.put("format","xml");
		uriVars.put("hoursBeforeNow","1");
		uriVars.put("stationString", icao);

	    WebClient client = WebClient.builder()
	    		.exchangeStrategies(
					ExchangeStrategies.builder()
						.codecs(configurer -> 
							configurer.defaultCodecs().jaxb2Decoder(new Jaxb2XmlDecoder())
	                    )
	                    .build()
	            )
	    		.baseUrl(url)
	    		.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML_VALUE)
	    		.defaultUriVariables(uriVars)
	    		.filters(exchangeFilterFunctions -> {
	    			exchangeFilterFunctions.add(this.logRequest());  
	    		})
	    		.build();

	    RawMetarResponse rawMetarResponse = client.get()
	    //String response = client.get()
	    	.retrieve()
	    	.bodyToMono(RawMetarResponse.class)
	    	.block();

	    log.info(rawMetarResponse.toString());
	    Metar metar = new Metar(rawMetarResponse.data.METAR.get(0).station_id, rawMetarResponse.data.METAR.get(0).raw_text);
	    //Metar metar = new Metar("test", "test");

	    return metar;
	}
	
	private ExchangeFilterFunction logRequest() {
	    return ExchangeFilterFunction.ofResponseProcessor(clientRequest -> {
            StringBuilder sb = new StringBuilder("Request: \n");
            sb.append(clientRequest.bodyToMono(String.class));
            log.info(sb.toString());
	        return Mono.just(clientRequest);
	    });
	}

}
