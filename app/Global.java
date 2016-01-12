import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modules.Admin;
import modules.Connect;
import modules.Contact;
import modules.Item;
import modules.Phone;
import modules.UserBuyItem;
import modules.phone.Camera;
import modules.phone.Configuration;
import modules.phone.Network;
import play.Application;
import play.GlobalSettings;
import play.Play;
import play.libs.F.Promise;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;

import controllers.Authentication;

public class Global extends GlobalSettings {

	@Override
	public void onStart(Application app) {
		super.onStart(app);
		Connect.connect();
		if (Phone.count() == 0)
			initDataPhone(app);
		if (Contact.count() == 0)
			initDataContact(app);
		if (UserBuyItem.count() == 0)
			initDataUserByItem(app);
		if (Admin.count() == 0)
			initDataAccountAdmin(app);
	}

	@Override
	public void onStop(Application app) {
		super.onStop(app);
		Connect.disConnect();
	}

	@Override
	public Promise<Result> onError(RequestHeader arg0, Throwable arg1) {
		return Promise
				.pure(Controller.badRequest("Hacker, you not permission"));
	}

	static void initDataPhone(Application app) {
		String phone_path = Play.application().configuration()
				.getString("phone_dir");
		File phoneFile = Play.application().getFile(phone_path);
		try {
			FileReader fr = new FileReader(phoneFile);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			while ((line = br.readLine()) != null) {
				JsonNode parse = Json.parse(line);
				String id = parse.get("_id").asText();
				String name = parse.get("name").asText();
				String size = parse.get("size").asText();
				String weight = parse.get("weight").asText();
				double price = parse.get("price").asDouble();
				String image = parse.get("image").asText();
				String provider = parse.get("provider").asText();

				String front = null;
				if (parse.get("configuration").get("camera").get("front") != null)
					front = parse.get("configuration").get("camera")
							.get("front").asText();
				String back = parse.get("configuration").get("camera")
						.get("back").asText();
				String wifi = parse.get("configuration").get("network")
						.get("wifi").asText();
				String bluetooth = parse.get("configuration").get("network")
						.get("bluetooth").asText();

				String os = null;
				if (parse.get("configuration").get("OS") != null)
					os = parse.get("configuration").get("OS").asText();
				String display = null;
				if (parse.get("configuration").get("display") != null)
					display = parse.get("configuration").get("display")
							.asText();
				String cpu = null;
				if (parse.get("configuration").get("cpu") != null)
					cpu = parse.get("configuration").get("cpu").asText();
				String ram = null;
				if (parse.get("configuration").get("ram") != null)
					ram = parse.get("configuration").get("ram").asText();
				String pin = null;
				if (parse.get("configuration").get("pin") != null)
					pin = parse.get("configuration").get("pin").asText();
				Camera camera = new modules.phone.Camera(front, back);
				Network network = new modules.phone.Network(wifi, bluetooth);
				Configuration configuration = new modules.phone.Configuration(
						os, display, cpu, ram, pin, network, camera);

				Phone phone = new Phone(id, name, size, weight, price, image,
						null, provider, configuration);

				Phone.savePhone(phone);
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void initDataContact(Application app) {
		String contact_dir = Play.application().configuration()
				.getString("contact_dir");
		File contactFile = Play.application().getFile(contact_dir);
		try {
			FileReader fr = new FileReader(contactFile);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			while ((line = br.readLine()) != null) {
				JsonNode parse = Json.parse(line);
				String id = parse.get("_id").asText();
				String name = parse.get("name").asText();
				String email = parse.get("email").asText();
				String subject = parse.get("subject").asText();
				String content = parse.get("content").asText();
				Contact contact = new Contact(id, name, email, subject, content);
				contact.save();
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void initDataUserByItem(Application app) {
		String userbyitem_dir = Play.application().configuration()
				.getString("userbyitem_dir");
		File userbyItemFile = Play.application().getFile(userbyitem_dir);
		try {
			FileReader fr = new FileReader(userbyItemFile);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			while ((line = br.readLine()) != null) {
				JsonNode parse = Json.parse(line);
				String id = parse.get("_id").asText();
				String username = parse.get("username").asText();
				String phonenumber = parse.get("phonenumber").asText();
				String email = parse.get("email").asText();
				String address = parse.get("address").asText();
				List<Item> items = new ArrayList<Item>();
				Iterator<JsonNode> it = parse.get("item").iterator();
				while (it.hasNext()) {
					JsonNode node = it.next();
					String idItem = node.get("id").asText();
					int quantity = node.get("quantity").asInt();
					items.add(new Item(idItem, quantity));
				}
				UserBuyItem userBuyItem = new UserBuyItem(id, username,
						phonenumber, email, address, items);
				userBuyItem.save();
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void initDataAccountAdmin(Application app) {
		Admin admin = new Admin("555360cdccbcc04cb1a07c10", "admin",
				Authentication.getSha512("admin123"));
		admin.save();
	}

}
