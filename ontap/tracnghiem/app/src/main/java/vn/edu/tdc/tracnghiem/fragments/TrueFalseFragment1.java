package vn.edu.tdc.tracnghiem.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import vn.edu.tdc.tracnghiem.R;
import vn.edu.tdc.tracnghiem.data_models.QuestionAbtract;
import vn.edu.tdc.tracnghiem.data_models.QuestionType1;
import vn.edu.tdc.tracnghiem.view_models.MyRadioGroup;


public class TrueFalseFragment1 extends MyFragmentAbstract {
    //private String questionType;
    private QuestionType1 question;
    private int position;

    private ToggleButton tbtnA;
    private ToggleButton tbtnB;
    private ToggleButton tbtnC;

    public TrueFalseFragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = null;

        view = inflater.inflate(R.layout.fragment_question_true_false1, container, false);

        EditText edtCau4 = (EditText) view.findViewById(R.id.edtCau4);
        TextView lblQuestion = (TextView) view.findViewById(R.id.lblQuestion);
        EditText edtA = (EditText) view.findViewById(R.id.edtA);
        EditText edtB = (EditText) view.findViewById(R.id.edtB);
        EditText edtC = (EditText) view.findViewById(R.id.edtC);
        tbtnA = (ToggleButton) view.findViewById(R.id.tbtnA);
        tbtnB = (ToggleButton) view.findViewById(R.id.tbtnB);
        tbtnC = (ToggleButton) view.findViewById(R.id.tbtnC);

        edtCau4.setText(question.getDesCription());
        lblQuestion.setText("Cau "+ position +":");
        edtA.setText(question.getQuestionChoices().get(0));
        edtB.setText(question.getQuestionChoices().get(1));
        edtC.setText(question.getQuestionChoices().get(2));


        return view;
    }

    @Override
    public void setQuestion(QuestionAbtract question) {
        this.question = (QuestionType1) question;
    }

    @Override
    public void setPosition(int position) {
        this.position = position +1;
    }

    @Override
    public void updateQuestionChosen(){

        question.getChosen().clear();

        if(tbtnA.isChecked()){
            question.getChosen().add(1);
        }
        else{
            question.getChosen().add(0);
        }
        if(tbtnB.isChecked()){
            question.getChosen().add(1);
        }
        else{
            question.getChosen().add(0);
        }
        if(tbtnC.isChecked()){
            question.getChosen().add(1);
        }
        else{
            question.getChosen().add(0);
        }
    }
}
