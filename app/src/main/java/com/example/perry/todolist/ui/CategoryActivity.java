package com.example.perry.todolist.ui;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.example.perry.todolist.R;
import com.example.perry.todolist.models.Category;
import com.example.perry.todolist.models.Task;

import java.util.ArrayList;

public class CategoryActivity extends ListActivity {

    private Category mCategory;
    private ArrayList<String> mTasks;
    private Button mNewTaskButton;
    private EditText mNewTaskText;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        String name = getIntent().getStringExtra("categoryName");
        mCategory = Category.find(name);

        mNewTaskButton = (Button) findViewById(R.id.taskSubmitButton);
        mNewTaskText = (EditText) findViewById(R.id.newTaskText);
        mTasks = new ArrayList<>();

        for(Task task : mCategory.tasks()){
            mTasks.add(task.getDescription());
        }

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mTasks);
        setListAdapter(mAdapter);

        mNewTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask();
            }
        });
    }

    private void addTask() {
        String description = mNewTaskText.getText().toString();
        Task newTask = new Task(description, mCategory);
        newTask.save();
        mTasks.add(description);
        mAdapter.notifyDataSetChanged();
    }
}
