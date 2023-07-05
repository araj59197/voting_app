package com.example.Aditya_Rajmyvotingapp;


import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class AdminActivity extends AppCompatActivity {

    private TextView adminPanelTextView;
    private TextView totalVotesTextView;
    private TextView party1VotesTextView;
    private TextView party2VotesTextView;
    private TextView party3VotesTextView;
    private DatabaseHelper databaseHelper;
    private TextView electionResultTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminactivity);

        adminPanelTextView = findViewById(R.id.adminPanelTextView);
        totalVotesTextView = findViewById(R.id.totalVotesTextView);
        party1VotesTextView = findViewById(R.id.party1VotesTextView);
        party2VotesTextView = findViewById(R.id.party2VotesTextView);
        party3VotesTextView = findViewById(R.id.party3VotesTextView);
        electionResultTextView = findViewById(R.id.electionResultTextView);

        databaseHelper = new DatabaseHelper(this);

        // Retrieve and display data from the database
        retrieveVotes();
        displayTotalVotes();
        displayPartyVotes();
    }

    private void displayTotalVotes() {
        Cursor cursor = databaseHelper.retrieveVotes();
        int totalVotes = cursor.getCount();
        cursor.close();

        totalVotesTextView.setText("Total Votes: " + totalVotes);
    }

    private void displayPartyVotes() {
        Cursor cursor = databaseHelper.retrieveVotes();

        int party1Votes = 0;
        int party2Votes = 0;
        int party3Votes = 0;

        while (cursor.moveToNext()) {
            int candidateId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_CANDIDATE_ID));

            if (candidateId == R.id.radioButtonParty1) {
                party1Votes++;
            } else if (candidateId == R.id.radioButtonParty2) {
                party2Votes++;
            } else if (candidateId == R.id.radioButtonParty3) {
                party3Votes++;
            }
        }
        cursor.close();

        party1VotesTextView.setText("Party 1 Votes (Aam Aadmi Party): " + party1Votes);
        party2VotesTextView.setText("Party 2 Votes (BJP): " + party2Votes);
        party3VotesTextView.setText("Party 3 Votes: (Congress): " + party3Votes);


        // Determine the winner
        int maxVotes = Math.max(Math.max(party1Votes, party2Votes), party3Votes);
        String winner;

        if (maxVotes == party1Votes) {
            winner = "Party 1";
        } else if (maxVotes == party2Votes) {
            winner = "Party 2";
        } else {
            winner = "Party 3";
        }

        // Display the election result
        String result = "Election Result:\n\n" +
                "Party 1 Votes: " + party1Votes + "\n" +
                "Party 2 Votes: " + party2Votes + "\n" +
                "Party 3 Votes: " + party3Votes + "\n\n" +
                "Winner: " + winner;

        electionResultTextView.setText(result);
    }

    private void retrieveVotes() {
        Cursor cursor = databaseHelper.retrieveVotes();

        StringBuilder votesBuilder = new StringBuilder();
        while (cursor.moveToNext()) {
            int candidateId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_CANDIDATE_ID));
            int issueId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ISSUE_ID));

            // Append the retrieved vote information to the StringBuilder
            votesBuilder.append("Candidate ID: ").append(candidateId).append(", Issue ID: ").append(issueId).append("\n");
        }
        cursor.close();

        // Display the retrieved votes in the admin panel
        String votes = votesBuilder.toString();
        adminPanelTextView.setText(votes);

        // Retrieve votes from the database and update the UI as needed

    }
}
