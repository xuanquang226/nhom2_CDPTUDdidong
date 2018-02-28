package vn.edu.tdc.tracnghiem.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import vn.edu.tdc.tracnghiem.R;
import vn.edu.tdc.tracnghiem.data_models.QuestionAbtract;
import vn.edu.tdc.tracnghiem.data_models.QuestionType1;

public class MultiChoice1Fragment extends MyFragmentAbstract {
    //private String questionType;
    private QuestionType1 question;
    private int position;

    private CheckBox chkA;
    private CheckBox chkB;
    private CheckBox chkC;
    private CheckBox chkD;

    public MultiChoice1Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = null;


        view = inflater.inflate(R.layout.fragment_multi_choice1, container, false);
        EditText edtCau1 = (EditText) view.findViewById(R.id.edtCau1);
        TextView lblQuestion = (TextView) view.findViewById(R.id.lblQuestion);
        chkA = (CheckBox) view.findViewById(R.id.chkA);
        chkB = (CheckBox) view.findViewById(R.id.chkB);
        chkC = (CheckBox) view.findViewById(R.id.chkC);
        chkD = (CheckBox) view.findViewById(R.id.chkD);


        edtCau1.setText(question.getDesCription());
        lblQuestion.setText("Cau "+ position +":");
        chkA.setText(question.getQuestionChoices().get(0));
        chkB.setText(question.getQuestionChoices().get(1));
        chkC.setText(question.getQuestionChoices().get(2));
        chkD.setText(question.getQuestionChoices().get(3));


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

        if (chkA.isChecked()) {
            question.getChosen().add(0);
        }
        if (chkB.isChecked()) {
            question.getChosen().add(1);
        }
        if (chkC.isChecked()) {
            question.getChosen().add(2);
        }
        if (chkD.isChecked()) {
            question.getChosen().add(3);
        }
    }
}
