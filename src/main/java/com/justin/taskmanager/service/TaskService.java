
package com.justin.taskmanager.service;

import com.justin.taskmanager.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final List<Task> taskList = new ArrayList<>();
    private int nextId = 1;

    public List<Task> getAllTasks() {
        return taskList;
    }

    public void addTask(Task task) {
        task.setId(nextId);
        nextId++;
        taskList.add(task);
    }

    public Task getTaskById(int id) {
        for (Task task : taskList) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    public void updateTask(Task updatedTask) {
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getId() == updatedTask.getId()) {
                taskList.set(i, updatedTask);
                break;
            }
        }
    }

    public void deleteTask(int id) {
        taskList.removeIf(task -> task.getId() == id);
    }
}
