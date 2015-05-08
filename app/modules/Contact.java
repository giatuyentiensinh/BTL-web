package modules;

import java.util.Date;

import org.mongojack.DBQuery;
import org.mongojack.Id;
import org.mongojack.JacksonDBCollection;
import org.mongojack.MongoCollection;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@MongoCollection(name = "contact")
public class Contact {

	private static final JacksonDBCollection<Contact, String> coll = JacksonDBCollection
			.wrap(Connect.getCollection("btlweb", "contact"), Contact.class,
					String.class);
	@Id
	private String id;

	@Required
	@JsonProperty("name")
	private String name;
	@Required
	@Email
	@JsonProperty("email")
	private String email;
	@Required
	@JsonProperty("content")
	private String content;
	@Required
	@JsonProperty("time")
	private Date time;

	@JsonCreator
	public Contact(@Id String id, @JsonProperty("name") String name,
			@JsonProperty("email") String email,
			@JsonProperty("content") String content,
			@JsonProperty("time") Date time) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.content = content;
		this.time = time;
	}

	public Contact findByName(String name) {
		return coll.findOne(DBQuery.is("name", name));
	}
}
