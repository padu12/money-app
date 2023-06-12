package ua.odessa.moneyApp;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.odessa.moneyApp.models.Person;
import ua.odessa.moneyApp.models.Recording;
import ua.odessa.moneyApp.repositories.PeopleRepository;
import ua.odessa.moneyApp.repositories.RecordingRepository;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RecordingRepositoryTests {

    @Autowired
    private RecordingRepository recordingRepository;

    @Autowired
    private PeopleRepository peopleRepository;
    private Person person;

    @Before
    public void createPerson() {

        person = new Person("tom@mail.com", "Tom", "123123123");

        peopleRepository.save(person);
    }

    @Test
    public void saveRecordingTest() {

        Recording recording = new Recording(person, "income", "may", 100, "Salary");

        recordingRepository.save(recording);

        Assertions.assertThat(recording.getId()).isGreaterThan(0);
    }

    @Test
    public void getRecordingTest() {

        Recording recording = recordingRepository.findById(39).get();

        Assertions.assertThat(recording.getId()).isEqualTo(39);
    }

    @Test
    public void getListOfRecordingsTest() {

        List<Recording> list = recordingRepository.findAll();

        Assertions.assertThat(list.size()).isGreaterThan(0);
    }
}
