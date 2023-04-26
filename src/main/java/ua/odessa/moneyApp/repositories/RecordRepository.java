package ua.odessa.moneyApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.odessa.moneyApp.models.Recording;

public interface RecordRepository extends JpaRepository<Recording, Integer>{
	List<Recording> findByType(String type);
}
