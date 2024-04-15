package com.example.assignment1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {
    private Button submitBtn;
    private TextView timerText;

    private EditText blank1text, blank2text, blank3text;
    private RadioGroup answer1RadioGroup, answer2RadioGroup, answer3RadioGroup, answer4RadioGroup;
    private RadioButton answer1ARadioButton, answer1BRadioButton, answer1CRadioButton;
    private RadioButton answer2ARadioButton, answer2BRadioButton, answer2CRadioButton;
    private RadioButton answer3ARadioButton, answer3BRadioButton, answer3CRadioButton;
    private static final long COUNTDOWN_DURATION_MILLIS = 1 * 60 * 1000; //this = 1 min in milliseconds
    private boolean isTimerRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        submitBtn = findViewById(R.id.submitButton);
        timerText = findViewById(R.id.timerTextView);

        answer1RadioGroup = findViewById(R.id.answer1RadioGroup);
        answer2RadioGroup = findViewById(R.id.answer2RadioGroup);
        answer3RadioGroup = findViewById(R.id.answer3RadioGroup);

        answer1ARadioButton = findViewById(R.id.answer1ARadioButton);
        answer1BRadioButton = findViewById(R.id.answer1BRadioButton);
        answer1CRadioButton = findViewById(R.id.answer1CRadioButton);

        answer2ARadioButton = findViewById(R.id.answer2ARadioButton);
        answer2BRadioButton = findViewById(R.id.answer2BRadioButton);
        answer2CRadioButton = findViewById(R.id.answer2CRadioButton);

        answer3ARadioButton = findViewById(R.id.answer3ARadioButton);
        answer3BRadioButton = findViewById(R.id.answer3BRadioButton);
        answer3CRadioButton = findViewById(R.id.answer3CRadioButton);

        blank1text = findViewById(R.id.fillBlank1EditText);
        blank2text = findViewById(R.id.fillBlank2EditText);
        blank3text = findViewById(R.id.fillBlank3EditText);

        startCountdown();


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmptyAnswers()) {
                    displayGrade(getMark());
                } else {
                    Toast.makeText(ThirdActivity.this, "Please answer all questions", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private int getMark() {
        int mark = 0;

        int radioId1 = answer1RadioGroup.getCheckedRadioButtonId();
        int radioId2 = answer2RadioGroup.getCheckedRadioButtonId();
        int radioId3 = answer3RadioGroup.getCheckedRadioButtonId();

        String answer1 = blank1text.getText().toString();
        String answer2 = blank2text.getText().toString();
        String answer3 = blank3text.getText().toString();

        if (radioId1 == R.id.answer1BRadioButton) {
            mark++;
        }
        if (radioId2 == R.id.answer2CRadioButton) {
            mark++;
        }
        if (radioId3 == R.id.answer3ARadioButton) {
            mark++;
        }

        if (answer1.equalsIgnoreCase("object")) {
            mark++;
        }
        if (answer2.equalsIgnoreCase("semicolon") || answer2.equals(";")) {
            mark++;
        }
        if (answer3.equalsIgnoreCase("1010")) {
            mark++;
        }
        return mark;
    }

    private void displayGrade(int mark) {
        String message;
        if (mark == 6) {
            message = "Your grade is: " + mark + "/6" + " , Excellent";
        } else if (mark >= 4) {
            message = "Your grade is: " + mark + "/6" + " , Not bad";
        } else {
            message = "Your grade is: " + mark + "/6" + " , better luck next time";
        }
        showGradeMessage(message);
    }

    private void showGradeMessage(String message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Quiz Result");
        alert.setMessage(message);
        alert.setPositiveButton("OK", null);
        alert.setNegativeButton("Home Screen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //give the student an option to go back to main screen after finishing the quiz
                Intent intent = new Intent(ThirdActivity.this, SecondActivity.class);
                startActivity(intent);
                finish();
            }
        });
        alert.show();
    }

    private void startCountdown() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            long startTime = System.currentTimeMillis();

            @Override
            public void run() {
                isTimerRunning = true;
                long elapsedTime = System.currentTimeMillis() - startTime;
                long remainingTime = COUNTDOWN_DURATION_MILLIS - elapsedTime;

                if (remainingTime > 0) {
                    int minutes = (int) (remainingTime / (60 * 1000));
                    int seconds = (int) ((remainingTime % (60 * 1000)) / 1000);

                    String timeString = String.format("%02d:%02d", minutes, seconds);
                    timerText.setText(timeString);


                    handler.postDelayed(this, 1000); // Repeat every second
                } else {
                    isTimerRunning = false;

                    AlertDialog.Builder alert = new AlertDialog.Builder(ThirdActivity.this);
                    alert.setTitle("Time's up");
                    alert.setMessage("Do you want to go back to the home screen ?");
                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(ThirdActivity.this, SecondActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                    alert.setNegativeButton("No", null);
                    alert.show();
                }
            }
        }, 0);
    }

    private boolean isEmptyAnswers() {
        if (blank1text.getText().toString().trim().isEmpty() || blank2text.getText().toString().trim().isEmpty() || blank3text.getText().toString().trim().isEmpty()) {
            return true;
        }

        if (answer1RadioGroup.getCheckedRadioButtonId() == -1 || answer2RadioGroup.getCheckedRadioButtonId() == -1 || answer3RadioGroup.getCheckedRadioButtonId() == -1) {
            return true;
        }
        return false;
    }


}
