
/*
 * Copyright 2012 Steve Chaloner
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package secure;

import be.objectify.deadbolt.core.models.Subject;
import be.objectify.deadbolt.java.AbstractDeadboltHandler;
import be.objectify.deadbolt.java.DynamicResourceHandler;

import play.libs.F;
import play.mvc.Http;
import play.mvc.Result;

import java.util.Optional;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
public class MyDeadboltHandler extends AbstractDeadboltHandler{

    public F.Promise<Optional<Result>> beforeAuthCheck(final Http.Context context){
        // returning null means that everything is OK.  Return a real result if you want a redirect to a login page or
        // somewhere else
        return F.Promise.promise(Optional::empty);
    }

    public F.Promise<Optional<Subject>> getSubject(final Http.Context context){
        // in a real application, the user name would probably be in the session following a login process
        return F.Promise.promise(() -> Optional.ofNullable(SessionManager.getUserFromSessionList(2L))); //AuthorisedUser.findByUserName("steve")
    }

    public F.Promise<Optional<DynamicResourceHandler>> getDynamicResourceHandler(final Http.Context context){
        return null;// F.Promise.promise(() -> Optional.of(new MyDynamicResourceHandler()));
    }

    @Override
    public F.Promise<Result> onAuthFailure(final Http.Context context,final String content){
        // you can return any result from here - forbidden, etc
//        return F.Promise.promise(() -> ok(views.html.login.render()));

        return F.Promise.promise(new F.Function0<Result>() {
            @Override
            public Result apply() throws Throwable {
                return redirect(controllers.routes.Application.login());
            }
        });

    }


}
