package ua.odessa.moneyApp.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.odessa.moneyApp.models.Person;
import ua.odessa.moneyApp.repositories.PeopleRepository;

@Service
public class RegistrationService {

  private final PeopleRepository peopleRepository;

  public RegistrationService(PeopleRepository peopleRepository) {
    this.peopleRepository = peopleRepository;
  }

  @Transactional
  public void register(Person person) {
    peopleRepository.save(person);
  }
}
