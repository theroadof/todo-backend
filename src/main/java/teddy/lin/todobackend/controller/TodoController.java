package teddy.lin.todobackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import teddy.lin.todobackend.dto.RequestTodo;
import teddy.lin.todobackend.dto.ResponseTodo;
import teddy.lin.todobackend.service.TodoService;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<ResponseTodo> getAll() {
        return todoService.getAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseTodo add(@RequestBody RequestTodo requestTodo) {
        return todoService.save(requestTodo);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public ResponseTodo update(@PathVariable Integer id, @RequestBody RequestTodo requestTodo) {
        return todoService.update(id, requestTodo);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        todoService.delete(id);
    }
}
