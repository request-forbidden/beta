package modules;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import models.Group;
import models.User;
import repository.HibernateRepository;
import repository.Repository;
import services.UserRepository;
import services.UserRepositoryImpl;

public class AuthModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(UserRepository.class).to(UserRepositoryImpl.class);

        bind(new TypeLiteral<Repository<User>>(){}).to(new TypeLiteral<HibernateRepository<User>>(){});
        bind(new TypeLiteral<Repository<Group>>(){}).to(new TypeLiteral<HibernateRepository<Group>>(){});

    }

}
