import modules.Connect;
import play.Application;
import play.GlobalSettings;
import play.libs.F.Promise;
import play.mvc.Controller;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;

public class Global extends GlobalSettings {

	@Override
	public void onStart(Application app) {
		super.onStart(app);
		Connect.connect();
	}
	
	@Override
	public void onStop(Application app) {
		super.onStop(app);
		Connect.disConnect();
	}
	
	@Override
	public Promise<Result> onError(RequestHeader arg0, Throwable arg1) {
		return Promise.pure(Controller.badRequest("Hacker, you not permission"));
	}
	
}
