package com.example.eventManagementSystem.EventAPI;

public interface EventViewMessage {

    void onUpdateFailure(String message);
    void onUpdateSuccess(String message);

}
