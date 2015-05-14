package modules.phone;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Configuration {
	@JsonProperty("OS")
	private String os;
	@JsonProperty("display")
	private String display;
	@JsonProperty("cpu")
	private String cpu;
	@JsonProperty("ram")
	private String ram;
	@JsonProperty("pin")
	private String pin;
	@JsonProperty("network")
	private Network network;
	@JsonProperty("camera")
	private Camera camera;

	@JsonCreator
	public Configuration(@JsonProperty("OS") String os,
			@JsonProperty("display") String display,
			@JsonProperty("cpu") String cpu, @JsonProperty("ram") String ram,
			@JsonProperty("pin") String pin,
			@JsonProperty("netword") Network network,
			@JsonProperty("camera") Camera camera) {
		super();
		this.os = os;
		this.display = display;
		this.cpu = cpu;
		this.ram = ram;
		this.pin = pin;
		this.network = network;
		this.camera = camera;
	}
}
