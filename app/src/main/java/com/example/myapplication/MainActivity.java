package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity {
    private Firebase ref;
    private EditText fd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fd=(EditText)findViewById(R.id.editText);
        Firebase.setAndroidContext(this);
        ref =new Firebase( "https://mini-project-69fce-default-rtdb.firebaseio.com/");

    }
    public void feedbackinput(View view)
    {
        String feedbackinput=fd.getText().toString();
        Firebase reffeedback=ref.child("Feedback");
        reffeedback.setValue(feedbackinput);
    }
}