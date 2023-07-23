package com.example.eventManagementSystem.EventAPI;

public interface EventViewFetchData {

    void onUpdateSuccess(EventModule message);
    void onUpdateFailure(String message);

}
