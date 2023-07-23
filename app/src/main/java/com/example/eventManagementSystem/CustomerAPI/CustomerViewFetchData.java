package com.example.eventManagementSystem.CustomerAPI;

public interface CustomerViewFetchData {

    void onUpdateSuccess(CustomerModule message);
    void onUpdateFailure(String message);

}
