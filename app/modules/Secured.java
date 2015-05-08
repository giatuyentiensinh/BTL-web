package modules;

import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;
import controllers.Authentication;

public class Secured extends Security.Authenticator{

	@Override
	public String getUsername(Context ctx) {
		Admin admin = null;
		String[] authenToken = ctx.request().headers().get(Authentication.AUTH_TOKEN_HEADER);
		if((authenToken != null) && (authenToken.length == 1) && (authenToken[0] != null)) {
			admin = Admin.findByToken(authenToken[0]);
			if(admin != null) {
				ctx.args.put("admin", admin);
				return admin.getUsername();
			}
		}
		return null;
	}
	
	@Override
	public Result onUnauthorized(Context ctx) {
		return redirect("/");
	}
	
	
}
