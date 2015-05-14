package modules;

import java.util.List;

import modules.phone.Configuration;

import org.mongojack.DBQuery;
import org.mongojack.DBQuery.Query;
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
	private String id;
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
	public Phone(@Id String id, @JsonProperty("name") String name,
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

	// Access Edit and change
	public static void deletePhone(String id) {
		coll.removeById(id);
	}

	public static void updatePhone(String id, Phone phone) {
		coll.updateById(id, phone);
	}

	public static void savePhone(Phone phone) {
		coll.save(phone);
	}

	// Access read
	public static Phone findbyId(String id) {
		return coll.findOneById(id);
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

	public static List<Phone> findCost(String lessthan, String greaterthan) {
		double lt = Double.parseDouble(lessthan);
		double gt = Double.parseDouble(greaterthan);

		// System.out.println(lt);
		// System.out.println(gt);

		if (lt == 0) {
			Query query = DBQuery.greaterThan("price", gt);
			return coll.find(query).toArray();
		}
		if (gt == 0) {
			Query query = DBQuery.lessThan("price", lt);
			return coll.find(query).toArray();
		}

		Query greate = DBQuery.greaterThanEquals("price", lt);
		Query less = DBQuery.lessThanEquals("price", gt);

		return coll.find(DBQuery.and(greate, less)).toArray();
	}

	public String getId() {
		return id.toString();
	}
}