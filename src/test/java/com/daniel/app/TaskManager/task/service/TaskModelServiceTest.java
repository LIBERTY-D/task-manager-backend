package com.daniel.app.TaskManager.task.service;

import com.daniel.app.TaskManager.task.model.TaskModel;
import com.daniel.app.TaskManager.task.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


class TaskModelServiceTest {

    //service we want to test
    @InjectMocks
    private TaskService taskService;

    //dependencies

    @Mock
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    public  void should_create_task(){
        //given
        TaskModel create =  new
                TaskModel("title",
                "test",
                new Date(),
                new String[]{"coding", "entertainment"},false,null);



        TaskModel taskModel =  new
                TaskModel("title",
                "test",
                new Date(),
                new String[]{"coding", "entertainment"},false,null);


        //mock calls
        Mockito.when(taskRepository.save(create)).thenReturn(taskModel);


        //when
        TaskModel savedTaskModel = taskService.createTask(1, create);


        //then
        assertEquals(create.getId(), savedTaskModel.getId());
        assertEquals(create.getTitle(), savedTaskModel.getTitle());
        assertEquals(create.getDesc(), savedTaskModel.getDesc());
        assertEquals(create.getTags().length, savedTaskModel.getTags().length);


        Mockito.verify(taskRepository,Mockito.times(1)).save(create);


    }

    @Test
     void should_getAll_Tasks(){


        //given
         ArrayList<TaskModel> taskModels =  new ArrayList<TaskModel>();
         taskModels.add(new TaskModel("task 1","my first desc",new Date(),
                 new String[]{"coding", "ai"},false,null));
         taskModels.add(new TaskModel("task 2","my sec desc",new Date(),
                 new String[]{"coding", "ai"},false,null));
         taskModels.add(new TaskModel("task 3","my third desc",new Date(),
                 new String[]{"coding", "ai"},false,null));

         //when
         Mockito.when(taskRepository.findAll()).thenReturn(taskModels);

         //then
        ArrayList<TaskModel> response =  taskService.getAllTasks();
        assertEquals(taskModels.size(),response.size());
        Mockito.verify(taskRepository,Mockito.times(1)).findAll();
     }

     @Test
     void should_update_task(){
        //given
         TaskModel taskModel =  new
                 TaskModel(
                 "test",
                 "This is my first taskModel",
                 new Date(),
                 new String[]{"coding", "entertainment",},false,null);

         // when
         Mockito.when(taskRepository.findById(taskModel.getId())).thenReturn(Optional.of(taskModel));
         Mockito.when(taskRepository.save(taskModel)).thenReturn(taskModel);

         //then
         TaskModel updatedTaskModel =  new
                 TaskModel(
                 "updated test",
                 "This is my updated first taskModel",
                 new Date(),
                 new String[]{"coding", "entertainment",},false,null);



         assertEquals(taskModel.getId(), updatedTaskModel.getId());


         TaskModel response =  taskService.updateTask(updatedTaskModel);


         Mockito.verify(taskRepository,Mockito.times(1)).findById(taskModel.getId());
         Mockito.verify(taskRepository,Mockito.times(1)).save(updatedTaskModel);



     }

     @Test
    void should_find_task_by_id(){
        //given
         Integer id = 1;

         TaskModel taskModel =  new
                 TaskModel(
                 "test",
                 "This is my first taskModel",
                 new Date(),
                 new String[]{"coding", "entertainment",},false,null);

         //when
         Mockito.when(taskRepository.findById(id)).thenReturn(Optional.of(taskModel));

         //then

         assertEquals(id, taskModel.getId());
         taskService.findTaskById(id);

         Mockito.verify(taskRepository,Mockito.times(1)).findById(id);


     }

     @Test
    public  void should_delete_a_task(){

         //given
         Integer id = 1;

         TaskModel taskModel =  new
                 TaskModel(
                 "test",
                 "This is my first taskModel",
                 new Date(),
                 new String[]{"coding", "entertainment",},false,null);

         //when
         Mockito.when(taskRepository.findById(id)).thenReturn(Optional.of(taskModel));

         //then

         assertEquals(id, taskModel.getId());
         taskService.deleteTask(id);

         Mockito.verify(taskRepository,Mockito.times(1)).deleteById(id);

     }
}