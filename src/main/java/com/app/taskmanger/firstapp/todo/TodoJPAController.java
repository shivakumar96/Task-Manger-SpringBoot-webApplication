package com.app.taskmanger.firstapp.todo;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("name")
public class TodoJPAController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private TodoRepository repository ;
    private TodoService todoService;

    public TodoJPAController(TodoService todoService, TodoRepository repository) {
        this.repository = repository;
        this.todoService = todoService;
    }

    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap model){
        String username =getLoggedInUserName();
        List<Todo> todos = repository.findByUsername(username);
        //List<Todo> todos = todoService.findByUsername(getLoggedInUserName());
        model.addAttribute("todos",todos);

        return "listTodos" ;
    }

    @RequestMapping(value="add-todo",method= RequestMethod.GET)
    public String showNewTodoPage(ModelMap model){
        logger.debug("get add-todo");
        String username =getLoggedInUserName();
        Todo todo = new Todo(0,username,"default description",LocalDate.now().plusYears(1),false);
        model.put("todo",todo);
        return "todo" ;
    }

    @RequestMapping(value="add-todo",method= RequestMethod.POST)
    public String addTodoPage(ModelMap model, @Valid Todo todo, BindingResult result){
        logger.debug("post add-todo");
        if(result.hasErrors()){
            return "todo" ;
        }
        String username =getLoggedInUserName();
        todo.setUsername(username);
        //todoService.addTodo((String) model.get("name"),todo.getDescription(), todo.getTargetDate(),false);
        repository.save(todo);
        return "redirect:list-todos" ;
    }

    @RequestMapping(value="delete-todo")
    public String deleteTodo(@RequestParam int id, ModelMap model){
        logger.debug("delete-todo");
        //todoService.deleteById(id);
        repository.deleteById(id);
        return "redirect:list-todos" ;
    }

    @RequestMapping(value="update-todo",method= RequestMethod.GET)
    public String showUpdatePage(@RequestParam int id, ModelMap model){
        logger.debug("update-todo");
        //Todo todo = todoService.findById(id);
        Todo todo = repository.findById(id).get();
        model.addAttribute("todo",todo);
        return "todo" ;
    }

    @RequestMapping(value="update-todo",method= RequestMethod.POST)
    public String updateTodoPage(ModelMap model, @Valid Todo todo, BindingResult result){
        logger.debug("post add-todo");
        if(result.hasErrors()){
            return "todo" ;
        }
        //todoService.update(todo);
        String username =getLoggedInUserName();
        todo.setUsername(username);
        repository.save(todo);
        return "redirect:list-todos" ;
    }

    private String getLoggedInUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

}
