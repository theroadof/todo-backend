package teddy.lin.todobackend.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import teddy.lin.todobackend.dto.RequestTodo;
import teddy.lin.todobackend.dto.ResponseTodo;
import teddy.lin.todobackend.model.Todo;

import java.util.ArrayList;
import java.util.List;

@Component
public class TodoMapper {

    public ResponseTodo toResponseTodo(Todo todo) {
        ResponseTodo responseTodo = new ResponseTodo();
        BeanUtils.copyProperties(todo, responseTodo);
        return responseTodo;
    }

    public List<ResponseTodo> toResponseTodos(List<Todo> todos) {
        List<ResponseTodo> responseTodos = new ArrayList<>();
        todos.forEach(todo -> responseTodos.add(toResponseTodo(todo)));
        return responseTodos;
    }

    public Todo toTodo(RequestTodo requestTodo) {
        Todo todo = new Todo();
        BeanUtils.copyProperties(requestTodo, todo);
        return todo;
    }
}
