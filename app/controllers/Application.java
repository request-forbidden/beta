package controllers;

import be.objectify.deadbolt.java.actions.SubjectPresent;
import play.*;
import play.api.routing.JavaScriptReverseRoute;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;

import security.SessionManager.Login;
import views.html.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import static org.reflections.ReflectionUtils.getAllMethods;
import static org.reflections.ReflectionUtils.withReturnType;

public class Application extends Controller {


    @SubjectPresent
    public Result index() {
        return ok(index.render(" ! Your new application is ready. "));
    }

    public Result test(){
        return ok(" test ");
    }

    public Result login() {
        return ok(login.render(Form.form(Login.class)));
    };

    @Transactional
    public Result doLogin() {

        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();

        if (loginForm.hasErrors()) {
            loginForm.data().put("password", "");
            return badRequest(login.render(loginForm));
        } else {
            loginForm.get().login(session(), request(), response());
            return redirect(routes.Application.index());
        }
    };

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
