package ua.odessa.moneyApp.services;

import org.apache.catalina.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.odessa.moneyApp.models.Users;
import ua.odessa.moneyApp.repositories.UsersRepository;
import ua.odessa.moneyApp.security.PersonDetails;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void save(Users users) {
        usersRepository.save(users);
    }

    public Users findOne() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        return usersRepository.findByPerson(personDetails.getPerson()).orElse(null);
    }

    public void transaction(String amount){
        int amount1 = Integer.valueOf(amount);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        Users user = usersRepository.findByPerson(personDetails.getPerson()).orElse(null);

        if(amount1 > 0) {
            user.setIncome(user.getIncome()+amount1);
        } else {
            user.setOutgo(user.getOutgo()-amount1);
        }
        user.setBalance(user.getBalance()+amount1);
        usersRepository.save(user);
    }
}
