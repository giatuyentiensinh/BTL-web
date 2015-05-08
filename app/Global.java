import modules.Connect;
import play.Application;
import play.GlobalSettings;

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
}
