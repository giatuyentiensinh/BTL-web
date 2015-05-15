package controllers;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.UUID;

import modules.Admin;
import modules.Secured;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import com.fasterxml.jackson.databind.JsonNode;

public class Authentication extends Controller {

	public final static String AUTH_TOKEN_HEADER = "X-AUTH-TOKEN";

	public static Result login() {
		Map<String, String[]> headers = request().headers();
		try {
			if (headers.get("xsrfHeaderName")[0] == null
					|| !"sercurity".equals(headers.get("xsrfHeaderName")[0]))
				return badRequest("Hacker");
		} catch (Exception e) {
			Logger.error(e.toString());
			return badRequest("Hacker");
		}

		JsonNode data = request().body().asJson();
//		System.out.println(data);

		String name = data.get("username").textValue();
		String pass = data.get("password").textValue();

		Admin admin = Admin.findByNameAndPass(name, getSha512(pass));
		if (admin == null)
			return badRequest("Check account again");
		else {
			session().clear();
			String cookie = UUID.randomUUID().toString();
			session().put(AUTH_TOKEN_HEADER, cookie);
			return ok("OK");
		}
	}
	
	@Security.Authenticated(Secured.class)
	public static Result logout() {
		session().remove(AUTH_TOKEN_HEADER);
		return ok("logout success");
	}

	@Security.Authenticated(Secured.class)
	public static Result changeName() {
		Map<String, String[]> headers = request().headers();
		try {
			if (headers.get("xsrfHeaderName")[0] == null
					|| !"sercurity".equals(headers.get("xsrfHeaderName")[0]))
				return badRequest("Hacker");
		} catch (Exception e) {
			Logger.error(e.toString());
			return badRequest("Hacker");
		}
		
		JsonNode body = request().body().asJson();
		String username = body.get("username").textValue();
		String password = body.get("password").textValue();
		String newname = body.get("newname").textValue();
		
		Admin admin = Admin.findByNameAndPass(username, getSha512(password));
		
//		System.out.println(admin);
		if(admin == null)
			return badRequest("incorrect");
		
		admin.updateName(newname);
		
		return ok("Update name success");
	}
	
	@Security.Authenticated(Secured.class)
	public static Result changePass() {
		Map<String, String[]> headers = request().headers();
		try {
			if (headers.get("xsrfHeaderName")[0] == null
					|| !"sercurity".equals(headers.get("xsrfHeaderName")[0]))
				return badRequest("Hacker");
		} catch (Exception e) {
			Logger.error(e.toString());
			return badRequest("Hacker");
		}
		
		JsonNode body = request().body().asJson();
		String username = body.get("username").textValue();
		String password = body.get("password").textValue();
		String newpass = body.get("newpass").textValue();
		
		Admin admin = Admin.findByNameAndPass(username, getSha512(password));
//		System.out.println(admin);
		if(admin == null) 
			return badRequest("incorrect");

		admin.updatePass(getSha512(newpass));

		return ok("Update pass success");
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
