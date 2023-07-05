package com.example.myvotingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class loginActivity extends AppCompatActivity {

    private EditText loginusername, loginpassword;
    private Button loginbtn;
    private TextView signuptext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);

        loginusername = findViewById(R.id.login_username);
        loginpassword = findViewById(R.id.login_password);
        loginbtn = findViewById(R.id.btnlogin);
        signuptext = findViewById(R.id.signuptext);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateUsername() | !validatePassword()) {
                    // Validation failed
                } else {
                    checkUser();
                }
            }
        });

        signuptext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(loginActivity.this, sign_upActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean validateUsername() {
        String val = loginusername.getText().toString().trim();
        if (val.isEmpty()) {
            loginusername.setError("Username cannot be empty");
            return false;
        } else {
            loginusername.setError(null);
            return true;
        }
    }

    public boolean validatePassword() {
        String val = loginpassword.getText().toString().trim();
        if (val.isEmpty()) {
            loginpassword.setError("Password cannot be empty");
            return false;
        } else {
            loginpassword.setError(null);
            return true;
        }
    }

    public void checkUser() {
        String username = loginusername.getText().toString().trim();
        String userpassword = loginpassword.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserQuery = reference.orderByChild("username").equalTo(username);

        checkUserQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        helperclass user = userSnapshot.getValue(helperclass.class);
                        if (user != null && user.getPassword().equals(userpassword)) {
                            // Valid credentials
                            loginusername.setError(null);
                            loginpassword.setError(null);
                            Intent intent = new Intent(loginActivity.this, MainActivity.class);
                            startActivity(intent);
                            return; // Exit the loop after successful login
                        }
                    }
                    // Invalid password
                    loginpassword.setError("Invalid Credential");
                    loginpassword.requestFocus();
                } else {
                    // User does not exist
                    loginusername.setError("User Does not Exist");
                    loginusername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error
            }
        });
    }
}
