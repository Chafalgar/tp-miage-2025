package com.acme.todolist.adapters.rest_api;

import java.util.List;

import javax.inject.Inject;

import com.acme.todolist.application.port.in.AddTodoItem;
import org.springframework.web.bind.annotation.*;

import com.acme.todolist.application.port.in.GetTodoItems;
import com.acme.todolist.domain.TodoItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Le controlleur Spring MVC qui expose les endpoints REST
 * 
 * @author bflorat
 *
 */
@RestController
@RequestMapping("/todos")
public class TodoListController {


	private final GetTodoItems getTodoItemsQuery;
	private final AddTodoItem addTodoItemsQuery;

	@Inject
	public TodoListController(GetTodoItems getTodoItemsQuery, AddTodoItem addTodoItemsQuery) {
		this.getTodoItemsQuery = getTodoItemsQuery;
		this.addTodoItemsQuery = addTodoItemsQuery;
	}
	
	@GetMapping("/todos")
	public List<TodoItem> getAllTodoItems() {
		return this.getTodoItemsQuery.getAllTodoItems();
	}
	
	
	// Endpoint de type POST vers "/todos"

	@PostMapping("/todos")
	public ResponseEntity<TodoItem> ajouterItem(@RequestBody TodoItem item) {
		addTodoItemsQuery.addTodoItem(item);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	
	
}
