package com.daniel.app.TaskManager.task.service;

import com.daniel.app.TaskManager.task.model.TaskModel;
import com.daniel.app.TaskManager.task.repository.TaskRepository;
import com.daniel.app.TaskManager.user.model.UserModel;
import com.daniel.app.TaskManager.user.repository.UserRepository;
import com.daniel.app.TaskManager.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service()
public class TaskService {
       private final TaskRepository  taskRepository;

       private  final UserService userService;
       private  final UserRepository userRepository;
       public  TaskService(TaskRepository taskRepository, UserService userService, UserRepository userRepository) {
           this.taskRepository = taskRepository;


           this.userService = userService;
           this.userRepository = userRepository;
       }
        public TaskModel createTask(Integer userId, TaskModel taskModel) {
           var user = userService.getUser(userId);
           if (user == null) {
               throw new NoSuchElementException("No such user with id + " + userId);
          }
           taskModel.setUser(user);
           return this.taskRepository.save(taskModel);
        }

        public ArrayList<TaskModel> getAllTasks() {

            return (ArrayList<TaskModel>) this.taskRepository.findAll();
        }

        public TaskModel updateTask(TaskModel taskModel) {

           var find =  this.taskRepository.findById(taskModel.getId());

           if(find.isEmpty()){
               throw  new NoSuchElementException("no task with with id " + taskModel.getId());
           }
            var existingTask = find.get();
           existingTask.setDesc(taskModel.getDesc());
           existingTask.setDueDate(taskModel.getDueDate());
           existingTask.setTags(taskModel.getTags());
           existingTask.setTitle(taskModel.getTitle());
           existingTask.setIsCompleted(taskModel.isCompleted());
           existingTask.setCompletedOn(taskModel.getCompletedOn());
            return this.taskRepository.save(existingTask);
        }

        public Optional<TaskModel> findTaskById(Integer id){

            return taskRepository.findById(id);
        }

    @Transactional
    public void deleteTask(Integer id) {
        try {
            Optional<TaskModel> returnedTask = this.taskRepository.findById(id);
            if (returnedTask.isEmpty()) {
                throw new NoSuchElementException("No task with id " + id);
            }
            TaskModel task = returnedTask.get();
            UserModel user = task.getUser();

            if (user != null) {
                user.getTasks().remove(task);
            }

            this.taskRepository.delete(task);
            if (user != null) {
                this.userRepository.save(user);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
        }
    }
}
