package controllers;

import play.*;
import play.api.routing.JavaScriptReverseRoute;
import play.data.Form;
import play.mvc.*;

import secure.SessionManager;
import views.html.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import secure.SessionManager.Login;

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

    //private static Configuration conf =  play.Play.application().configuration();


    public Result index() {
        return ok(index.render(" ! Your new application is ready. "));
    }

    public Result test(){
        return ok(" test ");
    }

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
