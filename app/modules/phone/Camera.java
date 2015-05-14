package modules.phone;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Camera {
	@JsonProperty("front")
	private String front;
	@JsonProperty("back")
	private String back;

	@JsonCreator
	public Camera(@JsonProperty("front") String front,
			@JsonProperty("back") String back) {
		this.front = front;
		this.back = back;
	}
}