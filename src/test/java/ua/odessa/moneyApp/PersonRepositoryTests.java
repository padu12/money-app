package ua.odessa.moneyApp;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.odessa.moneyApp.models.Person;
import ua.odessa.moneyApp.repositories.PeopleRepository;
import ua.odessa.moneyApp.repositories.RecordingRepository;
import ua.odessa.moneyApp.services.PersonService;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PersonRepositoryTests {

    @Autowired
    private PeopleRepository peopleRepository;

    @Test
    public void savePersonTest() {

        Person person = new Person("tom@mail.com", "TOM", "123123123");

        peopleRepository.save(person);

        Assertions.assertThat(person.getId()).isGreaterThan(0);
    }

    @Test
    public void getPersonTest() {

        Person person = peopleRepository.findById(7).get();

        Assertions.assertThat(person.getId()).isEqualTo(7);
    }

    @Test
    public void getListOfPeopleTest() {

        List<Person> list = peopleRepository.findAll();

        Assertions.assertThat(list.size()).isGreaterThan(0);
    }


}
