package vn.edu.tdc.tracnghiem.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import vn.edu.tdc.tracnghiem.R;
import vn.edu.tdc.tracnghiem.data_models.QuestionAbtract;
import vn.edu.tdc.tracnghiem.data_models.QuestionType2;


public class QuestionMatchFragment extends MyFragmentAbstract {
    //private String questionType;
    private QuestionType2 question;
    private int position;
    private Spinner spinA;
    private Spinner spinB;
    private Spinner spinC;


    public QuestionMatchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = null;

        view = inflater.inflate(R.layout.fragment_question_match, container, false);

        EditText edtCau3 = (EditText) view.findViewById(R.id.edtCau3);
        TextView lblQuestion = (TextView) view.findViewById(R.id.lblQuestion);
        EditText edtA = (EditText) view.findViewById(R.id.edtA);
        EditText edtB = (EditText) view.findViewById(R.id.edtB);
        EditText edtC = (EditText) view.findViewById(R.id.edtC);
        spinA = (Spinner) view.findViewById(R.id.spinA);
        spinB = (Spinner) view.findViewById(R.id.spinB);
        spinC = (Spinner) view.findViewById(R.id.spinC);


        edtCau3.setText(question.getDesCription());
        lblQuestion.setText("Cau "+ position +":");
        edtA.setText(question.getQuestionItems().get(0));
        edtB.setText(question.getQuestionItems().get(1));
        edtC.setText(question.getQuestionItems().get(2));

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_spinner_item, question.getQuestionChoices());
        spinA.setAdapter(spinnerAdapter);
        spinB.setAdapter(spinnerAdapter);
        spinC.setAdapter(spinnerAdapter);


        return view;
    }

    @Override
    public void setQuestion(QuestionAbtract question) {
        this.question = (QuestionType2) question;
    }

    @Override
    public void setPosition(int position) {
        this.position = position +1;
    }

    @Override
    public void updateQuestionChosen(){

        question.getChosen().clear();

        question.getChosen().add(spinA.getSelectedItemPosition());
        //Log.d("test", spinA.getSelectedItemPosition()+"");
        question.getChosen().add(spinB.getSelectedItemPosition());
        question.getChosen().add(spinC.getSelectedItemPosition());
    }
}
