package com.example.myvotingapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class ThankYouActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thankyou);

        new Handler().postDelayed(()->
        {
            startActivity(new Intent(ThankYouActivity.this, loginActivity.class));
            finish();
        },4000);
    }

    }
