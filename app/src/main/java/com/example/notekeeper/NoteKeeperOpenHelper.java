package com.example.notekeeper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.notekeeper.NoteKeeperDatabaseContract.*;

public class NoteKeeperOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "NoteKeeper.db";
    public static final int DATABASE_VERSION = 2;


    public NoteKeeperOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table schema
        db.execSQL(CourseInfoEntry.SQL_CREATE_TABLE);
        db.execSQL(NoteInfoEntry.SQL_CREATE_TABLE);

        //create table indexes
        db.execSQL(CourseInfoEntry.SQL_CREATE_INDEX1);
        db.execSQL(NoteInfoEntry.SQL_CREATE_INDEX1);

        DatabaseDataWorker worker = new DatabaseDataWorker(db);
        worker.insertCourses();
        worker.insertSampleNotes();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //upgrade people on database 1 to next version
        if(oldVersion < 2) {//evolve this overtime so we want to have this hardcoded
            db.execSQL(CourseInfoEntry.SQL_CREATE_INDEX1);
            db.execSQL(NoteInfoEntry.SQL_CREATE_INDEX1);
        }
    }
}
