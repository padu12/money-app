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

    public void transaction(int id, int amount){
        Users user = usersRepository.findById(id).orElse(null);

        if(amount > 0) {
            user.setIncome(user.getIncome()+amount);
        } else {
            user.setOutgo(user.getOutgo()-amount);
        }
        user.setBalance(user.getBalance()+amount);
    }
}
