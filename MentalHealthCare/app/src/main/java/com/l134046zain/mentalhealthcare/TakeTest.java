package com.l134046zain.mentalhealthcare;

import android.app.Application;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TakeTest extends AppCompatActivity {



    Button _NextButton;
    TextView _question;
    TextView _AltText;
    private int level;
    int questionNumber=0;
    int answerSelected;
    int depression_score;
    int anxiety_score;
    int OCD_score;
    Questionnaire level1_questionnaire;
    Questionnaire level2_questionnaire;
    ArrayList<String> questionBank = new ArrayList<String>();
    ArrayList<Integer> answers = new ArrayList<Integer>();
    RadioButton _option1;
    RadioButton _option2;
    RadioButton _option3;
    RadioButton _option4;
    RadioButton _option5;
    RadioGroup radioGroup;

    public String generatedisease()
    {
        String Disease=new String();
        ArrayList<Integer> answers=level1_questionnaire.getAns();


        if(answers.get(0) > answers.get(1)) depression_score = answers.get(0);
        else depression_score = answers.get(1);

        if(answers.get(5)>answers.get(6) && answers.get(5) > answers.get(7))
            anxiety_score = answers.get(5);
        else if (answers.get(6)>answers.get(5) && answers.get(6) > answers.get(7))
            anxiety_score = answers.get(6);
        else anxiety_score = answers.get(7);

        if(answers.get(15) > answers.get(16)) depression_score = answers.get(15);
        else depression_score = answers.get(16);


        if(depression_score >= anxiety_score && depression_score>=OCD_score ) {

            Toast.makeText(getApplicationContext(), "Now you will be given a questionnaire for Depression", Toast.LENGTH_SHORT).show();
            //populate level2 depression questionnaire

            questionBank.clear();
            questionBank.add("Q1 I could not stop feeling sad.");
            questionBank.add("Q2 I felt alone.");
            questionBank.add("Q3 I felt everything in my life went wrong.");
            questionBank.add("Q4 I felt like I couldn’t do anything right.");
            questionBank.add("Q5 I felt lonely.");
            questionBank.add("Q6 I felt sad.");
            questionBank.add("Q7 I felt unhappy.");
            questionBank.add("Q8 I thought that my life was bad.");
            questionBank.add("Q9 Being sad made it hard for me to do things with my friends.");
            questionBank.add("Q10 I didn’t care about anything.");
            questionBank.add("Q11 I felt stressed.");
            questionBank.add("Q12 I felt too sad to eat.");
            questionBank.add("Q13 I wanted to be by myself.");
            questionBank.add("Q14 It was hard for me to have fun.");

            level2_questionnaire = new Questionnaire(questionBank,2," How often you have been bothered by the following symptom during the past 7 days.",
                    "Never","Almost Never","Sometimes","Often","Almost Always");

        }

        else if(anxiety_score>depression_score && anxiety_score>=OCD_score)
        {
            Toast.makeText(getApplicationContext(), "Now you will be given a questionnaire for Anxiety", Toast.LENGTH_SHORT).show();

            //populate level2 anxiety questionnaire
            questionBank.clear();
            questionBank.add("Q1 I felt fearful.");
            questionBank.add("Q2 I felt anxious.");
            questionBank.add("Q3 I felt worried.");
            questionBank.add("Q4 I found it hard to focus on anything other than my anxiety.");
            questionBank.add("Q5 I felt nervous");
            questionBank.add("Q6 I felt uneasy.");
            questionBank.add("Q7 I felt tense.");

            level2_questionnaire = new Questionnaire(questionBank,2," How often you have been bothered by the following symptom during the past 7 days.",
                    "Never","Rarely","Sometimes","Often","Always");

        }

        else
        {
            Toast.makeText(getApplicationContext(), "Now you will be given a questionnaire for Repetitive thoughts and Behavioir", Toast.LENGTH_SHORT).show();
            //populate level2 OCD questionnaire
            questionBank.clear();
            questionBank.add("Q1 On average, how much time is occupied by these thoughts or behaviors each day?");
            questionBank.add("Q2 How much distress do these thoughts or behaviors cause you?");
            questionBank.add("Q3 How hard is it for you to control these thoughts or behaviors?");
            questionBank.add("Q4  How much do these thoughts or behaviors cause you to avoid doing anything, going anyplace or being with anyone?");
            questionBank.add("Q5  How much do these thoughts or behaviors interfere with school, work, or your social or family life?");

            level2_questionnaire = new Questionnaire(questionBank,2," How often you have been bothered by the following symptom during the past 7 days.",
                    "None","Mild","Moderate","Severe","Extreme");

        }

        return Disease;
    }

    public void updateQuestions() {

        if (level == level1_questionnaire.getLevel()) {

            questionBank=level1_questionnaire.getQuestion();
            if (questionNumber <questionBank.size()) {
                _AltText.setText(level1_questionnaire.getAlternativeText());
                _option1.setText(level1_questionnaire.getOption1());
                _option2.setText(level1_questionnaire.getOption2());
                _option3.setText(level1_questionnaire.getOption3());
                _option4.setText(level1_questionnaire.getOption4());
                _option5.setText(level1_questionnaire.getOption5());
                _question.setText(questionBank.get(questionNumber));
                questionNumber++;
            }
            else {
                Toast.makeText(getApplicationContext(), "level 1 Question ended", Toast.LENGTH_SHORT).show();
                questionNumber=0;
                level=2;
                generatedisease();
                updateQuestions();
            }


        }
        else {
            questionBank = level2_questionnaire.getQuestion();
            if (questionNumber <questionBank.size()) {
                _AltText.setText(level2_questionnaire.getAlternativeText());
                _option1.setText(level2_questionnaire.getOption1());
                _option2.setText(level2_questionnaire.getOption2());
                _option3.setText(level2_questionnaire.getOption3());
                _option4.setText(level2_questionnaire.getOption4());
                _option5.setText(level2_questionnaire.getOption5());
                _question.setText(questionBank.get(questionNumber));
                questionNumber++;
            } else {


                Toast.makeText(getApplicationContext(), "level 2 Question ended", Toast.LENGTH_SHORT).show();
                questionNumber=0;
                level=1;
                Intent intent=new Intent(getApplicationContext(),Home.class);
                startActivity(intent);
                finish();
            }

        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_test);
        overridePendingTransition(R.anim.pull_in_from_left,R.anim.hold);
        level=1;

        _NextButton = (Button)findViewById(R.id.button4);
        _question = (TextView)findViewById(R.id.textView13);
        _AltText = (TextView)findViewById(R.id.textView7);
        _option1 = (RadioButton)findViewById(R.id.radioButton);
        _option2 = (RadioButton)findViewById(R.id.radioButton2);
        _option3 = (RadioButton)findViewById(R.id.radioButton3);
        _option4 = (RadioButton)findViewById(R.id.radioButton4);
        _option5 = (RadioButton)findViewById(R.id.radioButton5);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);

        _AltText.setText("Select the choice that best describes how much you have been bothered by each problem during the past TWO (2) WEEKS");
        _option1.setText("Not at all");
        _option2.setText("Rare, less than a day or two");
        _option3.setText("Several days");
        _option4.setText("More than half the days");
        _option5.setText("Nearly everyday");

        questionNumber=0;
        questionBank.add("Q1 Little interest or pleasure in doing things?");
        questionBank.add("Q2 Feeling down, depressed, or hopeless?");
        questionBank.add("Q3 Feeling more irritated, grouchy, or angry than usual?");
        questionBank.add("Q4 Sleeping less than usual, but still have a lot of energy?");
        questionBank.add("Q5 Starting lots more projects than usual or doing more risky things than usual?");
        questionBank.add("Q6 Feeling nervous, anxious, frightened, worried, or on edge?");
        questionBank.add("Q7 Feeling panic or being frightened?");
        questionBank.add("Q8 Avoiding situations that make you anxious?");
        questionBank.add("Q9 Unexplained aches and pains (e.g., head, back, joints, abdomen, legs)?");
        questionBank.add("Q10 Feeling that your illnesses are not being taken seriously enough?");
        questionBank.add("Q11 Thoughts of actually hurting yourself?");
        questionBank.add("Q12 Hearing things other people couldn’t hear, such as voices even when no one was around?");
        questionBank.add("Q13 Feeling that someone could hear your thoughts, or that you could hear what another person was thinking?");
        questionBank.add("Q14 Problems with sleep that affected your sleep quality over all?");
        questionBank.add("Q15 Unpleasant thoughts, urges, or images that repeatedly enter your mind?");
        questionBank.add("Q16 Feeling driven to perform certain behaviors or mental acts over and over again?");
        questionBank.add("Q17 Drinking at least 4 drinks of any kind of alcohol in a single day?");
        questionBank.add("Q18 Smoking any cigarettes, a cigar, or pipe, or using snuff or chewing tobacco?");
        questionBank.add("Q19 Using any of the following medicines ON YOUR OWN, that is, without a doctor’s prescription, in greater amounts or longer than prescribed [e.g.,painkillers (like Vicodin), stimulants (like Ritalin or Adderall)");

        level1_questionnaire = new Questionnaire(questionBank,1, _AltText.getText().toString(),_option1.getText().toString(),_option2.getText().toString(),_option3.getText().toString(),_option4.getText().toString(),_option5.getText().toString());

        updateQuestions();

        _NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radioGroup.getCheckedRadioButtonId()!=-1)
                {
                    Toast.makeText(getApplicationContext(), "Answer selected : " + Integer.toString(answerSelected), Toast.LENGTH_SHORT).show();

                    if (level == 1) {
                        level1_questionnaire.setSelectedoption(answerSelected);
                    } else {
                        level2_questionnaire.setSelectedoption(answerSelected);
                    }
                    answers.add(answerSelected);
                    updateQuestions();
                    radioGroup.clearCheck();
                }

                else
                {
                    Toast.makeText(getApplicationContext(), "Please Select an Option", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId())
        {
            case R.id.radioButton: {
                if (checked) {
                    answerSelected = 1;
                    break;
                }
            }case R.id.radioButton2: {
            if (checked) {
                answerSelected = 2;
                break;
            }
        }
            case R.id.radioButton3: {
                if (checked) {
                    answerSelected = 3;
                    break;
                }
            }
            case R.id.radioButton4: {
                if (checked) {
                    answerSelected = 4;
                    break;
                }
            }
            case R.id.radioButton5: {
                if (checked) {
                    answerSelected = 5;
                    break;
                }
            }
        }

    }

    @Override
    protected void onPause() {
        // Whenever this activity is paused (i.e. looses focus because another activity is started etc)
        // Override how this activity is animated out of view
        // The new activity is kept still and this activity is pushed out to the right
        overridePendingTransition(R.anim.hold, R.anim.pull_out_to_right);
        super.onPause();
    }
}
