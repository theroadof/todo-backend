package teddy.lin.todobackend.service;

import org.springframework.stereotype.Service;
import teddy.lin.todobackend.dto.RequestTodo;
import teddy.lin.todobackend.dto.ResponseTodo;
import teddy.lin.todobackend.mapper.TodoMapper;
import teddy.lin.todobackend.model.Todo;
import teddy.lin.todobackend.repository.TodoRepository;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    private final TodoMapper todoMapper;

    public TodoService(TodoRepository todoRepository, TodoMapper todoMapper) {
        this.todoRepository = todoRepository;
        this.todoMapper = todoMapper;
    }

    public List<ResponseTodo> getAll() {
        List<Todo> todos = todoRepository.findAll();
        return todoMapper.toResponseTodos(todos);
    }

    public ResponseTodo save(RequestTodo requestTodo) {
        Todo todo = todoMapper.toTodo(requestTodo);
        return todoMapper.toResponseTodo(todoRepository.save(todo));
    }

    public ResponseTodo update(int id, RequestTodo requestTodo) {
        if (id != requestTodo.getId()) {
            return null;
        }
        Todo oldTodo = todoRepository.findById(id).orElse(null);
        if (isNull(oldTodo)) {
            return null;
        }
        Todo newTodo = todoMapper.toTodo(requestTodo);
        return todoMapper.toResponseTodo(todoRepository.save(newTodo));
    }

    public void delete(int id) {
        Todo todo = todoRepository.findById(id).orElse(null);
        todoRepository.delete(todo);
    }
}
