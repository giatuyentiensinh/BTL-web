package modules;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {
	@JsonProperty("id")
	private String id;
	@JsonProperty("quantity")
	private int quantity;

	@JsonCreator()
	public Item(@JsonProperty("id") String id,
			@JsonProperty("quantity") int quantity) {
		super();
		this.id = id;
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return id + " :: " + quantity;
	}
}