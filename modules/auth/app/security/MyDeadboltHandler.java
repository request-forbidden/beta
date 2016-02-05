package security;

import be.objectify.deadbolt.core.models.Subject;
import be.objectify.deadbolt.java.AbstractDeadboltHandler;

import play.libs.F;
import play.mvc.Http;
import play.mvc.Result;

import java.util.Optional;

import static play.mvc.Results.forbidden;
import static play.mvc.Results.redirect;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
public class MyDeadboltHandler extends AbstractDeadboltHandler
{
    public F.Promise<Optional<Result>> beforeAuthCheck(final Http.Context context)
    {
        // returning null means that everything is OK.  Return a real result if you want a redirect to a login page or
        // somewhere else
        return F.Promise.promise(Optional::empty);
    }

    public F.Promise<Optional<Subject>> getSubject(final Http.Context context)
    {
        // in a real application, the user name would probably be in the session following a login process
//        return F.Promise.promise(() -> Optional.ofNullable(AuthorisedUser.findByUserName("steve")));
        return F.Promise.promise(() -> Optional.ofNullable(SessionManager.getUserForSession(context)));
    }
/*
    public F.Promise<Optional<DynamicResourceHandler>> getDynamicResourceHandler(final Http.Context context)
    {
        return F.Promise.promise(() -> Optional.of(new MyDynamicResourceHandler()));
    }
*/
    @Override
    public F.Promise<Result> onAuthFailure(final Http.Context context,
                                           final String content)
    {
        // you can return any result from here - forbidden, etc
//        return F.Promise.promise(() -> ok(accessFailed.render()));

        if(context.request().headers().get("X-Requested-With")!=null && context.request().headers().get("X-Requested-With")[0].equals("XMLHttpRequest")) {
            return F.Promise.promise(new F.Function0<Result>() {
                @Override
                public Result apply() throws Throwable {
                    return forbidden("redirect_login");
                }
            });
        }
        return F.Promise.promise(new F.Function0<Result>() {
            @Override
            public Result apply() throws Throwable {
                return redirect("/login");
            }
        });
    }
}

