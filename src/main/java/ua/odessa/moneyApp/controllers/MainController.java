package ua.odessa.moneyApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ua.odessa.moneyApp.models.Recording;
import ua.odessa.moneyApp.services.RecordingService;

@Controller
public class MainController {

	private final RecordingService recordService;
	
	@Autowired
	public MainController(RecordingService recordService) {
		super();
		this.recordService = recordService;
	}

	@GetMapping("/home")
	public String goHome(Model model) {
//		model.addAttribute("balance", recordService.getBalance());
		model.addAttribute("records", recordService.findAll());
		return "home";
	}
	
	@GetMapping("/create")
	public String create(@ModelAttribute("record") Recording record) {
		return "create";
	}
	
	@PostMapping("/create1")
	public String newRecord(@ModelAttribute("record") Recording record) {
		recordService.save(record);
		return "redirect:/home";
	}
	
	@GetMapping("/{id}")
	public String edit(Model model, @PathVariable("id") int id) {
		model.addAttribute("record", recordService.findById(id));
		return "change";
	}
	
	@PostMapping("/edit/{id}")
	public String change(@PathVariable("id") int id, 
						@ModelAttribute("record") Recording record) {
		recordService.save(record);
		return "redirect:/home";
	}
	
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		recordService.delete(id);
		return "redirect:/home";
	}
}
