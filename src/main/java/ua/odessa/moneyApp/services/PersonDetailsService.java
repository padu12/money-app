package ua.odessa.moneyApp.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.odessa.moneyApp.models.Person;
import ua.odessa.moneyApp.repositories.PeopleRepository;
import ua.odessa.moneyApp.security.PersonDetails;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {
  private final PeopleRepository peopleRepository;


  public PersonDetailsService(PeopleRepository peopleRepository) {
    this.peopleRepository = peopleRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<Person> person = peopleRepository.findByUsername(username);

    if (person.isEmpty()){
      throw new UsernameNotFoundException("User not found!");
    }

    return new PersonDetails(person.get());
  }
}
