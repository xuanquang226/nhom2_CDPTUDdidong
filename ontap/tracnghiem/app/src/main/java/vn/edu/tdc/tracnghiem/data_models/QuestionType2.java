package vn.edu.tdc.tracnghiem.data_models;

import java.util.ArrayList;

/**
 * Created by IT on 10/4/2017.
 */

public class QuestionType2 extends QuestionAbtract {

    private String desCription;
    private ArrayList<String> questionItems;
    private ArrayList<String> questionChoices;
    private ArrayList<Integer> questionAnswers;
    private ArrayList<Integer> chosen;

    public QuestionType2(String desCription, ArrayList<String> questionItems, ArrayList<String> questionChoices, ArrayList<Integer> questionAnswers, ArrayList<Integer> chosen) {
        this.desCription = desCription;
        this.questionItems = questionItems;
        this.questionChoices = questionChoices;
        this.questionAnswers = questionAnswers;
        this.chosen =chosen;
        // Initiation for type of this class
        type = QuestionAbtract.CAU_GHEP_DOI;
    }

    // Calculate marks of the question
    @Override
    public int getPoints(){
        int point =0;
        int i;

        if(questionAnswers.size()==chosen.size()){
            for(i=0; i<chosen.size();++i){
                if (questionAnswers.get(i).longValue()!=chosen.get(i).longValue()){
                    break;
                }
            }
            if(i==chosen.size()){
                point = 1;
            }
        }

        return point;
    }

    public String getDesCription() {
        return desCription;
    }

    public ArrayList<String> getQuestionItems() {
        return questionItems;
    }

    public ArrayList<String> getQuestionChoices() {
        return questionChoices;
    }

    public ArrayList<Integer> getQuestionAnswers() {
        return questionAnswers;
    }

    public ArrayList<Integer> getChosen() {
        return chosen;
    }

}
