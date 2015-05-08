package modules;

import java.net.UnknownHostException;

import play.Configuration;
import play.Logger;
import play.Play;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class Connect {

	private static MongoClient connect = null;

	public static void connect() {
		try {
			connect = new MongoClient(getConfig().getString("mongo.host"),
					getConfig().getInt("mongo.port"));
		} catch (UnknownHostException e) {
			Logger.error(e.getMessage());
		}
	}

	public static void disConnect() {
		if (connect != null) {
			connect.close();
		}
	}
	
	protected static DBCollection getCollection(String db, String col) {
		DB database = connect.getDB(db);
		return database.getCollection(col);
	}

	private static Configuration getConfig() {
		return Play.application().configuration();
	}
}
