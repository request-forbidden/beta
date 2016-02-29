package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.*;
import play.api.routing.JavaScriptReverseRoute;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;

import scala.util.parsing.json.JSONObject$;
import security.SessionManager;
import views.html.*;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import security.SessionManager.Login;

import static org.reflections.ReflectionUtils.getAllMethods;
import static org.reflections.ReflectionUtils.withReturnType;

public class Application extends Controller {

//    @Transactional
    public Result doLogin() {

        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();

        if (loginForm.hasErrors()) {
            loginForm.data().put("password", "");
            return badRequest(views.html.login.render(loginForm));
        } else {

            loginForm.get().login(session(), request(), response());
            return redirect(routes.Application.index());
        }

    }

    public Result login() {
        return ok(login.render(Form.form(Login.class)));
    }

    public Result logout() {

        SessionManager.logout(session());

        return redirect(routes.Application.login());
    }

    public Result page(String page){ //AMD SUPPORT ? or not ? check one page !

        ObjectNode pageObject = Json.newObject();

        ArrayNode an = Json.newArray(); //list of
        ObjectNode on = Json.newObject(); //widgets

        an.add(on);

        pageObject.set("widgets", an);
        pageObject.put("title", "this is Title");
        pageObject.put("page", "page");

        return ok(pageObject);
    }

    //private static Configuration conf =  play.Play.application().configuration();


    public Result index() {
        return ok(index.render(" ! Your new application is ready. "));
    }

    public Result test(){
        return ok(" test ");
    }

    public Result upload(){

        Logger.info(" Uploading stuff .... ");

        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart picture = body.getFile("file");

        if (picture != null) {

            String fileName = picture.getFilename();
            String contentType = picture.getContentType();
            File file = picture.getFile();

            //whatever is uploaded

            return ok("File uploaded");

        } else {

            flash("error", "Missing file");

            return redirect(routes.Application.index());

        }

    }
    /*
        Make a gallery
        galleries sollutions


6

6
down vote
A good, basic starter strategy is like this:

Make a table called 'images'. Make sure it has an autoincrementing primary key (we'll call ID).
When a user uploads an image, insert a row into that table, including the original name of the file and the file extension. You'll probably also track uploading user, date/time, etc.
Get the last insert id from that query. Save the image to "your-image-path/(ID).(ext)" in the file system. For ex, "/images/350.png".
(Recommended) You can use the ID as the direct link to the image by making a simple wrapper script that loads the image by ID (thus obscuring the actual file name, or even keeping the images outside of the web directories). For ex, 'image.php?id=500' or '/image/500'. In that script, you can use the original file name from the table in the http header so that when they download it gets saved on the client under the original name.

Areas of enhancement:

Thumbnail Support. On upload, generate a thumbnail, and store it as ".thumbnail.jpg". Use a wrapper script for thumbnail retreival based on ID.
Hashing directories. If you expect more than 1000 or so images, you'll want to break them into multiple subdirectories so the file system doesnt get sluggish (the exact point this happens varies by OS and other factors). So if you have an image ID of 6500, you might store it in "/images/6/6500.png"
Security. Validate that an uploaded image is an actual image before saving it to disk. This is especially important if you are allowing direct access to the images - you dont want to accept a .php file that can get uploaded and then executed. Also make sure your web server config is setup to prevent that sort of thing by preventing the execution of scripts from the images directories.


     */


    public Result javascriptRoutes() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        Set<Object> reverseRoutes = new HashSet<>();
        Class[] routeClasses = {
                controllers.routes.javascript.class
        };

        for (Class routeClass : routeClasses) {
            for (Field f : routeClass.getFields()) {
                // get its methods
                for (Method m : getAllMethods(f.getType(), withReturnType(JavaScriptReverseRoute.class))) {
                    // for each method, add its result to the reverseRoutes
                    reverseRoutes.add(m.invoke(f.get(null)));
                }
            }
        }

        response().setContentType("text/javascript");

        return ok(Routes.javascriptRouter("jsRoutes",reverseRoutes.toArray(new play.api.routing.JavaScriptReverseRoute[reverseRoutes.size()])));
    }
}
