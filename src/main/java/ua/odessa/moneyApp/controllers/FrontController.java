package ua.odessa.moneyApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.odessa.moneyApp.models.Recording;
import ua.odessa.moneyApp.services.PersonService;
import ua.odessa.moneyApp.services.RecordingService;

@Controller
public class FrontController {

    private final RecordingService recordingService;
    private final PersonService personService;

    public FrontController(RecordingService recordingService, PersonService personService) {
        this.recordingService = recordingService;
        this.personService = personService;
    }

    @GetMapping("/")
    public String none() {
        return "redirect:/main";
    }

    @GetMapping("/transaction")
    public String transaction(@ModelAttribute("record") Recording record, Model model) {
        model.addAttribute("records", recordingService.findAll());
        model.addAttribute("balance", personService.getBalance());
        model.addAttribute("income", personService.getIncome());
        model.addAttribute("outgo", personService.getOutgo());

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
    public String makeTransaction(@ModelAttribute("record") Recording recording, @RequestParam String month) {
        recordingService.save(recording, month);
        return "redirect:/transaction";
    }

    @GetMapping("/report")
    public String report(Model model) {
        model.addAttribute("incomeRecords", recordingService.findAllIncomeByMonthIn());
        model.addAttribute("outgoRecords", recordingService.findAllIncomeByMonthOut());
        return "curs/report";
    }

    @GetMapping("/investing")
    public String investing() {
        return "curs/investing";
    }

}
