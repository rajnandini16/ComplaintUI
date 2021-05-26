package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity {
    private Firebase ref;
    private EditText user,fd;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=(EditText)findViewById(R.id.editText1) ;
        fd=(EditText)findViewById(R.id.editText);
        Firebase.setAndroidContext(this);
        ref =new Firebase( "https://mini-project-69fce-default-rtdb.firebaseio.com/");
        bt=(Button)findViewById(R.id.button3);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Skip();
            }
        });

    }

    public void Skip()
    {
        Intent intent=new Intent(this, Location.class);
        startActivity(intent);
    }
    public void feedbackinput(View view) {
        String usernameinput = user.getText().toString();
        String feedbackinput = fd.getText().toString();
        Toast.makeText(MainActivity.this,"Submitted Successfully",Toast.LENGTH_SHORT).show();
        Firebase Reusername = ref.child("Feedback");
        Firebase reffeedback = ref.child("Username");
        Reusername.setValue(usernameinput);



        reffeedback.setValue(feedbackinput);
    }

}