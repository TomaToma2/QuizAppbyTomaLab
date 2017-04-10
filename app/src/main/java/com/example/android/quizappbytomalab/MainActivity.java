package com.example.android.quizappbytomalab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;
public class MainActivity extends AppCompatActivity {



    EditText eText;
    int score;
    String correctResult, wrongResult;
    TextView correctResultView, wrongResultView;

    // gets the TextView in which to display the correct answers to the quiz questions.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eText = (EditText) findViewById(R.id.name_field);

        correctResultView = (TextView) findViewById(R.id.correctResults);
        correctResult = correctResultView.getText().toString();
        wrongResultView = (TextView) findViewById(R.id.wrongResults);
        wrongResult = correctResultView.getText().toString();
        if (savedInstanceState != null) {
            score = savedInstanceState.getInt("savedScore");
            correctResult = savedInstanceState.getString("savedCorrect");
            wrongResult = savedInstanceState.getString("savedWrong");

            correctResultView.setText(correctResult);
            wrongResultView.setText(wrongResult);
        }
    }
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();}

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();}


        @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("savedScore", score);
        savedInstanceState.putString("savedCorrect", correctResult);
        savedInstanceState.putString("savedWrong", wrongResult);
        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * checks if a checkBox has been tapped.
     * function will be used for many CheckBoxes, thus for many IDs
     * @param idName the id of the CheckBox whose value is to be verified
     * @return returns true if the CheckBox is checked and false otherwise
     * */

    public boolean getOptions(int idName) {
        CheckBox c = (CheckBox) findViewById(idName);
        return c.isChecked();
    }

    /**
     * checks if the options the user picked are correct
     * compares each option we got after calling getOptions(id) with the correct answers a, b, c or d
     *
     * @param idAnswerA id of the first checkbox in the group
     * @param answerA   correct answer for first checkbox in the group
     * @param idAnswerB  id of the second checkbox in the group
     * @param answerB   correct answer for second checkbox in the group
     * @param idAnswerC id of the third checkbox in the group
     * @param answerC   correct answer for third checkbox in the group
     * @param idAnswerD  id of the fourth checkbox in the group
     * @param answerD   cor1rect answer for fourth checkbox in the group
     * @return returns true if the user answered correctly and false otherwise
     */

    public boolean checkBox(int idAnswerA, boolean answerA, int idAnswerB, boolean answerB, int idAnswerC, boolean answerC, int idAnswerD, boolean answerD ) {
        boolean option1, option2, option3, option4;
        option1 = getOptions(idAnswerA);
        option2 = getOptions(idAnswerB);
        option3 = getOptions(idAnswerC );
        option4 = getOptions(idAnswerD);
        return option1 == answerA && option2 == answerB && option3 == answerC && option4 == answerD;
    }

    public boolean checkRadio(int id, String solution) {
        RadioGroup radioQ1 = (RadioGroup) findViewById(id);
        int selectedID = radioQ1.getCheckedRadioButtonId();
        RadioButton button = (RadioButton) findViewById(selectedID);
        String text = button.getText().toString().toLowerCase();
        return Objects.equals(text, solution);
    }

    public void checkAnswer(boolean answer, String i, String solution) {
        if (answer) {
            correctResult += i + ". Correct\n";
            score++;
        } else {
            wrongResult += i + ". Incorrect(Correct Answer: " + solution + ")\n";
        }
    }
    /**
     * gathers all the answers from the previous questions and displays the results on the screen
     */

    public void submitAnswers(View view) {
        score = 0;
        correctResult = "CORRECT ANSWERS\n";
        wrongResult = "INCORRECT ANSWERS\n";

        EditText editTextUserName = (EditText) findViewById(R.id.name_field);
        String UserName = editTextUserName.getText().toString();

        boolean questionOne = checkRadio(R.id.radioQ1, "80 years");
        checkAnswer(questionOne, "1", "80 years");

        boolean questionTwo = checkRadio(R.id.radioQ2, "35786km");
        checkAnswer(questionTwo, "2", "35786km");

        boolean questionThree = checkBox(R.id.A1Q3, true, R.id.A2Q3, true, R.id.A3Q3, false, R.id.A4Q3, true);
        checkAnswer(questionThree, "3", "8848, 8850, 8844");

        boolean questionFour = checkBox(R.id.A1Q4, true, R.id.A2Q4, false, R.id.A3Q4, false, R.id.A4Q4, true);
        checkAnswer(questionFour, "4", "1 ton, 1000kg");

        correctResultView.setText(correctResult);
        wrongResultView.setText(wrongResult);

        Toast toast = Toast.makeText(getApplicationContext(), UserName + " You scored " + score + " out of 4!", Toast.LENGTH_LONG);
        toast.show();
    }
}













