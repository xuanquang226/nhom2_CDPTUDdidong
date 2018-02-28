package vn.edu.tdc.tracnghiem.data_models;

import java.util.ArrayList;

/**
 * Created by IT on 10/4/2017.
 */

public class QuestionType1 extends QuestionAbtract {
    private String desCription;
    private ArrayList<String> questionChoices;
    private ArrayList<Integer> questionAnswers;
    private ArrayList<Integer> chosen;

    public QuestionType1(String desCription, ArrayList<String> questionChoices, ArrayList<Integer> questionAnswers, ArrayList<Integer> chosen) {
        this.desCription = desCription;
        this.questionChoices = questionChoices;
        this.questionAnswers = questionAnswers;
        this.chosen = chosen;
        // Initiation for type of this class
        type = QuestionAbtract.CAU_NHIEU_LUA_CHON_MOT_DAP_AN;
    }

    public QuestionType1(String desCription, ArrayList<String> questionChoices, ArrayList<Integer> questionAnswers, ArrayList<Integer> chosen, String type) {
        this.desCription = desCription;
        this.questionChoices = questionChoices;
        this.questionAnswers = questionAnswers;
        this.chosen = chosen;
        // Initiation for type of this class
        this.type = type;
    }

    @Override
    public int getPoints() {
        int point = 0;

        if (this.getType().equalsIgnoreCase(QuestionAbtract.CAU_NHIEU_LUA_CHON_MOT_DAP_AN)) {
            if (questionAnswers.size() == chosen.size()) {
                if (chosen.get(0).longValue() == questionAnswers.get(0).longValue()) {
                    point = 1;
                }
            }
        } else if (this.getType().equalsIgnoreCase(QuestionAbtract.CAU_NHIEU_LUA_CHON_NHIEU_DAP_AN)) {
            if (questionAnswers.size() == chosen.size()) {
                int i;
                for(i=0; i<chosen.size();++i){
                    if (questionAnswers.get(i).longValue()!=chosen.get(i).longValue()){
                        break;
                    }
                }
                if(i==chosen.size()){
                    point = 1;
                }
            }
        } else if (this.getType().equalsIgnoreCase(QuestionAbtract.CAU_DUNG_SAI)) {
            if (questionAnswers.size() == chosen.size()) {
                int i;
                for(i=0; i<chosen.size();++i){
                    if (questionAnswers.get(i).longValue()!=chosen.get(i).longValue()){
                        break;
                    }
                }
                if(i==chosen.size()){
                    point = 1;
                }
            }
        }

        return point;
    }

    public String getDesCription() {
        return desCription;
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