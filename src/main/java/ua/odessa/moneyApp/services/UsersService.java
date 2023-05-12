package ua.odessa.moneyApp.services;

import org.springframework.stereotype.Service;
import ua.odessa.moneyApp.models.Users;
import ua.odessa.moneyApp.repositories.UsersRepository;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users findOne() {
        return usersRepository.findById(1).orElse(null);
    }

    public void transaction(String amount){
        int amount1 = Integer.valueOf(amount);
        Users user = usersRepository.findById(1).orElse(null);

        if(amount1 > 0) {
            user.setIncome(user.getIncome()+amount1);
        } else {
            user.setOutgo(user.getOutgo()-amount1);
        }
        user.setBalance(user.getBalance()+amount1);
        usersRepository.save(user);
    }
}
