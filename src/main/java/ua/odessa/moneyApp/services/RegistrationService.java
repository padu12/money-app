package ua.odessa.moneyApp.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.odessa.moneyApp.models.Person;
import ua.odessa.moneyApp.models.Users;
import ua.odessa.moneyApp.repositories.PeopleRepository;

@Service
public class RegistrationService {

  private final PeopleRepository peopleRepository;
  private final UsersService usersService;

  public RegistrationService(PeopleRepository peopleRepository, UsersService usersService) {
    this.peopleRepository = peopleRepository;
    this.usersService = usersService;
  }

  @Transactional
  public void register(Person person, Users users) {
    peopleRepository.save(person);
    usersService.save(users);
  }
}
