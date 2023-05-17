package ua.odessa.moneyApp.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.odessa.moneyApp.models.Person;
import ua.odessa.moneyApp.models.Users;
import ua.odessa.moneyApp.services.RegistrationService;
import ua.odessa.moneyApp.util.PasswordValidator;
import ua.odessa.moneyApp.util.PersonValidator;

@Controller
@RequestMapping("/auth")
public class AuthController {

  private final PersonValidator personValidator;
  private final RegistrationService registrationService;
  private final PasswordValidator passwordValidator;

  public AuthController(PersonValidator personValidator, RegistrationService registrationService, PasswordValidator passwordValidator) {
    this.personValidator = personValidator;
    this.registrationService = registrationService;
    this.passwordValidator = passwordValidator;
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
                                 BindingResult bindingResult, @ModelAttribute("users") Users users) {
    personValidator.validate(person, bindingResult);
    passwordValidator.validate(person, bindingResult);
    users.setPerson(person);

    if (bindingResult.hasErrors()) {
      return "/auth/registration";
    }

    registrationService.register(person, users);

    return "redirect:/auth/login";
  }
}
