package com.example.mandatoryroomregistration;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private static final String TAG = "Authentication";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    public void defaultLogin() {
        EditText EmailField = findViewById(R.id.mainEmailEditText);
        EditText PasswordField = findViewById(R.id.mainPasswordEditText);
        EmailField.setText("test@test.dk");
        PasswordField.setText("testtest");
    }

    public void doLogin(View view) {

        EditText EmailField = findViewById(R.id.mainEmailEditText);
        EditText PasswordField = findViewById(R.id.mainPasswordEditText);
        String email = EmailField.getText().toString();
        String password = PasswordField.getText().toString();
        if (email.length() == 0 && password.length() == 0) {
            defaultLogin();
        } else if (email.length() > 5 && password.length() >= 8) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                Log.d(TAG, user.getEmail());
                                updateUI(user);
                                LogInSuccess();
                            } else {
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }
                        }
                    });
        } else {
            Toast.makeText(MainActivity.this, "Please enter a valid email and password", Toast.LENGTH_SHORT).show();
        }
    }

    public void LogInSuccess() {
        EditText EmailField = findViewById(R.id.mainEmailEditText);
        EditText PasswordField = findViewById(R.id.mainPasswordEditText);
        EmailField.setText("");
        PasswordField.setText("");
        Intent intent = new Intent(this, loggedIn.class);
        startActivity(intent);
    }

    private void updateUI(FirebaseUser user) {
        TextView status = findViewById(R.id.mainMessageTextView);
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if (firebaseUser != null) {
            status.setText("Welcome " + user.getEmail());
        }
    }
}


