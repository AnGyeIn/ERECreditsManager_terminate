package agi.erecreditsmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ERETMSpinnerDropdownItemLayout extends LinearLayout {

    Context context;
    TextView dropdownItemTextView;

    public ERETMSpinnerDropdownItemLayout(Context context) {
        super(context);
        this.context = context;

        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.eretm_spinner_dropdown_item, this, true);

        dropdownItemTextView = (TextView) findViewById(R.id.dropdownItemTextView);
    }

    public void setDropdownItemTextView(String text) {
        dropdownItemTextView.setText(text);
    }
}
