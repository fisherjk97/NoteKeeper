package com.example.notekeeper;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class NoteActivity extends AppCompatActivity {

    public static final String NOTE_POSITION = "com.example.notekeeper.NOTE_POSITION";
    public static final int POSITION_NOT_SET = -1;
    private NoteInfo mNote;
    private boolean mIsNewNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Spinner spinnerCourses = (Spinner) findViewById(R.id.spinner_courses);

        //get list of courses
        List<CourseInfo> courses = DataManager.getInstance().getCourses();

        //get the adapter to format the course layouts
        ArrayAdapter<CourseInfo> adapterCourses = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courses);

        //set the data to the dropdown
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //associate teh adapter with the spinner
        spinnerCourses.setAdapter(adapterCourses);

        readDisplayStateValues();

        EditText textNoteTitle = (EditText) findViewById(R.id.text_note_title);
        EditText textNoteText = (EditText) findViewById(R.id.text_note_text);

        if(!mIsNewNote)
            displayNote(spinnerCourses, textNoteTitle, textNoteText);

    }

    private void readDisplayStateValues(){
        Intent intent = getIntent();
        int position = intent.getIntExtra(NOTE_POSITION, POSITION_NOT_SET);

        mIsNewNote = (position == POSITION_NOT_SET);
        if(!mIsNewNote){
            mNote = DataManager.getInstance().getNotes().get(position);
        }
    }

    private void displayNote(Spinner spinnerCourses, EditText textNoteTitle, EditText textNoteText){
        List<CourseInfo> courses = DataManager.getInstance().getCourses();
        int courseIndex = courses.indexOf(mNote.getCourse());

        spinnerCourses.setSelection(courseIndex);
        textNoteTitle.setText(mNote.getTitle());
        textNoteText.setText(mNote.getText());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
