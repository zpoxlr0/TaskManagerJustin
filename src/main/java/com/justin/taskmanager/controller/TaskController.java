
package com.justin.taskmanager.controller;

import com.justin.taskmanager.model.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskController {

    private final List<Task> taskList = new ArrayList<>();

    public TaskController() {
        taskList.add(new Task(1, "Finish Java homework",
                "Complete exercises 1 to 5", LocalDate.of(2026, 4, 10), false));
        taskList.add(new Task(2, "Study Spring Boot basics",
                "Review controllers and templates", LocalDate.of(2026, 4, 12), true));
    }

    @GetMapping("/")
    public String showTaskList(Model model) {
        model.addAttribute("tasks", taskList);
        return "index";
    }

    @GetMapping("/addTask")
    public String showAddTaskPage(Model model) {
        model.addAttribute("task", new Task());
        return "add-task";
    }

    @PostMapping("/addTask")
    public String addTask(@ModelAttribute Task task) {
        taskList.add(task);
        return "redirect:/";
    }

    @GetMapping("/editTask/{id}")
    public String showEditTaskPage(@PathVariable int id, Model model) {
        for (Task task : taskList) {
            if (task.getId() == id) {
                model.addAttribute("task", task);
                return "edit-task";
            }
        }

        return "redirect:/";
    }

    @PostMapping("/updateTask")
    public String updateTask(@ModelAttribute Task updatedTask) {
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getId() == updatedTask.getId()) {
                taskList.set(i, updatedTask);
                break;
            }
        }

        return "redirect:/";
    }

    @GetMapping("/deleteTask/{id}")
    public String deleteTask(@PathVariable int id) {
        Task taskToRemove = null;

        for (Task task : taskList) {
            if (task.getId() == id) {
                taskToRemove = task;
                break;
            }
        }

        if (taskToRemove != null) {
            taskList.remove(taskToRemove);
        }

        return "redirect:/";
    }
}
