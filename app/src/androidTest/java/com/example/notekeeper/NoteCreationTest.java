package com.example.notekeeper;

import androidx.test.espresso.ViewInteraction;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;

@RunWith(AndroidJUnit4.class)
public class NoteCreationTest {

    @Rule
    public ActivityTestRule<NoteListActivity> mNoteListActivityRule = new ActivityTestRule<>(NoteListActivity.class);

    @Test
    public void createNewNote(){
        /*
        //get to the view
        ViewInteraction fabNewNote = onView(withId(R.id.fab));

        //perform the action
        fabNewNote.perform(click());
        */
        //simply...
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.text_note_title)).perform(typeText("Test note title"));
        onView(withId(R.id.text_note_text)).perform(typeText("This is the body of our test note"), closeSoftKeyboard());
    }
}