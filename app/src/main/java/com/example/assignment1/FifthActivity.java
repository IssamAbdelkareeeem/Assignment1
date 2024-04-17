package com.example.assignment1;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class FifthActivity extends AppCompatActivity {

    private TextInputEditText textInputEditText;
    private SharedPreferences sharedPreferences;

    private static final String prefs = "prefs";
    private static final String text = "savedText";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        textInputEditText = findViewById(R.id.inputEditText);

        sharedPreferences = getSharedPreferences(prefs, MODE_PRIVATE);
        String savedText = sharedPreferences.getString(text, "");
        textInputEditText.setText(savedText);
    }

    @Override
    protected void onPause() {
        super.onPause();
        String textToSave = textInputEditText.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(text, textToSave);
        editor.apply();
    }
}
