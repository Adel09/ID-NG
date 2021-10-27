package com.crumlabs.naijadocuments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LicenseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license);


    }

    public void goToAddLicense(View view){
        Intent intent = new Intent(this, AddLicenseActivity.class);
        startActivity(intent);
    }



}