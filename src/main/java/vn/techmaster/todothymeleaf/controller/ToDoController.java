package vn.techmaster.todothymeleaf.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import vn.techmaster.todothymeleaf.model.ToDo;
import vn.techmaster.todothymeleaf.request.CreateToDoRequest;
import vn.techmaster.todothymeleaf.request.UpdateToDoRequest;
import vn.techmaster.todothymeleaf.service.ToDoService;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ToDoController {
    private final ToDoService toDoService;

    // Get all todos
    @GetMapping("/todos")
    public List<ToDo> getToDos(@RequestParam(required = false) String status) {
        return toDoService.getToDos(status);
    }

    // Get todo by id
    @GetMapping("/todos/{id}")
    public ToDo getToDoById(@PathVariable int id) {
        return toDoService.getToDoById(id);
    }

    // Create todo
    @PostMapping("/todos")
    // @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ToDo> createToDo(@RequestBody CreateToDoRequest request) {
        ToDo toDo = toDoService.createToDo(request);
        return new ResponseEntity<>(toDo, HttpStatus.CREATED);
    }

    // Update todo
    @PutMapping("/todos/{id}")
    public ToDo updateToDo(@PathVariable int id, @RequestBody UpdateToDoRequest request) {
        return toDoService.updateToDo(id, request);
    }

    // Delete todo
    @DeleteMapping("/todos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteToDo(@PathVariable int id) {
        toDoService.deleteToDo(id);
    }
}
