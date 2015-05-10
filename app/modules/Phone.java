package modules;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongojack.Id;
import org.mongojack.JacksonDBCollection;
import org.mongojack.MongoCollection;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@MongoCollection(name = "phone")
public class Phone {

	public static final JacksonDBCollection<Phone, String> coll = JacksonDBCollection
			.wrap(Connect.getCollection("btlweb", "phone"), Phone.class,
					String.class);

	@Id
	private ObjectId id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("size")
	private String size;
	@JsonProperty("weight")
	private String weight;
	@JsonProperty("price")
	private Double price;
	@JsonProperty("image")
	private String image;
	@JsonProperty("rate")
	private String rate;
	@JsonProperty("provider")
	private String provider;
	@JsonProperty("configuration")
	private Configuration configuration;

	public Phone() {
		super();
	}

	@JsonCreator
	public Phone(@Id ObjectId id, @JsonProperty("name") String name,
			@JsonProperty("size") String size,
			@JsonProperty("weight") String weight,
			@JsonProperty("price") Double price,
			@JsonProperty("image") String image,
			@JsonProperty("rate") String rate,
			@JsonProperty("provider") String provider,
			@JsonProperty("configuration") Configuration configuration) {
		super();
		this.id = id;
		this.name = name;
		this.size = size;
		this.weight = weight;
		this.price = price;
		this.image = image;
		this.rate = rate;
		this.provider = provider;
		this.configuration = configuration;
	}

	public static Phone findbyId(String id) {
		List<Phone> all = findAll();
		for (Phone phone : all)
			if (id.equals(phone.id.toString()))
				return phone;
		return null;
	}

	public static List<Phone> findOffsetAndCount(String offset, String number) {
		int count = Integer.parseInt(number);
		int skip = count * Integer.parseInt(offset);
		return coll.find().limit(count).skip(skip).toArray();
	}
	
	public static long count() {
		return coll.count();
	}

	public static List<Phone> findAll() {
		return coll.find().toArray();
	}

	@SuppressWarnings("unchecked")
	public static List<String> findCategory() {
		return coll.distinct("provider");
	}

	public String getId() {
		return id.toString();
	}
}

class Configuration {
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

class Network {
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

class Camera {
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