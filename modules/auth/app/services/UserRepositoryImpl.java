package services;

import models.Group;
import models.User;
import play.db.jpa.JPA;
import repository.Repository;

import javax.inject.Inject;

public class UserRepositoryImpl implements UserRepository {

    Repository<User> userRepository;
    Repository<Group> groupRepository;

    @Inject
    public UserRepositoryImpl(Repository<User> userRepository, Repository<Group> groupRepository){
        this.userRepository=userRepository;
        this.groupRepository=groupRepository;
    }

    @Override
    public void save(User user) {
        if(user.getId() == null) JPA.em().persist(user);
        else JPA.em().merge(user);
    }

    @Override
    public User getByAuth(String login, String password) {
        return null;
    }

}
