package ua.odessa.moneyApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.odessa.moneyApp.services.RecordingService;

@Controller
public class FrontController {

    private final RecordingService recordingService;

    public FrontController(RecordingService recordingService) {
        this.recordingService = recordingService;
    }

    @GetMapping("/")
    public String none() {
        return "redirect:/main";
    }

    @GetMapping("/transaction")
    public String transaction(Model model) {
        model.addAttribute("records", recordingService.findAll());
        return "curs/transictions";
    }

    @GetMapping("/main")
    public String home() {
        return "curs/main";
    }

    @GetMapping("/about")
    public String about() {
        return "curs/about";
    }

    @PostMapping("/make-transaction")
    public String makeTransaction(@RequestParam String number) {
//        usersService.transaction(number);
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
