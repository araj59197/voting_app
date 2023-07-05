package com.example.Aditya_Rajmyvotingapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.concurrent.Executor;

public class HomeFragment extends Fragment {

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        TextView msg_txt = view.findViewById(R.id.text_message);
        Button login_btn = view.findViewById(R.id.register);

        Button next = view.findViewById(R.id.next);



        // Create the BiometricManager and check if the device supports biometric authentication
        BiometricManager biometricManager = BiometricManager.from(requireContext());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            switch (biometricManager.canAuthenticate()) {
                case BiometricManager.BIOMETRIC_SUCCESS:
                    msg_txt.setText("You can use the fingerprint sensor to Register");
                    msg_txt.setTextColor(Color.parseColor("#Fafafa"));
                    break;
                case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                    msg_txt.setText("The device doesn't have a fingerprint sensor");
                    login_btn.setVisibility(View.GONE);
                    break;
                case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                    msg_txt.setText("The biometric sensor is currently unavailable");
                    login_btn.setVisibility(View.GONE);
                    break;
                case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                    msg_txt.setText("Your device doesn't have any fingerprint saved, please check your security settings");
                    login_btn.setVisibility(View.GONE);
                    break;
            }
        }

        // Create an Executor
        Executor executor = ContextCompat.getMainExecutor(requireContext());

        // Create a BiometricPrompt instance
        BiometricPrompt biometricPrompt = new BiometricPrompt(HomeFragment.this, executor, new BiometricPrompt.AuthenticationCallback() {

            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(requireContext(), "Authentication Error: " + errString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(requireContext(), "Fingerprint Register Successfull! \n please Proceed With Next Step", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(requireContext(), "Authentication Failed", Toast.LENGTH_SHORT).show();
            }
        });

        // Create a BiometricPrompt.PromptInfo object
        final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Login")
                .setDescription("Use your fingerprint to login to your app")
                .setNegativeButtonText("Cancel")
                .build();

        // Set the click listener for the login button
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                biometricPrompt.authenticate(promptInfo);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), ReparteeActivity.class);
                startActivity(intent);
            }
        });

        return view;

    }
}


