
package  com.daniel.app.TaskManager.task.exception;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

public class TaskError {
    private HttpStatus status;
    private String message;
    private HashMap<String,String> details;

    public TaskError(HttpStatus status, String message, HashMap<String,String> details) {
        this.status = status;
        this.message = message;
        this.details = details;
    }

    // Getters and setters
    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HashMap<String,String> getDetails() {
        return details;
    }

    public void setDetails(HashMap<String,String> details) {
        this.details = details;
    }
}
