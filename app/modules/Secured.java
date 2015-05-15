package modules;

import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;
import controllers.Authentication;

public class Secured extends Security.Authenticator {

	@Override
	public String getUsername(Context ctx) {
		return ctx.session().get(Authentication.AUTH_TOKEN_HEADER);
	}

	@Override
	public Result onUnauthorized(Context ctx) {
		return badRequest("not login");
	}

}
