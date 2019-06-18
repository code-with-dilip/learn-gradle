package com.learngradle.repository;

import com.learngradle.domain.ToDoItem;

import java.util.List;

public interface ToDoRepository {

    List<ToDoItem> findAll();
    ToDoItem findById(Long id);
    Long insert (ToDoItem toDoItem);
    void update(ToDoItem toDoItem);
    void deleteItem(ToDoItem toDoItem);
}
