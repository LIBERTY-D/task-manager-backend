package com.daniel.app.TaskManager.task.mapper;

import com.daniel.app.TaskManager.task.dto.CreateTaskDto;
import com.daniel.app.TaskManager.task.dto.UpdateDto;
import com.daniel.app.TaskManager.task.model.TaskModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TaskModelMapperTest {


    private TaskMapper taskMapper;


    @BeforeEach
    public void setUp() {
        taskMapper = new TaskMapper();
    }

    @Test
    public void shouldMapCreateDtoToTask() {
        CreateTaskDto createTaskDto =  new
                CreateTaskDto(1,
                "test",
                "This is my first taskModel",
                new Date(),
                new String[]{"coding", "entertainment"},1);
        TaskModel taskModel = taskMapper.createDtoToTask(createTaskDto);
        assertEquals(createTaskDto.id(), taskModel.getId());
        assertEquals(createTaskDto.title(), taskModel.getTitle());
        assertEquals(createTaskDto.desc(), taskModel.getDesc());
        assertEquals(createTaskDto.dueDate(), taskModel.getDueDate());
        assertEquals(createTaskDto.tags(), taskModel.getTags());
    }


    @Test
    void should_throw_null_pointer_exception_when_create_dto_is_null(){
       var exp =  assertThrows(NullPointerException.class, () -> taskMapper.createDtoToTask(null));
       String expected = "CreateTaskDto cannot be null";
       assertEquals( expected,exp.getMessage());
    }

    @Test
    void shouldMapUpdateDtoToTask() {

        UpdateDto updateDto = new UpdateDto(1,
                "do yoga",
                "i want to finish yoga on this day",
                new Date(),
                new String[]{"love","yoga"});
        TaskModel taskModel = taskMapper.updateDtoToTask(updateDto);

        assertEquals(updateDto.id(), taskModel.getId());
        assertEquals(updateDto.title(), taskModel.getTitle());
        assertEquals(updateDto.desc(), taskModel.getDesc());
        assertEquals(updateDto.dueDate(), taskModel.getDueDate());
        assertEquals(updateDto.tags(), taskModel.getTags());

    }
    @Test
    void should_throw_null_pointer_exception_when_UPDATE_dto_is_null(){
        var exp = assertThrows(NullPointerException.class, () -> taskMapper.updateDtoToTask(null));
        String expected = "UpdateDto cannot be null";
        assertEquals(expected,exp.getMessage());
    }

}