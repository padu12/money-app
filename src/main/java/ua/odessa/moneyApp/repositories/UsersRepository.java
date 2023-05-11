package ua.odessa.moneyApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.odessa.moneyApp.models.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
}
