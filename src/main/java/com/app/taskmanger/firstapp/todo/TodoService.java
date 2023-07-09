package com.app.taskmanger.firstapp.todo;

import com.app.taskmanger.firstapp.todo.Todo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();
    private static int todoscount =0;
    static {
        todos.add(new Todo(++todoscount,"shiva","Learn AWS", LocalDate.now().plusYears(1),false));
        todos.add(new Todo(++todoscount,"shiva","Learn Java", LocalDate.now().plusYears(2),false));
        todos.add(new Todo(++todoscount,"kumar","Learn Docker", LocalDate.now().plusYears(1),false));
        todos.add(new Todo(++todoscount,"kumar","Learn Devops", LocalDate.now().plusYears(2),false));

    }

    public List<Todo> findByUsername(String username){
        Predicate<Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username) ;
        return todos.stream().filter(predicate).toList();
    }

    public void addTodo(String username, String description, LocalDate targetDate, boolean done){
        todos.add(new Todo(++todoscount,username,description, targetDate,done));
    }

    public void deleteById(int id){
        Predicate<Todo> predicate = todo -> todo.getId() == id ;
        todos.removeIf(predicate);
    }

    public Todo findById(int id){
        Predicate<Todo> predicate = todo -> todo.getId() == id ;
        Todo todo = todos.stream().filter(predicate).findFirst().get();
        return todo;
    }

    public void update(Todo todo){
        Todo temp = findById(todo.getId());
        temp.setDescription(todo.getDescription());
        if(todo.getTargetDate() != null)temp.setTargetDate(todo.getTargetDate());
        temp.setDone(temp.isDone());
        return;
    }

}
