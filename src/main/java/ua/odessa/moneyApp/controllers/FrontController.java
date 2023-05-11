package ua.odessa.moneyApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ua.odessa.moneyApp.services.UsersService;

@Controller
public class FrontController {

    private final UsersService usersService;

    public FrontController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/transaction")
    public String transaction(Model model, @ModelAttribute("number") Integer integer) {
        model.addAttribute("user", usersService.findOne());
        return "curs/transictions";
    }

    //TODO make method for changing the balance
}
