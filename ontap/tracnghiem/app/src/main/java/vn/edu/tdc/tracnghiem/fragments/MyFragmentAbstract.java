package vn.edu.tdc.tracnghiem.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import vn.edu.tdc.tracnghiem.R;
import vn.edu.tdc.tracnghiem.data_models.QuestionAbtract;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragmentAbstract extends Fragment {


    public MyFragmentAbstract() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    public void updateQuestionChosen(){

    }

    public void setQuestion(QuestionAbtract question) {
    }

    public void setPosition(int position) {
    }
}
