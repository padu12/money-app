package ua.odessa.moneyApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.odessa.moneyApp.models.Recording;

@Repository
public interface RecordRepository extends JpaRepository<Recording, Integer>{

}
