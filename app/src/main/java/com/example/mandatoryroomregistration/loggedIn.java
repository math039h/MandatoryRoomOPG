package com.example.mandatoryroomregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class loggedIn extends AppCompatActivity {
    private TextView messageView;
    private static final String LOG_TAG = "Rooms";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        messageView = findViewById(R.id.LoggedInMessageTextView);
    }

    @Override
    public void onStart() {
        super.onStart();
        getAndShowAllFreeRooms();
    }

    private void getAndShowAllFreeRooms() {
        RoomRegistrationService roomRegistrationService = ApiUtils.getRoomRegistrationService();
        Call<List<Rooms>> getAllRoomsCall = roomRegistrationService.getAllFreeRooms(573468);
        messageView.setText("");
        getAllRoomsCall.enqueue(new Callback<List<Rooms>>() {
            @Override
            public void onResponse(Call<List<Rooms>> call, Response<List<Rooms>> response) {
                if (response.isSuccessful()) {
                    List<Rooms> allRooms = response.body();
                    Log.d(LOG_TAG, allRooms.toString());
                    //populateRecyclerView(allRooms);
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
/*
    private void populateRecyclerView(List<Rooms> allRooms) {
        RecyclerView recyclerView = findViewById(R.id.mainRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewSimpleAdapter adapter = new RecyclerViewSimpleAdapter<>(allBooks);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((view, position, item) -> {
            Book book = (Book) item;
            Log.d(LOG_TAG, item.toString());
            Intent intent = new Intent(MainActivity.this, SingleBookActivity.class);
            intent.putExtra(SingleBookActivity.BOOK, book);
            Log.d(LOG_TAG, "putExtra " + book.toString());
            startActivity(intent);
        });
    }*/

    public void BackToMain(View view){
        finish();
    }
}
