package com.example.myvotingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ReparteeActivity extends AppCompatActivity {

    private RadioGroup radioGroupCandidates;
    private RadioGroup radioGroupIssues;
    private Button btnSubmit;
    private TextView textResult;
    private TextView party1;
    private TextView party2;
    private TextView party3;
    private int party1Votes = 0;
    private int party2Votes = 0;
    private int party3Votes = 0;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chooseparty);

        party1 = findViewById(R.id.textViewParty1Votes);
        party2 = findViewById(R.id.textViewParty2Votes);
        party3 = findViewById(R.id.textViewParty3Votes);
        btnSubmit = findViewById(R.id.buttonVote);
        textResult = findViewById(R.id.textResult);
        radioGroupCandidates = findViewById(R.id.radioGroupCandidates);
        radioGroupIssues = findViewById(R.id.radioGroupIssues);

        databaseHelper = new DatabaseHelper(this);

        // Retrieve the previous vote counts from the database (if available)
        party1Votes = databaseHelper.getPartyVotes(1);
        party2Votes = databaseHelper.getPartyVotes(2);
        party3Votes = databaseHelper.getPartyVotes(3);

        // Update the vote count TextViews
        party1.setText("Party 1 Votes: " + party1Votes);
        party2.setText("Party 2 Votes: " + party2Votes);
        party3.setText("Party 3 Votes: " + party3Votes);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedCandidateId = radioGroupCandidates.getCheckedRadioButtonId();
                int selectedIssueId = radioGroupIssues.getCheckedRadioButtonId();

                // Perform vote counting based on selected candidate and issue
                if (selectedCandidateId == R.id.radioButtonParty1 && selectedIssueId == R.id.radioButtonIssue1) {
                    party1Votes++;
                    Toast.makeText(ReparteeActivity.this, "Your Data is Saved In Our Database Successfully!🙏", Toast.LENGTH_SHORT).show();
                } else if (selectedCandidateId == R.id.radioButtonParty2 && selectedIssueId == R.id.radioButtonIssue2) {
                    party2Votes++;
                    Toast.makeText(ReparteeActivity.this, "Your Data is Saved In Our Database Successfully!🙏", Toast.LENGTH_SHORT).show();
                } else if (selectedCandidateId == R.id.radioButtonParty3 && selectedIssueId == R.id.radioButtonIssue3) {
                    party3Votes++;
                    Toast.makeText(ReparteeActivity.this, "Your Data is Saved In Our Database Successfully!🙏", Toast.LENGTH_SHORT).show();
                }

                // Update the vote count TextViews
                party1.setText("Party 1 Votes: " + party1Votes);
                party2.setText("Party 2 Votes: " + party2Votes);
                party3.setText("Party 3 Votes: " + party3Votes);

                // Display the result
                String result = "Party 1: " + party1Votes + " votes\n"
                        + "Party 2: " + party2Votes + " votes\n"
                        + "Party 3: " + party3Votes + " votes";
                textResult.setText(result);

                // Store the vote in the database
                databaseHelper.storeVote(selectedCandidateId, selectedIssueId);

                // Navigate to the "Thank You for Voting" activity
                Intent intent = new Intent(ReparteeActivity.this, ThankYouActivity.class);
                startActivity(intent);
                finish(); // Optional: Finish the current activity to prevent the user from going back
            }
        });
    }
}
