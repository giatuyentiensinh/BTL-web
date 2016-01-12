package modules;

import java.util.List;

import org.mongojack.Id;
import org.mongojack.JacksonDBCollection;
import org.mongojack.MongoCollection;

import play.Play;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@MongoCollection(name = "userbyitem")
public class UserBuyItem {

	private static final JacksonDBCollection<UserBuyItem, String> coll = JacksonDBCollection
			.wrap(Connect.getCollection(Play.application().configuration()
					.getString("mongo.collection"), "userbyitem"),
					UserBuyItem.class, String.class);

	@Id
	@JsonProperty("_id")
	private String id;
	@JsonProperty("username")
	private String username;
	@JsonProperty("phonenumber")
	private String phonenumber;
	@JsonProperty("email")
	private String email;
	@JsonProperty("address")
	private String address;
	@JsonProperty("item")
	private List<Item> items;

	@JsonCreator
	public UserBuyItem(@Id @JsonProperty("_id") String id,
			@JsonProperty("username") String username,
			@JsonProperty("phonenumber") String phonenumber,
			@JsonProperty("email") String email,
			@JsonProperty("address") String address,
			@JsonProperty("item") List<Item> items) {
		super();
		this.id = id;
		this.username = username;
		this.phonenumber = phonenumber;
		this.email = email;
		this.address = address;
		this.items = items;
	}

	public static Long count() {
		return coll.count();
	}
	
	public static List<UserBuyItem> readBill() {
		return coll.find().toArray();
	}

	public void save() {
		coll.save(this);
	}

	public void delete() {
		coll.removeById(this.id);
	}
}
