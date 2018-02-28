package vn.edu.tdc.tracnghiem.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import vn.edu.tdc.tracnghiem.R;
import vn.edu.tdc.tracnghiem.data_models.QuestionAbtract;


public class ResultFragment extends MyFragmentAbstract {
    //private String questionType;
    private ArrayList<QuestionAbtract> mQuestions;
    private ArrayList<String> mResult = new ArrayList<String>();

    public ResultFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = null;

        view = inflater.inflate(R.layout.fragment_result, container, false);


        ListView listView = (ListView) view.findViewById(R.id.listView);

        int i=0;
        int tong=0;
        for(QuestionAbtract ques:mQuestions){
            tong+=ques.getPoints();
            mResult.add((i+1) + " Diem dat duoc cua Cau hoi so "+(i+1)+ ": "+ques.getPoints());
            ++i;
        }
        mResult.add(" Ket qua cuoi cung la: " +tong);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, mResult);
        listView.setAdapter(listViewAdapter);

        return view;
    }

    @Override
    public void updateQuestionChosen() {

    }
    public void setmQuestions(ArrayList<QuestionAbtract> mQuestions){
        this.mQuestions = mQuestions;
    }
}
