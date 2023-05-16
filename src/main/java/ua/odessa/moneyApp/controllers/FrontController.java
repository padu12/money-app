package ua.odessa.moneyApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.odessa.moneyApp.services.UsersService;

@Controller
public class FrontController {

    private final UsersService usersService;

    public FrontController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/transaction")
    public String transaction(Model model) {
        model.addAttribute("user", usersService.findOne());
        return "curs/transictions";
    }

    @PostMapping("/make-transaction")
    public String makeTransaction(@RequestParam String number) {
        usersService.transaction(number);
        return "redirect:/transaction";
    }

    @GetMapping("/report")
    public String report() {
        return "curs/report";
    }

    @GetMapping("/investing")
    public String investing() {
        return "curs/investing";
    }

}
