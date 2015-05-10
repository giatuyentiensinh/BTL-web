package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import modules.Contact;
import modules.Item;
import modules.Phone;
import modules.UserBuyItem;

import org.bson.types.ObjectId;

import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http.Cookie;
import play.mvc.Http.Cookies;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;

public class Application extends Controller {

	public static Result index() {
		return ok();
	}

	/*Client*/
	public static Result category() {
		return ok(Json.toJson(Phone.findCategory()));
	}

	// contact
	public static Result contactByClient() {
		Map<String, String[]> headers = request().headers();
		try {
			if (headers.get("xsrfHeaderName")[0] == null
					|| !"sercurity".equals(headers.get("xsrfHeaderName")[0]))
				return badRequest("Hacker");
		} catch (Exception e) {
			Logger.error(e.toString());
			return badRequest("Hacker");
		}
		
		JsonNode comment = request().body().asJson();
		JsonNode contact = comment.get("comment");
		
		String username = contact.get("username").textValue();
		String email = contact.get("email").textValue();
		String subject = contact.get("subject").textValue();
		String content = contact.get("content").textValue();
		
		Contact message = new Contact(new ObjectId().toString(), username, email, subject, content);
		message.save();	
		return ok("OK");
	}
	
	// buy item 
	public static Result buyItem() {
		Map<String, String[]> headers = request().headers();
		try {
			if (headers.get("xsrfHeaderName")[0] == null
					|| !"sercurity".equals(headers.get("xsrfHeaderName")[0]))
				return badRequest("Hacker");
		} catch (Exception e) {
			Logger.error(e.toString());
			return badRequest("Hacker");
		}
		
		JsonNode request = request().body().asJson();
		
		List<Item> items = new ArrayList<Item>();
		
		for (JsonNode item : request.get("item")) {
			String id = item.get("id").textValue();
			int quantity = Integer.parseInt(item.get("quantity").textValue());
			items.add(new Item(id, quantity));
		}
		
		JsonNode jsonInfo = request.get("info");
		String username = jsonInfo.get("username").textValue();
		String phonenumber = jsonInfo.get("phonenumber").textValue();
		String email = jsonInfo.get("email").textValue();
		String address = jsonInfo.get("address").textValue();

		UserBuyItem userInfo = new UserBuyItem(new ObjectId().toString(),
				username, phonenumber, email, address, items);
		userInfo.save();
		return ok("OK");
		
	}	

	// load item in cart
	public static Result readCart() {
		Map<String, String[]> headers = request().headers();
		try {
			if (headers.get("xsrfHeaderName")[0] == null
					|| !"sercurity".equals(headers.get("xsrfHeaderName")[0]))
				return badRequest("Hacker");
		} catch (Exception e) {
			Logger.error(e.toString());
			return badRequest("Hacker");
		}
		Cookies cookies = request().cookies();
		List<Phone> response = new ArrayList<Phone>();
		for (Cookie cookie : cookies) {
			// cookie: cart_55438e16505f66d57fcf4cd9
			String[] id = cookie.name().split("_");
			if ("cart".equals(id[0]))
				response.add(Phone.findbyId(id[1]));
		}
		
		return ok(Json.toJson(response));
	}
	
	// load item in page
	public static Result phone() {
		Map<String, String[]> headers = request().headers();
		try {
			if (headers.get("xsrfHeaderName")[0] == null
					|| !"sercurity".equals(headers.get("xsrfHeaderName")[0]))
				return badRequest("Hacker");
		} catch(Exception e) {
			Logger.error(e.toString());
			return badRequest("Hacker");
		}
		
		String offset = headers.get("offset")[0];
		String count = headers.get("count")[0];
		if(offset == null || count == null)
			return badRequest("invalid request");
		if("0".equals(offset) && "0".equals(count))
			return ok(Json.toJson(Phone.count()));
		return ok(Json.toJson(Phone.findOffsetAndCount(offset, count)));
	}

	// load item by ID
	public static Result phoneId(String id) {
		Map<String, String[]> headers = request().headers();
		try {
			if (headers.get("xsrfHeaderName")[0] == null
					|| !"sercurity".equals(headers.get("xsrfHeaderName")[0]))
				return badRequest("Hacker");
		} catch(Exception e) {
			Logger.error(e.toString());
			return badRequest("Hacker");
		}
		
		return ok(Json.toJson(Phone.findbyId(id)));
	}
}
