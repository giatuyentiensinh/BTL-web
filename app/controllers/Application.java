package controllers;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import modules.Phone;
import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

    public static Result index() {
    	return ok();
    }
    

    public static Result addCartSession() {
    	DynamicForm form = Form.form().bindFromRequest();
    	Map<String, String> data = form.data();
    	for (String id : session().values()) {
			Logger.warn(id);
			if(id.equals(data.get("id"))) 
				return ok("exsited");
		}
    	session().put(UUID.randomUUID().toString(), data.get("id"));
    	
    	return ok("OK");
    }
    
    public static Result readListCart() {
    	List<Phone> cart = new ArrayList<Phone>();
    	for (String cart_id : session().values()) {
    		cart.add(Phone.findbyId(cart_id));
		}
    	return ok(Json.toJson(cart));
    }
    
    public static Result deleteCart() {
    	DynamicForm form = Form.form();
    	String cartId = form.data().get("id");
    	for (String key : session().keySet()) {
			if(session().get(key).equals(cartId)) 
				session().remove(key);
		}
    	return ok();
    }
    
    public static Result phone() {
    	return ok(Json.toJson(Phone.findAll()));
    }
    
    public static Result category() {
    	return ok(Json.toJson(Phone.findCategory()));
    }
    
    public static Result phoneId(String id) {
    	return ok(Json.toJson(Phone.findbyId(id)));
    }
}