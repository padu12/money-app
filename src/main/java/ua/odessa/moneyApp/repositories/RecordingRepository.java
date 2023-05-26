package ua.odessa.moneyApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import ua.odessa.moneyApp.models.Person;
import ua.odessa.moneyApp.models.Recording;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface RecordingRepository extends JpaRepository<Recording, Integer>{
	List<Recording> findByPersonId(Person person);
	List<Recording> findAllByType(String type);
}
