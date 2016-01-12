package modules;

import java.util.List;

import org.mongojack.DBQuery;
import org.mongojack.Id;
import org.mongojack.JacksonDBCollection;
import org.mongojack.MongoCollection;

import play.Play;
import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@MongoCollection(name = "contact")
public class Contact {

	private static final JacksonDBCollection<Contact, String> coll = JacksonDBCollection
			.wrap(Connect.getCollection(Play.application().configuration()
					.getString("mongo.collection"), "contact"), Contact.class,
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
	@JsonProperty("subject")
	@Required
	private String subject;
	@Required
	@JsonProperty("content")
	private String content;

	@JsonCreator
	public Contact(@Id String id, @JsonProperty("name") String name,
			@JsonProperty("email") String email,
			@JsonProperty("subject") String subject,
			@JsonProperty("content") String content) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.subject = subject;
		this.content = content;
	}

	public void save() {
		coll.save(this);
	}

	public static Contact findByName(String name) {
		return coll.findOne(DBQuery.is("name", name));
	}

	public static List<Contact> readContactAll() {
		return coll.find().toArray();
	}

	public static Long count() {
		return coll.count();
	}
	
	public String getId() {
		return id;
	}
}
