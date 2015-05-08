package modules;

import java.util.UUID;

import org.mongojack.DBQuery;
import org.mongojack.Id;
import org.mongojack.JacksonDBCollection;
import org.mongojack.MongoCollection;

import com.fasterxml.jackson.annotation.JsonProperty;

@MongoCollection(name = "admin")
public class Admin {

	private static final JacksonDBCollection<Admin, String> coll = JacksonDBCollection
			.wrap(Connect.getCollection("btlweb", "admin"), Admin.class,
					String.class);
	@Id
	private String id;

	@JsonProperty("username")
	private String username;
	@JsonProperty("password")
	private String password;
	@JsonProperty("token")
	private String token;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String createToken() {
		this.setToken(UUID.randomUUID().toString());
		coll.updateById(this.id, this);
		return this.token;
	}

	public void deleteToken() {
		this.setToken(null);
		coll.updateById(this.id, this);
	}

	public Admin(@Id String id, @JsonProperty("username") String username,
			@JsonProperty("password") String password,
			@JsonProperty("token") String token) {
		this.id = id;
		this.username = username;
		this.token = token;
		this.password = password;
	}

	public static Admin find(String id) {
		if(id == null)
			return null;
		return coll.findOneById(id);
	}

	public static Admin findByNameAndPass(String name, String pass) {
		if(name == null || pass == null)
			return null;
		return coll.findOne(DBQuery.and(DBQuery.is("username", name),
				DBQuery.is("password", pass)));
	}

	@Override
	public String toString() {
		return username + " : " + id + " : " + password + ":  " + token;
	}

	public static Admin findByToken(String token) {
		if (token == null)
			return null;
		return coll.findOne(DBQuery.is("token", token));
	}

}
