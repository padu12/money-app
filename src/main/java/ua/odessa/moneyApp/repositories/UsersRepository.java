package ua.odessa.moneyApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.odessa.moneyApp.models.Person;
import ua.odessa.moneyApp.models.Users;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByPerson(Person person);
}
