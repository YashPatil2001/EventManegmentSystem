package com.example.eventManagementSystem.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventManagementSystem.Adapters.EventListAdapter;
import com.example.eventManagementSystem.EventAPI.EventModule;
import com.example.eventManagementSystem.EventAPI.EventPullData;
import com.example.eventManagementSystem.EventAPI.EventViewFetchData;
import com.example.eventManagementSystem.R;

import java.util.ArrayList;

public class EventDashborad extends AppCompatActivity implements EventViewFetchData {
    private RecyclerView ListDataView;
    private EventListAdapter mPostsAdapter;
    ArrayList<EventModule> eventArrayList = new ArrayList<>();
    private EventPullData eventPullData;
    public String stuTP, stuDegreeField, stuName, stuEmail, stuPhoneNumber;
    private static final String TAG = "EventList";


    @Override
    protected void onStart() {
        super.onStart();
        stuTP = getIntent().getStringExtra("Tp");
        stuDegreeField = getIntent().getStringExtra("Degree");
        stuName = getIntent().getStringExtra("Name");
        stuEmail= getIntent().getStringExtra("Email");
        stuPhoneNumber = getIntent().getStringExtra("PhoneNo");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.event_list_view);
        ListDataView = findViewById(R.id.EventListView);
        eventPullData = new EventPullData(this,this);
        RecyclerViewMethods();
        eventPullData.onSuccessUpdate(this);
    }

    private void RecyclerViewMethods() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        ListDataView.setLayoutManager(manager);
        ListDataView.setHasFixedSize(true);
        mPostsAdapter = new EventListAdapter(this, eventArrayList);
        ListDataView.setAdapter(mPostsAdapter);
        ListDataView.invalidate();

    }

    // we fetch the data from the student pull data then we set it into the student module then we add
    // each module into the jobpostlist
    @Override
    public void onUpdateSuccess(EventModule message) {
        if(message != null){
            if(message.getEventStatus().equals("Approved")){
                EventModule eventModules = new EventModule();
                eventModules.setStudentID(message.getStudentID());
                eventModules.setStudentEmail(message.getStudentEmail());
                eventModules.setEventID(message.getEventID());
                eventModules.setEventDescription(message.getEventDescription());
                eventModules.setEventCategory(message.getEventCategory());
                eventModules.setEventTime(message.getEventTime());
                eventModules.setEventDate(message.getEventDate());
                eventModules.setEventPriceTicket(message.getEventPriceTicket());
                eventModules.setEventImportPicture(message.getEventImportPicture());
                eventModules.setEventStatus(message.getEventStatus());
                eventArrayList.add(eventModules);
            }
        }
        mPostsAdapter.notifyDataSetChanged();


    }

    @Override
    public void onUpdateFailure(String message) {

        Toast.makeText(EventDashborad.this, message, Toast.LENGTH_LONG).show();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(EventDashborad.this, CustomerPanel.class);
        intent.putExtra("Tp", stuTP);
        intent.putExtra("Degree", stuDegreeField);
        intent.putExtra("Name",stuName);
        intent.putExtra("Email", stuEmail);
        intent.putExtra("PhoneNo", stuPhoneNumber);
        startActivity(intent);
        finish();
    }
}