package agi.erecreditsmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MultiMajorDialogLayout extends LinearLayout {

    Context context;
    RadioGroup multiMajorRadioGroup;

    public MultiMajorDialogLayout(Context context) {
        super(context);
        this.context = context;

        init();
    }

    public void init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.multimajordialog, this, true);

        multiMajorRadioGroup = (RadioGroup) findViewById(R.id.multiMajorRadioGroup);
    }

    public String checkSelectedMajor() {
        int id = multiMajorRadioGroup.getCheckedRadioButtonId();
        RadioButton selectedMajorButton = (RadioButton) findViewById(id);
        return selectedMajorButton.getText().toString();
    }
}
