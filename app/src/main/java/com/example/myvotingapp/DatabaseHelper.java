package com.example.myvotingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "voting.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_VOTES = "votes";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CANDIDATE_ID = "candidate_id";
    public static final String COLUMN_ISSUE_ID = "issue_id";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createVotesTableQuery = "CREATE TABLE " + TABLE_VOTES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_CANDIDATE_ID + " INTEGER,"
                + COLUMN_ISSUE_ID + " INTEGER"
                + ")";
        db.execSQL(createVotesTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Implement upgrade logic here if needed
    }

    public void storeVote(int candidateId, int issueId) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CANDIDATE_ID, candidateId);
        values.put(COLUMN_ISSUE_ID, issueId);

        long newRowId = db.insert(TABLE_VOTES, null, values);

        if (newRowId != -1) {
            // Insertion successful
            // Perform any additional actions if needed
        } else {
            // Insertion failed
            // Handle the error
        }
    }

    public Cursor retrieveVotes() {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                COLUMN_CANDIDATE_ID,
                COLUMN_ISSUE_ID
        };

        Cursor cursor = db.query(
                TABLE_VOTES,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        return cursor;
    }

    public int getPartyVotes(int candidateId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT COUNT(*) FROM " + TABLE_VOTES + " WHERE " + COLUMN_CANDIDATE_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(candidateId)});
        int count = 0;
        if (cursor != null && cursor.moveToFirst()) {
            count = cursor.getInt(0);
            cursor.close();
        }
        return count;
    }


//
//    public int getTotalVotes() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String query = "SELECT * FROM " + TABLE_VOTES;
//        Cursor cursor = db.rawQuery(query, null);
//        int totalVotes = cursor.getCount();
//        cursor.close();
//        return totalVotes;
//    }

}


