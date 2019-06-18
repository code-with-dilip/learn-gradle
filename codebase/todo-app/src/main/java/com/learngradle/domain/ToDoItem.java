package com.learngradle.domain;

public class ToDoItem implements Comparable<ToDoItem> {
    private Long id;
    private String name;
    private  boolean completed;

    @Override
    public int compareTo(ToDoItem o) {
        return 0;
    }
}
