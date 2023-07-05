package com.example.Aditya_Rajmyvotingapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class sign_upActivity extends AppCompatActivity {

    EditText signupname , signupemail, signuppassword, Signupusename;
    TextView logintext;
    Button btnsignup;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        signupname = findViewById(R.id.signupname);
        signupemail=findViewById(R.id.signupemail);
        signuppassword = findViewById(R.id.password);
        Signupusename = findViewById(R.id.username);
        logintext=findViewById(R.id.logintext);
        btnsignup=findViewById(R.id.btnsignup);


        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                database = database.getInstance();
                reference= database.getReference("users");

                String name = signupname.getText().toString();
                String email = signupemail.getText().toString();
                String password = signuppassword.getText().toString();
                String username = Signupusename.getText().toString();

                helperclass helperclass = new helperclass(name,email,username,password);
                reference.child(name).setValue(helperclass);

                Toast.makeText(sign_upActivity.this, "You have signup successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(sign_upActivity.this, loginActivity.class);
                startActivity(intent);

            }
        });

        logintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sign_upActivity.this, loginActivity.class);
                startActivity(intent);
            }
        });


    }
}




