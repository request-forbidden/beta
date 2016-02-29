package controllers;

import com.google.inject.Inject;
import models.User;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.db.jpa.Transactional;
import repository.Repository;

import java.util.List;

public class CAuth extends Controller {

    private final Repository<User> userRepository;

    @Inject
    public CAuth(Repository<User> userRepository){
        this.userRepository = userRepository;
    }

    public Result page(){

        return ok(); // here return page object - from commons ui. pageObject / widget object
    }

    @Transactional
    public Result test() {
        User u = userRepository.findById(2L);
        return ok(u.getJson());
    }

    @Transactional
    public Result list() {
        List<User> u = userRepository.findAll();
        return ok(Json.toJson(u));
    }

}
