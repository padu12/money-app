package ua.odessa.moneyApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.odessa.moneyApp.models.Person;
import ua.odessa.moneyApp.models.Recording;

import java.util.List;
import java.util.Optional;

public interface RecordingRepository extends JpaRepository<Recording, Integer>{
	List<Recording> findByPersonId(Person person);
}
