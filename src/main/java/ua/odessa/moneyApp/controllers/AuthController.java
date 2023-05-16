package ua.odessa.moneyApp.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.odessa.moneyApp.models.Person;
import ua.odessa.moneyApp.services.RegistrationService;
import ua.odessa.moneyApp.util.PersonValidator;

@Controller
@RequestMapping("/auth")
public class AuthController {

  private final PersonValidator personValidator;
  private final RegistrationService registrationService;

  public AuthController(PersonValidator personValidator, RegistrationService registrationService) {
    this.personValidator = personValidator;
    this.registrationService = registrationService;
  }

  @GetMapping("/login")
  public String getLoginPage() {
    return "auth/login";
  }

  @GetMapping("/registration")
  public String getRegistrationPage(@ModelAttribute("person") Person person) {
    return "auth/registration";
  }

  @PostMapping("/registration")
  public String postRegistration(@ModelAttribute("person") @Valid Person person,
                                 BindingResult bindingResult) {
    personValidator.validate(person, bindingResult);

    if (bindingResult.hasErrors()) {
      return "/auth/registration";
    }

    registrationService.register(person);

    return "redirect:/auth/login";
  }
}
