package com.crumlabs.naijadocuments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UpdateProfileActivity extends AppCompatActivity {

    TextInputEditText nameInput, dobInput, stateInput, phoneInput;
    AutoCompleteTextView act;
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        nameInput = (TextInputEditText) findViewById(R.id.name_input);
        dobInput = (TextInputEditText) findViewById(R.id.dob_input);
        stateInput = (TextInputEditText) findViewById(R.id.state_input);
        act = (AutoCompleteTextView) findViewById(R.id.autocomptext);
        phoneInput = (TextInputEditText) findViewById(R.id.phone_input);

        String[] genders = {"Male", "Female"};
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_item, genders);
        act.setAdapter(adapter);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        database = FirebaseDatabase.getInstance();
    }

    public void proceed(View view){


        String uid = currentUser.getUid();
        myRef = database.getReference().child("Profiles").child(uid);

        String fullname = nameInput.getText().toString();
        String dob = dobInput.getText().toString();
        String state = stateInput.getText().toString();
        String gender = act.getText().toString();
        String phone = phoneInput.getText().toString();

        HashMap<String, Object> map = new HashMap<>();
        map.put("fullname", fullname);
        map.put("dob", dob);
        map.put("email", currentUser.getEmail());
        map.put("phone", phone);
        map.put("gender", gender);
        map.put("state", state);

        myRef.updateChildren(map);
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);

    }

}