package com.example.ats.dto;

import com.example.ats.entity.Stage;

public class MoveStageRequest {
    private Stage stage;
    private String note;
    public Stage getStage() { return stage; }
    public void setStage(Stage stage) { this.stage = stage; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}
