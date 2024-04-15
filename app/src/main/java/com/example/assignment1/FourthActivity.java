package com.example.assignment1;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FourthActivity extends AppCompatActivity {
    private ListView subjectListView;
    private TextView infoTextView;
    private SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        subjectListView = findViewById(R.id.listView);
        infoTextView = findViewById(R.id.textView3);
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        ArrayList<String> subjects = new ArrayList<>();
        subjects.add("Introduction");
        subjects.add("Numbering System");
        subjects.add("Operating Systems");
        subjects.add("Programming");
        subjects.add("Computer Science");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, subjects);

        String lastSelectedSubject = sharedPreferences.getString("last_subject", "");

        int lastIndex = subjects.indexOf(lastSelectedSubject);
        if (lastIndex != -1) {
            subjectListView.setSelection(lastIndex);
            updateInfoTextView(lastSelectedSubject); //show the info for the last selected subject
        }

        subjectListView.setAdapter(adapter);

        subjectListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                String selectedSubject = subjects.get(position);

                // Update the TextView with corresponding information
                if (selectedSubject.equals("Introduction")) {
                    infoTextView.setText(introInfo());
                } else if (selectedSubject.equals("Numbering System")) {
                    infoTextView.setText(numberingSystemInfo());
                } else if (selectedSubject.equals("Operating Systems")) {
                    infoTextView.setText(operatingSystemsInfo());
                } else if (selectedSubject.equals("Programming")) {
                    infoTextView.setText(programmingInfo());
                } else if (selectedSubject.equals("Computer Science")) {
                    infoTextView.setText(computerScienceInfo());
                }
            }
        });
    }

    private void updateInfoTextView(String selectedSubject) {
        switch (selectedSubject) {
            case "Introduction":
                infoTextView.setText(introInfo());
                break;
            case "Numbering System":
                infoTextView.setText(numberingSystemInfo());
                break;
            case "Operating Systems":
                infoTextView.setText(operatingSystemsInfo());
                break;
            case "Programming":
                infoTextView.setText(programmingInfo());
                break;
            case "Computer Science":
                infoTextView.setText(computerScienceInfo());
                break;
        }
    }

    private String introInfo() {
        return "Technology is all about using science to create tools and systems that help us "
                + "in our daily lives, It includes things like phones, computers, and the internet, "
                + "which makes it easier for us to communicate, learn, and solve problems";
    }

    private String numberingSystemInfo() {
        return "Numbering systems are methods of representing numbers. Common numbering systems "
                + "like binary, octal, decimal, and hexadecimal. Each numbering system has its "
                + "own base and set of digits";
    }

    private String operatingSystemsInfo() {
        return "An operating system (OS) is a software that manages the computer hardware and provides "
                + "common services for computer programs. Examples of operating systems :  "
                + "Windows, macOS, Linux, and Android";
    }

    private String programmingInfo() {
        return "Programming is writing instructions for computers to execute, "
                + "Programs are created using programming languages like Java, Python, C++, "
                + "and JavaScript, Programming allows us to create software and applications "
                + "that perform many tasks";
    }

    private String computerScienceInfo() {
        return "Computer science is the study of computers and computational systems "
                + "It involves understanding algorithms, data structures, programming languages, "
                + "computer architecture, and more. Computer scientists develop theories, "
                + "design algorithms, and create new technologies";
    }


}
