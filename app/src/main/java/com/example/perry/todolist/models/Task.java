package com.example.perry.todolist.models;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;
@Table(name = "tasks", id = "_id")
public class Task extends Model{
    @Column(name = "description")
    private String mDescription;

    @Column(name = "Category")
    private Category mCategory;

    public Task(){
        super();
    }

    public Task(String description, Category category){
        super();
        mDescription = description;
        mCategory = category;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public static List<Task> all(){
        return new Select().from(Task.class).execute();
    }
}