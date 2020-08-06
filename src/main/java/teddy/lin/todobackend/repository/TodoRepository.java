package teddy.lin.todobackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teddy.lin.todobackend.model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
}
