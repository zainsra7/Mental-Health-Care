package com.l134046zain.mentalhealthcare;

import java.util.ArrayList;

/**
 * Created by Zan on 12/7/2016.
 */
public class Questionnaire {

    private ArrayList<String> Question;
    private int level;
    private String AlternativeText;
    private String Option1;
    private String Option2;
    private String Option3;
    private String Option4;
    private String Option5;
    private ArrayList<Integer>Selectedoption;

    public int getSelectedoption(int i) {
        return Selectedoption.get(i);
    }

    public void setSelectedoption(int selectedoption) {
        Selectedoption .add(selectedoption);
    }
    public ArrayList<Integer> getAns()
    {return Selectedoption;}

    public Questionnaire(ArrayList<String> question, int level, String alternativeText, String option1, String option2, String option3, String option4, String option5) {
        Selectedoption=new ArrayList<Integer>();
        Question = question;
        this.level = level;
        AlternativeText = alternativeText;
        Option1 = option1;
        Option2 = option2;
        Option3 = option3;
        Option4 = option4;
        Option5 = option5;

    }

    public ArrayList<String> getQuestion() {
        return Question;
    }

    public int getLevel() {
        return level;
    }

    public String getAlternativeText() {
        return AlternativeText;
    }

    public String getOption1() {
        return Option1;
    }

    public String getOption2() {
        return Option2;
    }

    public String getOption3() {
        return Option3;
    }

    public String getOption4() {
        return Option4;
    }

    public String getOption5() {
        return Option5;
    }

    public void setQuestion(ArrayList<String> question) {
        Question = question;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setAlternativeText(String alternativeText) {
        AlternativeText = alternativeText;
    }

    public void setOption1(String option1) {
        Option1 = option1;
    }

    public void setOption2(String option2) {
        Option2 = option2;
    }

    public void setOption3(String option3) {
        Option3 = option3;
    }

    public void setOption4(String option4) {
        Option4 = option4;
    }

    public void setOption5(String option5) {
        Option5 = option5;
    }
}
