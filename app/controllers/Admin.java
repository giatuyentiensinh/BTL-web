package controllers;

import java.util.Map;

import org.bson.types.ObjectId;

import modules.Contact;
import modules.Phone;
import modules.UserBuyItem;
import modules.phone.Camera;
import modules.phone.Configuration;
import modules.phone.Network;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;

public class Admin extends Controller {

	
	/*Contact phone*/
	public static Result readBill() {
		Map<String, String[]> headers = request().headers();
		try {
			if (headers.get("xsrfHeaderName")[0] == null
					|| !"sercurity".equals(headers.get("xsrfHeaderName")[0]))
				return badRequest("Hacker");
		} catch (Exception e) {
			Logger.error(e.toString());
			return badRequest("Hacker");
		}
		
		return ok(Json.toJson(UserBuyItem.readBill()));
	}
	
	public static Result readComment() {
		Map<String, String[]> headers = request().headers();
		try {
			if (headers.get("xsrfHeaderName")[0] == null
					|| !"sercurity".equals(headers.get("xsrfHeaderName")[0]))
				return badRequest("Hacker");
		} catch (Exception e) {
			Logger.error(e.toString());
			return badRequest("Hacker");
		}
		
		return ok(Json.toJson(Contact.readContactAll()));
	}
	
	
	
	
	/*CRUD phone*/
	public static Result deletePhone(String id) {
		Map<String, String[]> headers = request().headers();
		try {
			if (headers.get("xsrfHeaderName")[0] == null
					|| !"sercurity".equals(headers.get("xsrfHeaderName")[0]))
				return badRequest("Hacker");
		} catch (Exception e) {
			Logger.error(e.toString());
			return badRequest("Hacker");
		}
		// Logger.info("Item " + id + " was delete");
		Phone.deletePhone(id);
		// Logger.info("Delete success: " + id);
		return ok("delete success");
	}

	public static Result updatePhone(String id) {
		Map<String, String[]> headers = request().headers();
		try {
			if (headers.get("xsrfHeaderName")[0] == null
					|| !"sercurity".equals(headers.get("xsrfHeaderName")[0]))
				return badRequest("Hacker");
		} catch (Exception e) {
			Logger.error(e.toString());
			return badRequest("Hacker");
		}
//		System.out.println("---------------------");
//		System.out.println(request().body().asJson());
//		System.out.println("----------------------");
		JsonNode json = request().body().asJson();
		JsonNode phoneJson = json.get("phone");
//		System.out.println(phoneJson);
		
		String name = phoneJson.get("name").textValue();
		String size = phoneJson.get("size").textValue();
		String weight = phoneJson.get("weight").textValue();
		double price = Double.parseDouble(phoneJson.get("price").textValue());
		String image = phoneJson.get("image").textValue();
		String provider = phoneJson.get("provider").textValue();
		
		
		JsonNode configJson = phoneJson.get("configuration");
		String os = configJson.get("OS").textValue();
		String display = configJson.get("display").textValue();
		String cpu = configJson.get("cpu").textValue();
		String ram = configJson.get("ram").textValue();
		String pin = configJson.get("pin").textValue();

		JsonNode cameraJson = configJson.get("camera");
		String front = cameraJson.get("front").textValue();
		String back = cameraJson.get("back").textValue();

		JsonNode network = configJson.get("network");
		String wifi = network.get("wifi").textValue();
		String bluetooth = network.get("bluetooth").textValue();

		Phone phone = new Phone(id, name, size, weight, price, image, null, provider,
				new Configuration(os, display, cpu, ram, pin, new Network(wifi,
						bluetooth), new Camera(front, back)));

		Phone.updatePhone(id, phone);
		
		return ok("update successfully");
	}
	
	public static Result createPhone() {
		Map<String, String[]> headers = request().headers();
		try {
			if (headers.get("xsrfHeaderName")[0] == null
					|| !"sercurity".equals(headers.get("xsrfHeaderName")[0]))
				return badRequest("Hacker");
		} catch (Exception e) {
			Logger.error(e.toString());
			return badRequest("Hacker");
		}
		JsonNode json = request().body().asJson();
		JsonNode phoneJson = json.get("phone");
//		System.out.println(phoneJson);
		
		String name = phoneJson.get("name").textValue();
		String size = phoneJson.get("size").textValue();
		String weight = phoneJson.get("weight").textValue();
		double price = Double.parseDouble(phoneJson.get("price").textValue());
		String image = phoneJson.get("image").textValue();
		String provider = phoneJson.get("provider").textValue();
		
		JsonNode configJson = phoneJson.get("configuration");
		String os = configJson.get("OS").textValue();
		String display = configJson.get("display").textValue();
		String cpu = configJson.get("cpu").textValue();
		String ram = configJson.get("ram").textValue();
		String pin = configJson.get("pin").textValue();

		JsonNode cameraJson = configJson.get("camera");
		String front = cameraJson.get("front").textValue();
		String back = cameraJson.get("back").textValue();

		JsonNode network = configJson.get("network");
		String wifi = network.get("wifi").textValue();
		String bluetooth = network.get("bluetooth").textValue();

		Phone phone = new Phone(new ObjectId().toString(), name, size, weight, price, image, null, provider,
				new Configuration(os, display, cpu, ram, pin, new Network(wifi,
						bluetooth), new Camera(front, back)));

		Phone.savePhone(phone);
		
		return ok("create successfully");
	}
}
