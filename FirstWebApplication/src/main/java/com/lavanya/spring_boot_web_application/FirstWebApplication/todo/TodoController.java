package com.lavanya.spring_boot_web_application.FirstWebApplication.todo;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("name")
public class TodoController {
    private final TodoService todoService;
    private TodoService todoservice;

    public TodoController(TodoService todoservice, TodoService todoService) {
        this.todoservice = todoservice;
        this.todoService = todoService;
    }
    

    // "/list-todos"
    @RequestMapping("/list-todos")
    public String getListOfTodos(ModelMap model){
        String username = getLoggedinUserName(model);
        List<Todo> todos = todoservice.findbyUsername(username);
        model.addAttribute("todos",todos);
        return "listTodo";

    }



    //GET, POST
    @RequestMapping(value = "/add-todo",method = RequestMethod.GET)
    public String showNewTodoPage(ModelMap model){
        String username = getLoggedinUserName(model);
        Todo todo = new Todo(0,username,"",
                LocalDate.now().plusYears(1),false);
        model.put("todo",todo);
        return "todo";
    }

    @RequestMapping(value="add-todo", method = RequestMethod.POST)
    //Mapping to bean "Todo.java" -->Two way binding fprm to bean and bean to form
    public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        if(result.hasErrors()){
            return "todo";
        }
        String username = getLoggedinUserName(model);
        todoservice.addTodo(username, todo.getDescription(),
                todo.getTargetDate(), false);
        return "redirect:list-todos";
    }

    @RequestMapping("/delete-todo")
    public String deleteTodd(@RequestParam int id){
        todoService.deleteById(id);
        return "redirect:list-todos";
    }

    @RequestMapping(value = "/update-todo",method = RequestMethod.GET)
    public String showUpdateTodo(@RequestParam int id, ModelMap model){
        Todo todo= todoService.findById(id);
        model.addAttribute("todo",todo);
        return "todo";
    }
    @RequestMapping(value = "/update-todo",method = RequestMethod.POST)
    public String updateTodo(@Valid Todo todo, BindingResult result, ModelMap model){
        if(result.hasErrors()){
            return "todo";
        }
        String username = getLoggedinUserName(model);
        todo.setUserName(username);
        todoService.updateTodo(todo);

        return "redirect:list-todos";
    }


    private String getLoggedinUserName(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }


}
