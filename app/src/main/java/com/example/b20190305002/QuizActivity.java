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

public class QuizActivity extends AppCompatActivity {

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
                        Toast.makeText(QuizActivity.this, "Please Select an Option", Toast.LENGTH_SHORT).show();
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
        questionsList.add(new QuestionModel("Which Sultan conquered Constantinople in 1453 ?", "Osman I", "Mehmed II", "Suleiman I the Magnificent", 2));
        questionsList.add(new QuestionModel("Who was the famous architect who built the The Suleymaniye and over 300 other buildings and bridges during the reign of Suleiman I the Magnificent", "Mimar Sinan", "Envar Pasha", "Kara Mustafa.", 1));
        questionsList.add(new QuestionModel("What did the Janissaries symbolically do to show they were in revolt ?", "Burn their turbans.", "Overturn their large communal cooking pots.", "Sing a certain song.", 2));
        questionsList.add(new QuestionModel("Which Sultan defeated the Persians at the battle  Chaldiran in 1514 and the Mamluk Dynasty in Egypt and claimed the Caliphate?", "Osman I", "Abdul Hamid II", "Selim I", 3));
        questionsList.add(new QuestionModel("The main area of conflict between the Ottomans and the Russians was fought in the Caucasus region. The Caucasus lies between which two seas?", "The Black Sea and the Caspian Sea", "The Black Sea and the Mediterranean Sea", "The Mediterranean Sea and the Red Sea", 1));
        questionsList.add(new QuestionModel("The Battle of Romani, fought from 3rd to 5th August 1916, was Britain's first major victory against the Ottomans. What was notable about two of the three Allied military units involved?", "They were armed with swords", "They were armed with rocket launchers", "They were on horseback",  3));
        questionsList.add(new QuestionModel("Perhaps the greatest Ottoman victory of the war took place in the Dardanelles Campaign. By what name is it more usually known?", "The Gallipoli Campaign", "The Turkish Campaign", "The Desert Campaign", 1));
        questionsList.add(new QuestionModel("During the war some of those ruled by the Ottomans rose up to fight against them. Which people were they?", "The Greeks", "The Egyptians", "The Arabs", 3));
        questionsList.add(new QuestionModel("Following the Ottoman's defeat in the war, their Empire was split into several smaller countries. The heartland of the Empire became which modern nation?", "Iran", "Turkey", "Iraq", 2));
        questionsList.add(new QuestionModel("The British sent several people to help the Arabs in their revolt, among them a young Captain, remembered today as who?", "Langham of Persia", "Lawrence of Arabia", "Lewington of Palestine", 2));
    }
}