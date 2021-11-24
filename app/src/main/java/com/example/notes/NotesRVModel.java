package com.example.notes;

import android.widget.Button;

public class NotesRVModel {

    public NotesRVModel(String c){
        content = c;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content;
    private Button delete;


}
