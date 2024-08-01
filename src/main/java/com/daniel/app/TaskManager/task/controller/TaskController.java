package com.daniel.app.TaskManager.task.controller;
import com.daniel.app.TaskManager.task.dto.ResponseDto;
import com.daniel.app.TaskManager.task.dto.CreateTaskDto;
import com.daniel.app.TaskManager.task.dto.UpdateDto;
import com.daniel.app.TaskManager.task.exception.TaskError;
import com.daniel.app.TaskManager.task.mapper.TaskMapper;
import com.daniel.app.TaskManager.task.model.TaskModel;
import com.daniel.app.TaskManager.task.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.NoSuchElementException;

@CrossOrigin()
@RestController
@RequestMapping("/api/v1/tasks")  //This means URL requests will be directed to this class and method.
public class TaskController {


    private  final TaskService taskService;
    private  final TaskMapper taskMapper;
    public TaskController(TaskService taskService, TaskMapper taskMapper){
        this.taskService = taskService;
        this.taskMapper = taskMapper;

    }
    //create
     @PostMapping
     public ResponseEntity<ResponseDto> createTask(@RequestBody() @Valid() CreateTaskDto createTaskDto) {


         TaskModel taskModel =  taskMapper.createDtoToTask(createTaskDto);
         var taskCreated =
                 this.taskService.createTask(createTaskDto.userId(),taskModel);
         return taskMapper.createTaskToResponseDto(taskCreated);
     }


    //read
     @GetMapping
     public ResponseEntity<ResponseDto> getAllTasks() {
         var tasks =  this.taskService.getAllTasks();

         return taskMapper.getAllTaskToResponseDto(tasks);

     }

    //update
     @PatchMapping()
     public ResponseEntity<ResponseDto> updateTask(
                                                   @RequestBody() @Valid()UpdateDto updateDto) {
        TaskModel taskModel =taskMapper.updateDtoToTask(updateDto);
        var updatedData = this.taskService.updateTask(taskModel);

        return taskMapper.updateTaskToResponseDto(updatedData);

     }


     //find task by id
    @GetMapping("/{id}")
    public  ResponseEntity<ResponseDto> findTaskById(@PathVariable() Integer id){
        var task= this.taskService.findTaskById(id);
        if(task.isEmpty()){
            throw  new NoSuchElementException("no such task with id "+id);
        }
        return taskMapper.getByIdTaskToResponseDto(task.get());
    }

    //delete
     @DeleteMapping("/{id}")
     public ResponseEntity<ResponseDto> deleteTask(@PathVariable Integer id) {
         this.taskService.deleteTask(id);
         return  taskMapper.deleteTaskToResponseDto(new TaskModel());
     }


     @ExceptionHandler({MethodArgumentNotValidException.class})
    public  ResponseEntity<TaskError>  methodArgumentNotValidException(MethodArgumentNotValidException e){
         HashMap<String,String>errors = new HashMap<>();
         e.getBindingResult().getAllErrors().forEach(error->{
             String fieldError =((FieldError)error).getField();
             String message =  error.getDefaultMessage();
             errors.put(fieldError,message);
         });
     return new ResponseEntity<>(new TaskError(HttpStatus.BAD_REQUEST,"fill in required fields",errors),HttpStatus.BAD_REQUEST);
     }
     @ExceptionHandler(NoSuchElementException.class)
     public ResponseEntity<TaskError>  notFoundException(NoSuchElementException e){

         return new ResponseEntity<>(new TaskError(HttpStatus.NOT_FOUND,e.getMessage(),null),HttpStatus.NOT_FOUND);
     }
}
