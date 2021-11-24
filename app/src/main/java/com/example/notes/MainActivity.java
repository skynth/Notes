package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView notesRecyclerView;
    private NotesRVAdapter notesRVAdapter;
    ArrayList<NotesRVModel> notesRVModels = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notesRecyclerView = findViewById(R.id.notes_view);
        loadData();
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        notesRVModels.add(new NotesRVModel("test input"));
        notesRVModels.add(new NotesRVModel("test input 2"));
        notesRVModels.add(new NotesRVModel("test input 3"));
        notesRVModels.add(new NotesRVModel("test input 4"));
        notesRVModels.add(new NotesRVModel("test input 5"));
        notesRVModels.add(new NotesRVModel("test input 6"));
        notesRVAdapter = new NotesRVAdapter(notesRVModels);
        notesRecyclerView.setAdapter(notesRVAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("notes", null);
        Type type = new TypeToken<ArrayList<NotesRVModel>>() {}.getType();
        notesRVModels = gson.fromJson(json, type);
        if (notesRVModels == null) {
            notesRVModels = new ArrayList<>();
        }
    }


    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(notesRVModels);
        editor.putString("notes", json);
        editor.apply();
    }
}