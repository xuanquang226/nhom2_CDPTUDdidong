package vn.edu.tdc.tracnghiem.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import vn.edu.tdc.tracnghiem.R;
import vn.edu.tdc.tracnghiem.data_models.QuestionAbtract;
import vn.edu.tdc.tracnghiem.data_models.QuestionType1;
import vn.edu.tdc.tracnghiem.view_models.MyRadioGroup;


public class MultiChoice2Fragment extends MyFragmentAbstract {
    //private String questionType;
    private QuestionType1 question;
    private int position;

    private RadioButton radA;
    private RadioButton radB;
    private RadioButton radC;
    private RadioButton radD;

    public MultiChoice2Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = null;

        view = inflater.inflate(R.layout.fragment_multi_choice2, container, false);

        EditText edtCau2 = (EditText) view.findViewById(R.id.edtCau2);
        TextView lblQuestion = (TextView) view.findViewById(R.id.lblQuestion);
        //RadioGroup radGroup = (RadioGroup) findViewById(R.id.radGroup);
        radA = (RadioButton) view.findViewById(R.id.radA);
        radB = (RadioButton) view.findViewById(R.id.radB);
        radC = (RadioButton) view.findViewById(R.id.radC);
        radD = (RadioButton) view.findViewById(R.id.radD);

        MyRadioGroup myRadioGroup = new MyRadioGroup(radA, radB, radC, radD);


        edtCau2.setText(question.getDesCription());
        lblQuestion.setText("Cau "+ position +":");
        radA.setText(question.getQuestionChoices().get(0));
        radB.setText(question.getQuestionChoices().get(1));
        radC.setText(question.getQuestionChoices().get(2));
        radD.setText(question.getQuestionChoices().get(3));


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

        if (radA.isChecked()) {
            question.getChosen().add(0);
        }
        if (radB.isChecked()) {
            question.getChosen().add(1);
        }
        if (radC.isChecked()) {
            question.getChosen().add(2);
        }
        if (radD.isChecked()) {
            question.getChosen().add(3);
        }
    }
}
