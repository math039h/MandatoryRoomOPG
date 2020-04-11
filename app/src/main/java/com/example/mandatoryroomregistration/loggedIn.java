package com.example.mandatoryroomregistration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class loggedIn extends AppCompatActivity {
    private TextView messageView;
    private static final String LOG_TAG = "Rooms";
    private FirebaseUser mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        //getAndShowAllFreeRooms();
        messageView = findViewById(R.id.LoggedInMessageTextView);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        getAllRooms();
        //FirebaseUser user = mAuth.getInstance().getCurrentUser();

/*
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            //        .setAction("Action", null).show();
            Intent intent = new Intent(loggedIn.this, AddReservationActivity.class);
            startActivity(intent);
        });
*/
        SwipeRefreshLayout refreshLayout = findViewById(R.id.LoggedInSwiperefresh);
        refreshLayout.setOnRefreshListener(() -> {
            getAllRooms();
            refreshLayout.setRefreshing(false);
        });
    }

    public void loggedInReservateRoomsFloatingAButton(View view) {
        Log.d(LOG_TAG, "LoggedIn Intent Test 1");
        Intent intent = new Intent(this, AddReservationActivity.class);
        Log.d(LOG_TAG, "LoggedIn Intent");
        startActivity(intent);
    }

    private void getAllRooms() {
        RoomRegistrationService roomRegistrationService = ApiUtils.getRoomRegistrationService();
        Call<List<Rooms>> getAllRoomsCall = roomRegistrationService.getAllRooms();
        TextView messageView = findViewById(R.id.LoggedInMessageTextView);

        messageView.setText("");
        getAllRoomsCall.enqueue(new Callback<List<Rooms>>() {
            @Override
            public void onResponse(Call<List<Rooms>> call, Response<List<Rooms>> response) {
                if (response.isSuccessful()) {
                    List<Rooms> allRooms = response.body();
                    Log.d(LOG_TAG, allRooms.toString());
                    populateRecyclerView(allRooms);
                } else {
                    String message = "Problem " + response.code() + " " + response.message();
                    Log.d(LOG_TAG, message);
                    messageView.setText(message);
                }
            }

            @Override
            public void onFailure(Call<List<Rooms>> call, Throwable t) {
                Log.e(LOG_TAG, t.getMessage());
                messageView.setText(t.getMessage());
            }
        });
    }

    /*@Override
    public void onStart() {
        super.onStart();
        //getAndShowAllFreeRooms();
    }*/

    /*private void getAndShowAllFreeRooms() {
        RoomRegistrationService roomRegistrationService = ApiUtils.getRoomRegistrationService();
        Call<List<Rooms>> getAllRoomsCall = roomRegistrationService.getAllFreeRooms(573468);
        messageView.setText("");
        getAllRoomsCall.enqueue(new Callback<List<Rooms>>() {
            @Override
            public void onResponse(Call<List<Rooms>> call, Response<List<Rooms>> response) {
                if (response.isSuccessful()) {
                    List<Rooms> allRooms = response.body();
                    Log.d(LOG_TAG, allRooms.toString());
                    populateRecyclerView(allRooms);
                } else {
                    String message = "Problem " + response.code() + " " + response.message();
                    Log.d(LOG_TAG, message);
                    messageView.setText(message);
                }
            }

            @Override
            public void onFailure(Call<List<Rooms>> call, Throwable t) {
                Log.e(LOG_TAG, t.getMessage());
                messageView.setText(t.getMessage());
            }
        });
    }*/

    private void populateRecyclerView(List<Rooms> allRooms) {
        RecyclerView recyclerView = findViewById(R.id.LoggedInRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewSimpleAdapter adapter = new RecyclerViewSimpleAdapter<>(allRooms);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((view, position, item) -> {
            Rooms rooms = (Rooms) item;
            Log.d(LOG_TAG, item.toString());
            Intent intent = new Intent(loggedIn.this, SingleRoomActivity.class);
            intent.putExtra(SingleRoomActivity.ROOM, rooms);
            Log.d(LOG_TAG, "putExtra " + rooms.toString());
            startActivity(intent);
        });
    }

    public void BackToMain(View view){
        finish();
    }
}
