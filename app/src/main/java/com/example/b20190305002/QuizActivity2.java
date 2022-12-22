package com.example.b20190305002;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.b20190305002.R;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity2 extends AppCompatActivity {

    private TextView tvQuestion, tvScore, tvQuestionNo;
    private RadioGroup radioGroup;
    private RadioButton rb1, rb2, rb3;
    private Button btnNext;

    int totalQuestions;
    int qCounter = 0;
    int score;

    ColorStateList dfRbColor;
    boolean answered;


    private QuestionModel currentQuestion;

    private List<QuestionModel> questionsList;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionsList = new ArrayList<>();

        tvQuestion = findViewById(R.id.textQuestion);
        tvScore = findViewById(R.id.textScore);
        tvQuestionNo = findViewById(R.id.textQuestionNo);

        radioGroup = findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        btnNext = findViewById(R.id.btnNext);

        dfRbColor = rb1.getTextColors();

        addQuestions();
        totalQuestions = questionsList.size();
        showNextQuestion();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answered == false) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(QuizActivity2.this, "Please Select an Option", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });

    }

    private void checkAnswer() {
        answered = true;
        RadioButton rbSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNo = radioGroup.indexOfChild(rbSelected) + 1;
        if (answerNo == currentQuestion.getCorrectAnsNo()) {
            score++;
            tvScore.setText("Score: " + score);
        }
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        switch (currentQuestion.getCorrectAnsNo()) {
            case 1:
                rb1.setTextColor(Color.GREEN);
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                break;
        }
        if (qCounter < totalQuestions) {
            btnNext.setText("Next");
        } else {
            btnNext.setText("Finish");
        }

    }

    private void showNextQuestion() {

        radioGroup.clearCheck();
        rb1.setTextColor(dfRbColor);
        rb2.setTextColor(dfRbColor);
        rb3.setTextColor(dfRbColor);


        if (qCounter < totalQuestions) {
            currentQuestion = questionsList.get(qCounter);
            tvQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());

            qCounter++;
            btnNext.setText("Submit");
            tvQuestionNo.setText("Question: " + qCounter + "/" + totalQuestions);
            answered = false;

        } else {
            finish();
        }
    }

    private void addQuestions() {
        questionsList.add(new QuestionModel("Who led the Turkish Revolution and became the first President of Turkey?", " Mustafa Kemal Atatürk", "Adnan Menderes", " Turgut Özal", 1));
        questionsList.add(new QuestionModel("What was the main goal of the Turkish Revolution?", " To modernize and westernize the country", "To increase industrialization and economic development", " To return to traditional Islamic values and practices", 1));
        questionsList.add(new QuestionModel("Which of the following was NOT a major reform implemented during the Turkish Revolution?", "The adoption of a new, simplified Turkish alphabet", "The introduction of a new, democratic constitution", "The abolition of the Ottoman caliphate", 2));
        questionsList.add(new QuestionModel("The Turkish Revolution was preceded by which major event in Ottoman history?", "The Crimean War", "The Balkan Wars", "The Young Turks Revolution", 3));
        questionsList.add(new QuestionModel("What was the main political party established during the Turkish Revolution?", "The Republican People's Party ", "The Nationalist Movement Party", "The Democratic Party ", 1));
        questionsList.add(new QuestionModel("What was the main military organization involved in the Turkish Revolution?", "The Grey Wolves", "The Turkish Land Forces", "The Turkish Armed Forces", 3));
        questionsList.add(new QuestionModel("Who was the main military leader of the Turkish Revolution?", " İsmet İnönü", "Mustafa Kemal Atatürk", " Fevzi Çakmak", 2));
        questionsList.add(new QuestionModel("What was the main source of resistance to the Turkish Revolution?", "The foreign powers", "The religious conservatives", "The Ottoman Empire", 1));
        questionsList.add(new QuestionModel("What was the main international conflict in which the Turkish Revolution was involved?", "The Cold War", "World War I", " World War II", 2));
        questionsList.add(new QuestionModel("What was the main economic policy implemented during the Turkish Revolution?", "Neoliberalism", "State socialism", "Import substitution industrialization", 3));
    }
}