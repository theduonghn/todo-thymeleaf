package vn.techmaster.todothymeleaf.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import vn.techmaster.todothymeleaf.exception.NotFoundException;
import vn.techmaster.todothymeleaf.model.ToDo;
import vn.techmaster.todothymeleaf.request.CreateToDoRequest;
import vn.techmaster.todothymeleaf.request.UpdateToDoRequest;

@Service
public class ToDoService {
    private List<ToDo> toDos;
    private Random rd = new Random();

    public ToDoService() {
        toDos = new ArrayList<>();
        toDos.add(new ToDo(rd.nextInt(100), "Đi làm", false));
        toDos.add(new ToDo(rd.nextInt(100), "Học Java", true));
        toDos.add(new ToDo(rd.nextInt(100), "Học Spring Boot", true));
        toDos.add(new ToDo(rd.nextInt(100), "Tập thể dục", false));
        toDos.add(new ToDo(rd.nextInt(100), "Xem phim", true));
    }

    public List<ToDo> getToDos(String status) {
        if (status == null) {
            return this.toDos;
        } else if (status.equals("true")) {
            return toDos.stream().filter(ToDo::isStatus).collect(Collectors.toList());
        } else if (status.equals("false")) {
            return toDos.stream().filter(toDo -> !toDo.isStatus()).collect(Collectors.toList());
        } else {
            return this.toDos;
        }
    }

    // Helper method to find todo by id
    public Optional<ToDo> findToDoById(int id) {
        return toDos.stream().filter(toDo -> toDo.getId() == id).findFirst();
    }

    public ToDo getToDoById(int id) {
        Optional<ToDo> toDoOptional = findToDoById(id);
        // if (toDoOptional.isPresent()) {
        // return toDoOptional.get();
        // } else {
        // throw new NotFoundException("Không tìm thấy công việc có id = " + id);
        // }

        return toDoOptional.orElseThrow(() -> new NotFoundException("Không tìm thấy công việc có id = " + id));
    }

    public ToDo createToDo(CreateToDoRequest request) {
        // Có thể validate title nếu để trống

        ToDo toDo = new ToDo(rd.nextInt(100), request.getTitle(), false);
        toDos.add(toDo);
        return toDo;
    }

    public ToDo updateToDo(int id, UpdateToDoRequest request) {
        ToDo toDo = this.getToDoById(id);
        toDo.setTitle(request.getTitle());
        toDo.setStatus(request.isStatus());

        return toDo;
    }

    public void deleteToDo(int id) {
        ToDo toDo = getToDoById(id);
        this.toDos.removeIf(t -> t.getId() == id);
    }
}
