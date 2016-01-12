package modules;

import org.mongojack.DBQuery;
import org.mongojack.Id;
import org.mongojack.JacksonDBCollection;
import org.mongojack.MongoCollection;

import play.Play;

import com.fasterxml.jackson.annotation.JsonProperty;

@MongoCollection(name = "admin")
public class Admin {

	private static final JacksonDBCollection<Admin, String> coll = JacksonDBCollection
			.wrap(Connect.getCollection(Play.application().configuration()
					.getString("mongo.collection"), "admin"), Admin.class,
					String.class);

	@Id
	private String id;

	@JsonProperty("username")
	private String username;
	@JsonProperty("password")
	private String password;

	public Admin(@Id String id, @JsonProperty("username") String username,
			@JsonProperty("password") String password) {
		this.id = id;
		this.username = username;
		this.password = password;

	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static Admin find(String id) {
		if (id == null)
			return null;
		return coll.findOneById(id);
	}

	public static Admin findByNameAndPass(String name, String pass) {
		if (name == null || pass == null)
			return null;
		return coll.findOne(DBQuery.and(DBQuery.is("username", name),
				DBQuery.is("password", pass)));
	}

	public void updateName(String newName) {
		this.setUsername(newName);
		coll.updateById(this.id, this);
	}

	public void updatePass(String newPass) {
		this.setPassword(newPass);
		coll.updateById(this.id, this);
	}

	public static Long count() {
		return coll.count();
	}

	@Override
	public String toString() {
		return username + " : " + id + " : " + password;
	}

	/**
	 * save account admin
	 */
	public void save() {
		coll.save(this);
	}

}
