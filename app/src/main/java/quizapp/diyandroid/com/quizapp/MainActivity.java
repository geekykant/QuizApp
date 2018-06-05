package quizapp.diyandroid.com.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int score = 0;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit = (Button) findViewById(R.id.submit);
        Button reset = (Button) findViewById(R.id.reset);

        submit.setOnClickListener(this);
        reset.setOnClickListener(this);

        //Clears options and sets listener
        new questionsExtraAction().initialRadioActions();
    }

    @Override
    public void onClick(View view) {
        //Button clicks listener
        switch (view.getId()) {
            case R.id.submit:
                new questionsExtraAction().checkAnswers();
                submit.setClickable(false);
                Toast.makeText(this, "Your Score is: " + score + "/9", Toast.LENGTH_SHORT).show();
                new questionsExtraAction().showAnswers();
                break;

            case R.id.reset:
                score = 0;
                submit.setClickable(true);
                new questionsExtraAction().initialRadioActions();
                break;
        }
    }

    private class questionsExtraAction {

        int[] all_questions_id = {
                R.id.question_1,
                R.id.question_2,
                R.id.question_3,
                R.id.question_5,
                R.id.question_6,
                R.id.question_7,
                R.id.question_8,
        };

        int[] answers_id = {
                R.id.rcorrect_1,
                R.id.rcorrect_2,
                R.id.rcorrect_3,
                R.id.rcorrect_5,
                R.id.rcorrect_6,
                R.id.rcorrect_7,
                R.id.rcorrect_8,
        };

        private void showAnswers() {
            initialRadioActions();
            for (int Aanswers_id : answers_id) {
                ((RadioButton) findViewById(Aanswers_id)).setChecked(true);
            }

            ((EditText) findViewById(R.id.type_question_4)).setText(getResources().getText(R.string.kik));

            ((CheckBox) findViewById(R.id.question_9_box1)).setChecked(true);
            ((CheckBox) findViewById(R.id.question_9_box3)).setChecked(true);
        }

        private void initialRadioActions() {
            for (int Aquestionid : all_questions_id) {
                ((RadioGroup) findViewById(Aquestionid)).clearCheck();
            }

            ((CheckBox) findViewById(R.id.question_9_box1)).setChecked(false);
            ((CheckBox) findViewById(R.id.question_9_box2)).setChecked(false);
            ((CheckBox) findViewById(R.id.question_9_box3)).setChecked(false);
            ((CheckBox) findViewById(R.id.question_9_box4)).setChecked(false);
            ((EditText) findViewById(R.id.type_question_4)).setText(null);
        }

        private void checkAnswers() {
            for (int Aanswers_id : answers_id) {
                if (((RadioButton) findViewById(Aanswers_id)).isChecked())
                    score++;
            }

            if (((EditText) findViewById(R.id.type_question_4)).getText().toString().toLowerCase().equals(getResources().getText(R.string.kik)))
                score++;
            if (((CheckBox) findViewById(R.id.question_9_box1)).isChecked() && ((CheckBox) findViewById(R.id.question_9_box3)).isChecked() &&
                    !((CheckBox) findViewById(R.id.question_9_box2)).isChecked() && !((CheckBox) findViewById(R.id.question_9_box4)).isChecked() )
                score++;
        }
    }
}

