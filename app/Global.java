import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import play.Application;
import play.GlobalSettings;
import play.libs.Json;


/**
 * Global by masakra
 */
public class Global extends GlobalSettings {


    /**
     * Sync the context lifecycle with Play's.
     */
    @Override
    public void onStart(final Application app) {

        super.onStart(app);


        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibilityChecker(objectMapper.getSerializationConfig().getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.registerModule(new JodaModule());
        Json.setObjectMapper(objectMapper);
    }

    /**
     * Sync the context lifecycle with Play's.
     */
    @Override
    public void onStop(final Application app) {
        super.onStop(app);
    }


}