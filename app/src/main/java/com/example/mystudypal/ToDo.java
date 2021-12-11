package com.example.mystudypal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.example.mystudypal.Adapter.ToDoAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.mystudypal.Model.ToDoModel;
import com.example.mystudypal.Utils.DataBaseHandler;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ToDo extends AppCompatActivity implements DialogCloseListener{
    private DataBaseHandler db;
    private RecyclerView tasksRecyclerView;
    private ToDoAdapter tasksAdapter;
    private FloatingActionButton fab;
    private List<ToDoModel> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do);
        Objects.requireNonNull(getSupportActionBar()).hide();

        db = new DataBaseHandler(this);
        db.openDatabase();

        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasksAdapter = new ToDoAdapter(db,ToDo.this);
        tasksRecyclerView.setAdapter(tasksAdapter);

        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new RecyclerItemTouchHelper(tasksAdapter));
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView);

        fab = findViewById(R.id.fab);

        taskList = db.getAllTasks();
        Collections.reverse(taskList);

        tasksAdapter.setTasks(taskList);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTask.newInstance().show(getSupportFragmentManager(), AddNewTask.TAG);
            }
        });
    }

    @Override
    public void handleDialogClose(DialogInterface dialog){
        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        tasksAdapter.setTasks(taskList);
        tasksAdapter.notifyDataSetChanged();
    }
}





































//package com.example.mystudypal;
//
//import android.os.Bundle;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.mystudypal.databinding.ActivityTodoBinding;
//
//import android.os.Handler;
//import android.util.Log;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.ListAdapter;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import android.os.SystemClock;
//import android.widget.Button;
//import android.widget.LinearLayout;
//import android.widget.Toast;
//
//
//public class ToDo extends AppCompatActivity {
//    private ActivityTodoBinding binding;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivityTodoBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//        Log.i("enter something", "Add a New Task");
//
//
//        binding.buttonDone.setOnClickListener(v -> finish());
////        binding.buttonPlus.setOnClickListener(v -> {
////            Log.i("enter something", "Today's New Task is: " + binding.textView.getText());
////            Log.i("enter something", "Weekly New Task is: " + binding.textView.getText());
////        });
//    }
//
//    private void printLog(String message) {
//        Log.i("enter something", message);
//    }
//}
//
//
//
//
//


//        LinearLayout layout = new LinearLayout(this);
//        layout.setOrientation(LinearLayout.VERTICAL);
//
//        mChronometer = new Chronometer(this);
//        layout.addView(mChronometer);
//        setContentView(layout);
//    }
//
//    private void showElapsedTime() {
//        long elapsedMillis = SystemClock.elapsedRealtime()
//                - mChronometer.getBase();
//        Toast.makeText(Report.this,
//                "Elapsed milliseconds: " + elapsedMillis, Toast.LENGTH_SHORT).show();
//    }
//
//
//    View.OnClickListener mStartListener = new OnClickListener() {
//        public void onClick(View v) {
//            int stoppedMilliseconds = 0;
//            String chronoText = mChronometer.getText().toString();
//            String array[] = chronoText.split(":");
//            if (array.length == 2) {
//                stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 1000
//                        + Integer.parseInt(array[1]) * 1000;
//            } else if (array.length == 3) {
//                stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 60 * 1000
//                        + Integer.parseInt(array[1]) * 60 * 1000
//                        + Integer.parseInt(array[2]) * 1000;
//            }
//            mChronometer.setBase(SystemClock.elapsedRealtime() - stoppedMilliseconds);
//            mChronometer.start();
//        }
//    };
//
//    View.OnClickListener mStopListener = new OnClickListener() {
//        public void onClick(View v) {
//            mChronometer.stop();
//            showElapsedTime();
//        }
//    };
//
//    View.OnClickListener mResetListener = new OnClickListener() {
//        public void onClick(View v) {
//            mChronometer.setBase(SystemClock.elapsedRealtime());
//            mChronometer.stop();
//            showElapsedTime();
//        }
//    };
//    public class Main extends Report {
//        /** Called when the activity is first created. */
//        @Override
//        public void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_report);
//        }
//
//        public void startChronometer(View view) {
//            ((Chronometer) findViewById(R.id.chronometer1)).start();
//        }
//
//        public void stopChronometer(View view) {
//            ((Chronometer) findViewById(R.id.chronometer1)).stop();
//        }
//    }








