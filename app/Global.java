import json.JsonSetup;
import play.Application;
import play.GlobalSettings;

public class Global extends GlobalSettings {

    @Override
    public void onStart(final Application app) {

        JsonSetup.setup();

    }

}