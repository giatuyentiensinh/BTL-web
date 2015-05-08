package controllers;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import modules.Admin;
import modules.Secured;
import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class Authentication extends Controller {

	public final static String AUTH_TOKEN_HEADER = "X-AUTH-TOKEN";
	public static final String AUTH_TOKEN = "authToken";

	public static Admin getAdmin() {
		return (Admin) Http.Context.current().args.get("admin");
	}
	
	public static Result login() {
		DynamicForm form = Form.form().bindFromRequest();
		Map<String, String> data = form.data();
		String username = data.get("username");
		String password = data.get("password");
		Admin admin = Admin.findByNameAndPass(username, getSha512(password));
		if (admin == null)
			return badRequest("Kiem tra lai tai khoan");
		else {
			String token = admin.createToken();
			ObjectNode authToken = Json.newObject();
			authToken.put(AUTH_TOKEN, token);
			response().setCookie(AUTH_TOKEN, token);
			return redirect(routes.Assets.at("/public", "admin-index.html"));
		}
	}

	@Security.Authenticated(Secured.class)
	public static Result logout() {
		response().discardCookie(AUTH_TOKEN);
		getAdmin().deleteToken();
		return redirect("/");
	}
	
	public static String getSha512(String value) {
		try {
			byte[] pass = MessageDigest.getInstance("SHA-512").digest(
					value.getBytes("UTF-8"));
			StringBuffer sbPass = new StringBuffer();
			for (byte b : pass)
				sbPass.append(b);
			return sbPass.toString();
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			Logger.error(e.getMessage());
			return null;
		}
	}

}
