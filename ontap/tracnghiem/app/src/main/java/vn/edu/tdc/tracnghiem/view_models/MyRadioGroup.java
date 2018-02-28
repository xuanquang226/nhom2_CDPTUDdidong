package vn.edu.tdc.tracnghiem.view_models;

import android.view.View;
import android.widget.RadioButton;

import java.util.ArrayList;

/**
 * Created by cuongtieu-kim on 10/8/17.
 */

public class MyRadioGroup{
    private ArrayList<RadioButton> radios = new ArrayList<RadioButton>();

    // Our Constructor
    public MyRadioGroup(RadioButton... radios) {
        for (RadioButton rb : radios) {
            this.radios.add(rb);
            rb.setOnClickListener(onClick);
        }
    }

    View.OnClickListener onClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            // let's deselect all radios in group
            for (RadioButton rb : radios) {
                rb.setChecked(false);
            }
            ((RadioButton) v).setChecked(true);
        }
    };
}
