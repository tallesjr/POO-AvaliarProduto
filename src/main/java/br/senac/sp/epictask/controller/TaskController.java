package br.senac.sp.epictask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.senac.sp.epictask.model.Task;
import br.senac.sp.epictask.repository.TaskRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("avaliar")
public class TaskController {

	@Autowired //Injeção de Dependência
	TaskRepository repository;
	
	@GetMapping
	public String index(Model model) {
		var lista = repository.findAll();
		model.addAttribute("tasks", lista);
		return "task/index";
	}

	@GetMapping("add") // localhost:8080/task/new
	public String form(Task task){
		return "task/form";
	}

	@PostMapping("add")
	public String save(@Valid Task task, BindingResult result, RedirectAttributes redirect){
		if (result.hasErrors()) return "task/form";
		redirect.addFlashAttribute("message", "Tarefa cadastrada com sucesso");
		repository.save(task);
		return "redirect:/task";
	}

}
