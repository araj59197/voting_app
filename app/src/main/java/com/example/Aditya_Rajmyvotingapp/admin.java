package com.example.Aditya_Rajmyvotingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class admin extends Fragment {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin, container, false);

        usernameEditText = view.findViewById(R.id.editTextUsername);
        passwordEditText = view.findViewById(R.id.editTextPassword);
        loginButton = view.findViewById(R.id.buttonLogin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Perform admin login logic here
                if (isValidCredentials(username, password)) {
                    // Admin login successful
                    // Proceed to admin dashboard or other functionality
                    // For example:
                    Toast.makeText(getActivity(), "Admin login successful", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(requireContext(), AdminActivity.class);
                    startActivity(intent);
                } else {
                    // Invalid credentials
                    Toast.makeText(getActivity(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private boolean isValidCredentials(String username, String password) {
        // Perform validation logic here
        // For example, check against a predefined username and password
        if (username == null || password == null) {
            // Null values are considered invalid credentials
            return false;
        }

        // Trim any leading or trailing whitespace from the username and password
        username = username.trim();
        password = password.trim();

        // Check if the username and password meet certain criteria
        if (username.isEmpty() || password.isEmpty()) {
            // Empty values are considered invalid credentials
            return false;
        }

        // Check if the username and password have a minimum length requirement
        int minLength = 5;
        if (username.length() < minLength || password.length() < minLength) {
            // Credentials with insufficient length are considered invalid
            return false;
        }

        // Perform additional validation logic, such as checking against a database or API

        // Example: Check against a predefined username and password
        String validUsername = "admin";
        String validPassword = "admin";

        if (username.equals(validUsername) && password.equals(validPassword)) {
            // Valid credentials
            return true;
        } else {
            // Invalid credentials
            return false;
        }
    }
}
