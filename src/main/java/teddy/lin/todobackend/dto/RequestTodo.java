package teddy.lin.todobackend.dto;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class RequestTodo {
    private int id;

    private String text;

    private boolean status;

    public RequestTodo(int id, String text, boolean status) {
        this.id = id;
        this.text = text;
        this.status = status;
    }

    public RequestTodo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
