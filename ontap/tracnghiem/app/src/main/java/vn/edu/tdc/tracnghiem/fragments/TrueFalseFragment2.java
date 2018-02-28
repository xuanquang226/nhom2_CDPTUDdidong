package vn.edu.tdc.tracnghiem.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import vn.edu.tdc.tracnghiem.R;
import vn.edu.tdc.tracnghiem.data_models.QuestionAbtract;
import vn.edu.tdc.tracnghiem.data_models.QuestionType1;


public class TrueFalseFragment2 extends MyFragmentAbstract {
    //private String questionType;
    private QuestionType1 question;
    private int position;

    private Switch swtA;
    private Switch swtB;
    private Switch swtC;

    public TrueFalseFragment2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = null;

        view = inflater.inflate(R.layout.fragment_question_true_false2, container, false);


        EditText edtCau5 = (EditText) view.findViewById(R.id.edtCau5);
        TextView lblQuestion = (TextView) view.findViewById(R.id.lblQuestion);
        EditText edtA = (EditText) view.findViewById(R.id.edtA);
        EditText edtB = (EditText) view.findViewById(R.id.edtB);
        EditText edtC = (EditText) view.findViewById(R.id.edtC);
        swtA = (Switch) view.findViewById(R.id.swtA);
        swtB = (Switch) view.findViewById(R.id.swtB);
        swtC = (Switch) view.findViewById(R.id.swtC);

        edtCau5.setText(question.getDesCription());
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

        if(swtA.isChecked()){
            question.getChosen().add(1);
        }
        else{
            question.getChosen().add(0);
        }
        if(swtB.isChecked()){
            question.getChosen().add(1);
        }
        else{
            question.getChosen().add(0);
        }
        if(swtC.isChecked()){
            question.getChosen().add(1);
        }
        else{
            question.getChosen().add(0);
        }
    }
}
