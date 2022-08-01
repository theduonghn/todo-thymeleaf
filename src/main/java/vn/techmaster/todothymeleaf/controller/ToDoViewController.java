package vn.techmaster.todothymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;
import vn.techmaster.todothymeleaf.service.ToDoService;

@Controller
@AllArgsConstructor
public class ToDoViewController {
    private final ToDoService toDoService;

    @GetMapping("/")
    public String getHome(Model model) {
        model.addAttribute("todos", toDoService.getToDos(""));
        return "index";
    }
}
