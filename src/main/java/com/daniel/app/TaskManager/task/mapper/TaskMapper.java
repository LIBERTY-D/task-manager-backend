package com.daniel.app.TaskManager.task.mapper;

import com.daniel.app.TaskManager.task.dto.ResponseDto;
import com.daniel.app.TaskManager.task.dto.CreateTaskDto;
import com.daniel.app.TaskManager.task.dto.UpdateDto;
import com.daniel.app.TaskManager.task.model.TaskModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component()
public class TaskMapper {

    public TaskModel createDtoToTask(CreateTaskDto createTaskDto) {

        if(createTaskDto ==null){
            throw new NullPointerException("CreateTaskDto cannot be null");
        }
        TaskModel taskModel =  new TaskModel();
        taskModel.setId(createTaskDto.id());
        taskModel.setTitle(createTaskDto.title());
        taskModel.setDesc(createTaskDto.desc());
        taskModel.setDueDate(createTaskDto.dueDate());
        taskModel.setTags(createTaskDto.tags());
        return taskModel;

    }
    public TaskModel updateDtoToTask(UpdateDto updateDto) {
        if(updateDto ==null){
            throw new NullPointerException("UpdateDto cannot be null");
        }
        TaskModel taskModel =  new TaskModel();
        taskModel.setId(updateDto.id());
        taskModel.setTitle(updateDto.title());
        taskModel.setDesc(updateDto.desc());
        taskModel.setDueDate(updateDto.dueDate());
        taskModel.setTags(updateDto.tags());
        taskModel.setIsCompleted(updateDto.isCompleted());
        taskModel.setCompletedOn(updateDto.completedOn());
        return taskModel;

    }
    public ResponseEntity<ResponseDto> createTaskToResponseDto(TaskModel taskModel) {

        return new ResponseEntity<>(new ResponseDto(HttpStatus.OK,"created",new TaskModel[]{taskModel}),HttpStatus.CREATED);
    }
    public ResponseEntity<ResponseDto> deleteTaskToResponseDto(TaskModel taskModel) {

        return new ResponseEntity<>(new ResponseDto(HttpStatus.OK,"deleted",new TaskModel[]{taskModel}),HttpStatus.OK);
    }

    public ResponseEntity<ResponseDto> updateTaskToResponseDto(TaskModel taskModel) {

        return new ResponseEntity<>(new ResponseDto(HttpStatus.OK,"updated",new TaskModel[]{taskModel}),HttpStatus.OK);
    }
    public ResponseEntity<ResponseDto> getAllTaskToResponseDto(ArrayList<TaskModel> taskModels) {

        TaskModel[] tasksArray = taskModels.toArray(new TaskModel[taskModels.size()]);

        return new ResponseEntity<>(new ResponseDto(HttpStatus.OK,"results",tasksArray ),HttpStatus.OK);
    }
    public ResponseEntity<ResponseDto> getByIdTaskToResponseDto(TaskModel taskModel) {


        return new ResponseEntity<>(new ResponseDto(HttpStatus.OK,"results",new TaskModel[]{taskModel}),HttpStatus.OK);
    }

}
