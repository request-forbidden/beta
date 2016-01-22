package services;

import models.User;
import repository.Repository;

public interface UserRepository  {

    void save(User user);

    User getByAuth(String login, String password);

}
