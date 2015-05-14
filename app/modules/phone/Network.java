package modules.phone;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Network {
	@JsonProperty("wifi")
	private String wifi;
	@JsonProperty("bluetooth")
	private String bluetooth;

	@JsonCreator
	public Network(@JsonProperty("wifi") String wifi,
			@JsonProperty("bluetooth") String bluetooth) {
		this.wifi = wifi;
		this.bluetooth = bluetooth;
	}
}
