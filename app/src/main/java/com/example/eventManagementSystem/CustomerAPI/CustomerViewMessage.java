package com.example.eventManagementSystem.CustomerAPI;

public interface CustomerViewMessage {

    void onUpdateFailure(String message);
    void onUpdateSuccess(String message);

}
